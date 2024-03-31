package com.mygymroutine.persistence.exercise.favouriteExercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.user.User;

@Service
public class FavouriteExerciseService {
	
	@Autowired
	private FavouriteExerciseRepository favouriteExerciseRepository;
	
    public List<FavouriteExercise> findAllByUserId(Integer userId) {
        return favouriteExerciseRepository.findAllByUser_Id(userId);
    }

    public void deleteById(Long userId) {
        favouriteExerciseRepository.deleteById(userId);
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
