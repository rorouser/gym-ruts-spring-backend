package com.mygymroutine.persistence.workout;

import com.mygymroutine.persistence.routine.RoutineResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponse {
	
    private Long workoutId;

    private String workoutName;

    private Integer user_id;

}
