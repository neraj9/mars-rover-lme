package com.lme.marsrover.model;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarsRover {
  private int xCoordinate;
  private int yCoordinate;

  private Direction direction;

  private String processingSteps;

  public String getCurrentLocation() {
    return xCoordinate + " " + yCoordinate  + " " + direction.getDirectionCharacter();
  }

  public List<ProcessingStep> getProcessingStepList() {
    return Arrays.stream(processingSteps.split(""))
        .map(aProcessingStep -> ProcessingStep.getProcessingStep(aProcessingStep))
        .collect(toList());
  }

  public void turnLeft(){
    if (direction.equals(Direction.NORTH)) {
      direction = Direction.WEST;
    }

    else if (direction.equals(Direction.EAST)) {
      direction = Direction.NORTH;
    }

    else if (direction.equals(Direction.SOUTH)) {
      direction = Direction.EAST;
    }

    else if (direction.equals(Direction.WEST)) {
      direction = Direction.SOUTH;
    }
  }

  public void turnRight(){
    if (direction.equals(Direction.NORTH)) {
      direction = Direction.EAST;
    }

    else if (direction.equals(Direction.EAST)) {
      direction = Direction.SOUTH;
    }

    else if (direction.equals(Direction.SOUTH)) {
      direction = Direction.WEST;
    }

    else if (direction.equals(Direction.WEST)) {
      direction = Direction.NORTH;
    }
  }

  public RoverCoordinate evalCoordinateAfterMove() {
    int xCoord = xCoordinate;
    int yCoord = yCoordinate;

    if (direction.equals(Direction.NORTH)) {
      yCoord =  yCoord + 1;
    }

    else if (direction.equals(Direction.EAST)) {
      xCoord = xCoord + 1;
    }

    else if (direction.equals(Direction.SOUTH)) {
      yCoord =  yCoord - 1;
    }

    else if (direction.equals(Direction.WEST)) {
      xCoord = xCoord - 1;
    }

    return RoverCoordinate.builder()
        .xCoordinate(xCoord)
        .yCoordinate(yCoord)
        .build();
  }

}