package com.mygymroutine.persistence.routine;

import com.mygymroutine.persistence.routine.routineCreation.WorkoutWeekdayCreate;

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
    private WorkoutWeekdayCreate[] workoutWeekdayCreateList;

}
