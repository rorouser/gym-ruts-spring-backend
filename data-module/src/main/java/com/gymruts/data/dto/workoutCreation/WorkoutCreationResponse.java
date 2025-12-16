package com.gymruts.data.dto.workoutCreation;


import com.gymruts.data.dto.exercise.ExerciseResponse;
import com.gymruts.data.dto.workout.WorkoutResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutCreationResponse {
	
    private Long id;

    private WorkoutResponse workout;

    private ExerciseResponse exercise;

}
