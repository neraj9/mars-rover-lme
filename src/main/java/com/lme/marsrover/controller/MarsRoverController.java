package com.lme.marsrover.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lme.marsrover.processor.MarsRoverManager;
import com.lme.marsrover.model.web.RoverRequest;
import com.lme.marsrover.model.web.RoverResponse;

@RestController
public class MarsRoverController {

  private MarsRoverManager marsRoverManager;

  public MarsRoverController(MarsRoverManager marsRoverManager) {
    this.marsRoverManager = marsRoverManager;
  }

  @PostMapping(value="/processMarsRover")
  public RoverResponse processMarsRoverRequest(@RequestBody RoverRequest roverRequest) {

    return RoverResponse.builder()
        .responseList(marsRoverManager.processMarsRoverInput(roverRequest.getInputStrings()))
            .build();
  }
}
