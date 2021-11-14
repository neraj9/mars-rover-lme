package com.lme.marsrover.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lme.marsrover.MarsRoverScentManager;
import com.lme.marsrover.model.Direction;
import com.lme.marsrover.model.MarsRover;

@ExtendWith(MockitoExtension.class)
public class MarsRoverProcessorTest {

  @Mock
  private MarsRoverScentManager marsRoverScentManager;

  @Test
  public void processMarsRover_valid() {

    MarsRover marsRover = MarsRover.builder()
        .xCoordinate(1)
        .yCoordinate(1)
        .direction(Direction.EAST)
        .processingSteps("RF")
        .build();

    MarsRoverProcessor marsRoverProcessor = new MarsRoverProcessor(marsRoverScentManager);

    String processedOutput = marsRoverProcessor.processMarsRover(3, 4, marsRover);

    assertEquals("1 0 S", processedOutput);
  }

  @Test
  public void processMarsRover_lost() {

    MarsRover marsRover = MarsRover.builder()
        .xCoordinate(1)
        .yCoordinate(1)
        .direction(Direction.EAST)
        .processingSteps("RFF")
        .build();

    MarsRoverProcessor marsRoverProcessor = new MarsRoverProcessor(marsRoverScentManager);

    when(marsRoverScentManager.isCoordinateScented(1, 0)).thenReturn(false);

    String processedOutput = marsRoverProcessor.processMarsRover(1, 4, marsRover);

    assertEquals("1 0 S LOST", processedOutput);
  }

  @Test
  public void processMarsRover_ignoringScented() {

    MarsRover marsRover = MarsRover.builder()
        .xCoordinate(1)
        .yCoordinate(1)
        .direction(Direction.EAST)
        .processingSteps("RFF")
        .build();

    MarsRoverProcessor marsRoverProcessor = new MarsRoverProcessor(marsRoverScentManager);

    when(marsRoverScentManager.isCoordinateScented(1, 0)).thenReturn(true);

    String processedOutput = marsRoverProcessor.processMarsRover(1, 4, marsRover);

    assertEquals("1 0 S", processedOutput);
  }
}