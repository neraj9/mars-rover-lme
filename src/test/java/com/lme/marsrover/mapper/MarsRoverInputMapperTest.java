package com.lme.marsrover.mapper;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.lme.marsrover.model.Direction;
import com.lme.marsrover.model.MarsRover;
import com.lme.marsrover.model.MarsRoverInput;

public class MarsRoverInputMapperTest {

  @Test
  public void mapToMarsRoverInput() {

    List<String> marsRoverInputs = asList("5 3", "1 1 E", "RFRFRFRF");

    MarsRoverInputMapper marsRoverInputMapper = new MarsRoverInputMapper();

    MarsRoverInput roverInput = marsRoverInputMapper.mapToMarsRoverInput(marsRoverInputs);

    MarsRover marsRover = MarsRover.builder()
        .xCoordinate(1)
        .yCoordinate(1)
        .direction(Direction.EAST)
        .processingSteps("RFRFRFRF")
        .build();

    MarsRoverInput expectedMarsRoverInput = MarsRoverInput.builder()
        .maxX(5)
        .maxY(3)
        .marsRoverList(asList(marsRover))
        .build();

    assertEquals(expectedMarsRoverInput, roverInput);
  }
}