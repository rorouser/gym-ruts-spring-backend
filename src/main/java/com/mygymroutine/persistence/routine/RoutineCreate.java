package com.mygymroutine.persistence.routine;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineCreate {
	
    private RoutineResponse routine;
    private WorkoutResponse[] workoutResponseList;

}
