package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeekDayExerciseDetailsResponse {
	
    private Long id;
	
	private int series;
	
	private int reps;
	
	private double weight;
	
    private Long exerciseId;

    private Long routineCreationId;
}
