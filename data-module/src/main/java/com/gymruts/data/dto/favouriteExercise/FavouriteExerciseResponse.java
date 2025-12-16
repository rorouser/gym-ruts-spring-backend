package com.gymruts.data.dto.favouriteExercise;

import com.gymruts.data.dto.exercise.ExerciseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteExerciseResponse {
	
    private Long id;

    private ExerciseResponse exercise;
    
    private Integer userId;   

}
