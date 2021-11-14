package com.lme.marsrover.processor;

import static com.lme.marsrover.model.ProcessingStep.FORWARD;

import org.springframework.stereotype.Component;

import com.lme.marsrover.model.MarsRover;
import com.lme.marsrover.model.ProcessingStep;
import com.lme.marsrover.model.RoverCoordinate;

@Component
public class MarsRoverProcessor {

  private MarsRoverScentManager marsRoverScentManager;

  public MarsRoverProcessor(MarsRoverScentManager marsRoverScentManager) {
    this. marsRoverScentManager = marsRoverScentManager;
  }

  public String processMarsRover(int maxX, int maxY, MarsRover marsRover) {

    for (ProcessingStep processingStep : marsRover.getProcessingStepList()) {
      if(isValidStep(maxX, maxY, processingStep, marsRover)) {
        performStep(processingStep, marsRover);
      }
      else {
        if (marsRoverScentManager.isCoordinateScented(marsRover.getXCoordinate(), marsRover.getYCoordinate())) {
          //refusing this instructions, but carrying on
        } else {
          //storing scent and getting lost
          addMarsRoverScent(marsRover.getXCoordinate(), marsRover.getYCoordinate());
          return marsRover.getXCoordinate() + " " + marsRover.getYCoordinate() + " " + marsRover.getDirection().getDirectionCharacter() + " LOST";
        }
      }
    }

    return marsRover.getCurrentLocation();
  }

  private void performStep(ProcessingStep processingStep, MarsRover marsRover) {

    switch(processingStep) {
    case LEFT:
      marsRover.turnLeft();
      break;
    case RIGHT:
      marsRover.turnRight();
      break;
    case FORWARD:
      RoverCoordinate coordinateAfterMove = marsRover.evalCoordinateAfterMove();
      marsRover.setXCoordinate(coordinateAfterMove.getXCoordinate());
      marsRover.setYCoordinate(coordinateAfterMove.getYCoordinate());
      break;
    }
  }

  private boolean isValidStep(int maxX, int maxY, ProcessingStep processingStep, MarsRover marsRover) {
    if (! processingStep.equals(FORWARD)) {
      return true;
    } else {
      RoverCoordinate coordinateAfterMove = marsRover.evalCoordinateAfterMove();
      if (coordinateAfterMove.getXCoordinate() > maxX ||
          coordinateAfterMove.getYCoordinate() > maxY ||
          coordinateAfterMove.getXCoordinate() < 0 ||
          coordinateAfterMove.getYCoordinate() < 0) {
        return false;
      } else {
        return true;
      }
    }
  }

  private void addMarsRoverScent(int xCoordinate, int yCoordinate) {
    RoverCoordinate roverCoordinate = RoverCoordinate.builder()
        .xCoordinate(xCoordinate)
        .yCoordinate(yCoordinate)
        .build();

    marsRoverScentManager.addMarsRoverScent(roverCoordinate);
  }
}