package com.mygymroutine.persistence.routineWorkout;

import com.mygymroutine.persistence.workout.WorkoutResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineWorkoutResponse {
	
    private Long id;

    private WorkoutResponse workout;

    private Long routineId;
}
