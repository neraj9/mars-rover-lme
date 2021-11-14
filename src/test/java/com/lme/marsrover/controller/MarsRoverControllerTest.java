package com.lme.marsrover.controller;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lme.marsrover.MarsRoverManager;
import com.lme.marsrover.model.web.RoverRequest;

@WebMvcTest( controllers = MarsRoverController.class )
public class MarsRoverControllerTest {

  @MockBean
  private MarsRoverManager marsRoverManager;

  @Autowired
  private MockMvc mvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void processMarsRoverRequest() throws Exception {
    List<String> marsRoverInputs = asList("5 3", "1 1 E", "RFRFRFRF", "3 2 N", "FRRFLLFFRRFLL", "0 3 W", "LLFFFLFLFL");

    List<String> expectedRoverOutput = asList("1 1 E" , "3 3 N LOST", "2 3 S");

    when(marsRoverManager.processMarsRoverInput(marsRoverInputs)).thenReturn(expectedRoverOutput);

    RoverRequest roverRequest = RoverRequest.builder()
        .inputStrings(marsRoverInputs)
        .build();

    String roverRequestString = objectMapper.writeValueAsString(roverRequest);

    MvcResult result = mvc
        .perform( MockMvcRequestBuilders.post( "/processMarsRover" )
            .content(roverRequestString)
            .contentType( MediaType.APPLICATION_JSON )
            .accept( MediaType.APPLICATION_JSON ) )
        .andExpect( content( ).contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
        .andExpect( status( ).isOk( ) )
        .andReturn( );

    String content = result
        .getResponse( )
        .getContentAsString( );

    String expectedJsonString = "{\"responseList\":[\"1 1 E\",\"3 3 N LOST\",\"2 3 S\"]}";

    assertEquals(expectedJsonString, content);
  }
}