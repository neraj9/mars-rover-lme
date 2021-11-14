package com.lme.marsrover.model;

import java.util.Arrays;

public enum ProcessingStep {

	LEFT("L"), RIGHT("R"), FORWARD("F");

	private final String processingStepCharacter;

	ProcessingStep(String processingStepCharacter) {
		this.processingStepCharacter = processingStepCharacter;
	}

	public static ProcessingStep getProcessingStep(String processingStep) {

		return Arrays.stream(ProcessingStep.values())
				.filter(aProcessingStep -> aProcessingStep.getProcessingStepCharacter().equalsIgnoreCase(processingStep))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid Processing Step"));
	}

	public String getProcessingStepCharacter() {
		return processingStepCharacter;
	}
}