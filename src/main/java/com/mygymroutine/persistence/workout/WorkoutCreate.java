package com.mygymroutine.persistence.workout;

import com.mygymroutine.persistence.exercise.Exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutCreate {
	
    private WorkoutResponse workout;
    private Exercise[] exerciseList;

}
