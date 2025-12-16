package com.gymruts.data.dto.exercise;

import com.gymruts.data.enums.IsCalistenics;
import com.gymruts.data.enums.MuscleGroup;
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
    
    private MuscleGroup muscleGroup;
    
    private IsCalistenics isCalistenics;

	private Integer userId;

}
