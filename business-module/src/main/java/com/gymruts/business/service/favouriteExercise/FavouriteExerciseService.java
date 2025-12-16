package com.gymruts.business.service.favouriteExercise;

import java.util.List;
import java.util.stream.Collectors;

import com.gymruts.data.dto.exercise.ExerciseResponse;
import com.gymruts.data.dto.favouriteExercise.FavouriteExerciseResponse;
import com.gymruts.data.entity.exercise.Exercise;
import com.gymruts.data.entity.favouriteExercise.FavouriteExercise;
import com.gymruts.data.entity.user.User;
import com.gymruts.data.repository.favouriteExercise.FavouriteExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteExerciseService {
	
	@Autowired
	private FavouriteExerciseRepository favouriteExerciseRepository;
	
    public List<FavouriteExerciseResponse> findAllByUserId(Integer userId) {
    	 List<FavouriteExercise> favouriteExercises = favouriteExerciseRepository.findAllByUser_Id(userId);
    	   	 List<FavouriteExerciseResponse> favouriteExerciseResponses = favouriteExercises.stream()
 		            .map(favouriteExercise -> {
 		                return FavouriteExerciseResponse.builder()
 		                        .id(favouriteExercise.getId())
 		                        .exercise(new ExerciseResponse(favouriteExercise.getExercise().getExerciseId(),
 		                        		favouriteExercise.getExercise().getExerciseName(),
 		                        		favouriteExercise.getExercise().getInstructions(),
 		                        		favouriteExercise.getExercise().getImg(),
 		                        		favouriteExercise.getExercise().getMuscleGroup(),
 		                        		favouriteExercise.getExercise().getIsCalistenics(),
 		                        		favouriteExercise.getExercise().getUser().getId()))
 		                        .userId(favouriteExercise.getUser().getId())
 		                        .build();
 		            })
 		            .collect(Collectors.toList());
    	   	 return favouriteExerciseResponses;

    }

    public void deleteById(Integer userId, Long exerciseId) {
        favouriteExerciseRepository.deleteByUserIdAndExerciseId(userId, exerciseId);
    }
    
    public FavouriteExercise createFavouriteExercise(Integer userId, Exercise exercise) {
    	User user = new User();
    	user.setId(userId);
    	FavouriteExercise favouriteExercise = new FavouriteExercise();
    	favouriteExercise.setUser(user);
    	favouriteExercise.setExercise(exercise);
        return favouriteExerciseRepository.save(favouriteExercise);
    }
}
