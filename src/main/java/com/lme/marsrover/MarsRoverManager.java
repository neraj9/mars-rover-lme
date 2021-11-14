package com.lme.marsrover;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lme.marsrover.mapper.MarsRoverInputMapper;
import com.lme.marsrover.model.MarsRoverInput;
import com.lme.marsrover.processor.MarsRoverProcessor;

@Component
public class MarsRoverManager {

  private MarsRoverInputMapper marsRoverInputMapper;
  private MarsRoverProcessor marsRoverProcessor;

  public MarsRoverManager(MarsRoverInputMapper marsRoverInputMapper, MarsRoverProcessor marsRoverProcessor) {
    this.marsRoverInputMapper = marsRoverInputMapper;
    this.marsRoverProcessor = marsRoverProcessor;
  }

  public List<String> processMarsRoverInput(List<String> marsRoverInputs) {
    final MarsRoverInput marsRoverInput = marsRoverInputMapper.mapToMarsRoverInput(marsRoverInputs);

    return marsRoverInput.getMarsRoverList().stream()
        .map(aMarsRover -> marsRoverProcessor.processMarsRover(marsRoverInput.getMaxX(), marsRoverInput.getMaxY(), aMarsRover))
        .collect(toList());
  }
}
