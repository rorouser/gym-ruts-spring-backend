package com.mygymroutine.persistence.routine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineResponse {
	
    private Long routineId;

    private String routineName;

    private Integer user_id;


}
