package com.lme.marsrover;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.lme.marsrover.model.RoverCoordinate;

public class MarsRoverScentManagerTest {

  @Test
  public void marsRoverScentTest() {

    int testX = 1;
    int testY = 4;

    MarsRoverScentManager marsRoverScentManager = new MarsRoverScentManager();
    boolean coordinateScented = marsRoverScentManager.isCoordinateScented(testX, testY);

    assertEquals(false, coordinateScented);

    RoverCoordinate roverCoordinate = RoverCoordinate.builder()
        .xCoordinate(testX)
        .yCoordinate(testY)
        .build();

    marsRoverScentManager.addMarsRoverScent(roverCoordinate);

    boolean coordinateScented2 = marsRoverScentManager.isCoordinateScented(testX, testY);

    assertEquals(true, coordinateScented2);
  }
}