package com.lme.marsrover;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lme.marsrover.model.web.RoverRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarsRoverIT {

  @LocalServerPort
  private int port;

  private TestRestTemplate testRestTemplate;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @BeforeEach
  public void setup() {
    restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
    testRestTemplate = new TestRestTemplate(restTemplateBuilder);
  }

  @Test
  public void testProcessMarsRover() throws Exception {

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    List<String> marsRoverInputs = asList("5 3", "1 1 E", "RFRFRFRF", "3 2 N", "FRRFLLFFRRFLL", "0 3 W", "LLFFFLFLFL");

    RoverRequest roverRequest = RoverRequest.builder()
        .inputStrings(marsRoverInputs)
        .build();

    final HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(roverRequest),
        headers);

    String processMarsRoverUrl = "/processMarsRover";
    final ResponseEntity<String> response = testRestTemplate.exchange(processMarsRoverUrl,
        HttpMethod.POST,
        request,
        String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(MediaType.APPLICATION_JSON.isCompatibleWith(response.getHeaders().getContentType()));

    String expectedJsonString = "{\"responseList\":[\"1 1 E\",\"3 3 N LOST\",\"2 3 S\"]}";

    assertEquals(expectedJsonString, response.getBody());
  }
}
