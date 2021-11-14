package com.lme.marsrover.model;

import java.util.Arrays;

public enum Direction {

	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

	private final String directionCharacter;

	Direction(String directionCharacter) {
		this.directionCharacter = directionCharacter;
	}

	public static Direction getDirection(String directionString) {

		return Arrays.stream(Direction.values())
				.filter(aDirection -> aDirection.getDirectionCharacter().equalsIgnoreCase(directionString))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid Direction"));
	}

	public String getDirectionCharacter() {
		return directionCharacter;
	}

}