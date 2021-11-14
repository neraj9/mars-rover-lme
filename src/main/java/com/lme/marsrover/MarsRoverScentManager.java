package com.lme.marsrover;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lme.marsrover.model.RoverCoordinate;

@Component
public class MarsRoverScentManager {
  private List<RoverCoordinate> scentedCoordinates = new ArrayList<>();

  public boolean isCoordinateScented(int xCoordinate,  int yCoordinate) {
    return scentedCoordinates.stream()
        .anyMatch(aCoordinate -> aCoordinate.getXCoordinate() == xCoordinate && aCoordinate.getYCoordinate() == yCoordinate);
  }

  public void addMarsRoverScent(RoverCoordinate roverCoordinate) {
    scentedCoordinates.add(roverCoordinate);
  }
}
