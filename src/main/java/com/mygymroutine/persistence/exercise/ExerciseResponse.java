package com.mygymroutine.persistence.exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponse {
	
    private Long exerciseId;

    private String exerciseName;
    
    private String instructions;
    
    private String img;
    
    private String isCalistenics;
    
    private String muscleGroup;

	private Integer userId;

}
