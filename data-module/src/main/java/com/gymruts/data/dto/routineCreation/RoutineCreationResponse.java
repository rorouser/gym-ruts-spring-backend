package com.gymruts.data.dto.routineCreation;


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
public class RoutineCreationResponse {
	
    private Long id;

    private WorkoutResponse workout;

    private Long routineId;
    
    private WeekDay weekday;
}
