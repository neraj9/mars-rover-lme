package com.lme.marsrover.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarsRoverInput {

  private int maxX;
  private int maxY;

  private List<MarsRover> marsRoverList;
}
