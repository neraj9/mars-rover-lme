package com.lme.marsrover.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lme.marsrover.model.Direction;
import com.lme.marsrover.model.MarsRover;
import com.lme.marsrover.model.MarsRoverInput;

@Component
public class MarsRoverInputMapper {

  public MarsRoverInput mapToMarsRoverInput(List<String> marsRoverInputs) {

    String[] firstLineElements = marsRoverInputs.get(0).split(" ");

    int maxX = Integer.valueOf(firstLineElements[0]);
    int maxY = Integer.valueOf(firstLineElements[1]);

    List<MarsRover> marsRoverList = new ArrayList<>();

    for (int i = 1; i < marsRoverInputs.size(); i = i + 2) {
      MarsRover rover = createRover(marsRoverInputs.get(i),
          marsRoverInputs.get(i + 1));
      marsRoverList.add(rover);
    }

    return MarsRoverInput.builder()
        .maxX(maxX)
        .maxY(maxY)
        .marsRoverList(marsRoverList)
        .build();
  }

  private MarsRover createRover(String initialPositionString, String processingSteps) {
    String[] initialPositionElements = initialPositionString.split(" ");

    int xCoordinate = Integer.valueOf(initialPositionElements[0]);
    int yCoordinate = Integer.valueOf(initialPositionElements[1]);
    Direction direction = Direction.getDirection(initialPositionElements[2]);

    return MarsRover.builder()
        .xCoordinate(xCoordinate)
        .yCoordinate(yCoordinate)
        .direction(direction)
        .processingSteps(processingSteps)
        .build();
  }
}
