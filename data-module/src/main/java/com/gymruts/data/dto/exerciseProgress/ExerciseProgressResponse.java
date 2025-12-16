package com.gymruts.data.dto.exerciseProgress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseProgressResponse {
	
    private Long id;
	
    private Long weekDayExerciseDetailsId;
	
	private int series;
	
	private int reps;
	
	private double weight;
	
	private Date workoutDate;

}
