package com.lme.marsrover;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lme.marsrover.mapper.MarsRoverInputMapper;
import com.lme.marsrover.processor.MarsRoverProcessor;

@ExtendWith(SpringExtension.class)
@Import({ MarsRoverManager.class, MarsRoverInputMapper.class, MarsRoverScentManager.class, MarsRoverProcessor.class})
public class MarsRoverManagerIntegrationTest {

  @Autowired
  private MarsRoverManager marsRoverManager;

  @Test
  public void processMarsRoverInput() {

    List<String> marsRoverInputs = asList("5 3", "1 1 E", "RFRFRFRF", "3 2 N", "FRRFLLFFRRFLL", "0 3 W", "LLFFFLFLFL");

    List<String> roverOutput = marsRoverManager.processMarsRoverInput(marsRoverInputs);

    List<String> expectedRoverOutput = asList("1 1 E" , "3 3 N LOST", "2 3 S");

    assertEquals(expectedRoverOutput, roverOutput);
  }
}