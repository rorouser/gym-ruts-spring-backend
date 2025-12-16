package com.gymruts.data.dto.weekDay;


import com.gymruts.data.dto.workout.WorkoutResponse;
import com.gymruts.data.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutWeekdayCreate {
	
	private WorkoutResponse workout;
	
	private WeekDay weekDay;

}
