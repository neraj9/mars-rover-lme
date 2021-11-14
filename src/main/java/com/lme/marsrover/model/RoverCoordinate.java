package com.lme.marsrover.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverCoordinate {
  private int xCoordinate;
  private int yCoordinate;
}
