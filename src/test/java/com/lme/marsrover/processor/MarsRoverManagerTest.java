package com.lme.marsrover.processor;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lme.marsrover.mapper.MarsRoverInputMapper;
import com.lme.marsrover.model.Direction;
import com.lme.marsrover.model.MarsRover;
import com.lme.marsrover.model.MarsRoverInput;
import com.lme.marsrover.processor.MarsRoverManager;
import com.lme.marsrover.processor.MarsRoverProcessor;

@ExtendWith(MockitoExtension.class)
public class MarsRoverManagerTest {

  @Mock
  private MarsRoverInputMapper marsRoverInputMapper;

  @Mock
  private MarsRoverProcessor marsRoverProcessor;

  @Test
  public void processMarsRoverInput() {
    List<String> marsRoverInputs = asList("5 3", "1 1 E", "RFRFRFRF");

    MarsRover marsRover = MarsRover.builder()
        .xCoordinate(1)
        .yCoordinate(1)
        .direction(Direction.EAST)
        .processingSteps("RFRFRFRF")
        .build();

    MarsRoverInput marsRoverInput = MarsRoverInput.builder()
        .maxX(5)
        .maxY(3)
        .marsRoverList(asList(marsRover)).build();


    when(marsRoverInputMapper.mapToMarsRoverInput(marsRoverInputs)).thenReturn(marsRoverInput);

    when(marsRoverProcessor.processMarsRover(5,3, marsRover)).thenReturn("1 1 E");

    MarsRoverManager marsRoverManager = new MarsRoverManager(marsRoverInputMapper, marsRoverProcessor);

    List<String> roverOutput = marsRoverManager.processMarsRoverInput(marsRoverInputs);

    List<String> expectedRoverOutput = asList("1 1 E");

    assertEquals(expectedRoverOutput, roverOutput);

    verify(marsRoverInputMapper).mapToMarsRoverInput(marsRoverInputs);
    verify(marsRoverProcessor).processMarsRover(5,3, marsRover);
  }
}