package com.gymruts.data.dto.routine;

import com.gymruts.data.dto.weekDay.WorkoutWeekdayCreate;
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
