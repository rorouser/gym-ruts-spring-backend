package com.mygymroutine.persistence.exercise.favouriteExercise;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.exercise.ExerciseResponse;

@RestController
@RequestMapping("/api/favouritexercises")
public class FavouriteExerciseController {
	
	@Autowired
	private FavouriteExerciseService favouriteExerciseService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<FavouriteExerciseResponse>> getFavouriteExercisesByUserId(@PathVariable Integer userId) {
	    List<FavouriteExercise> favouriteExercises = favouriteExerciseService.findAllByUserId(userId);

	    List<FavouriteExerciseResponse> favouriteExerciseResponses = favouriteExercises.stream()
	            .map(favouriteExercise -> {
	                return FavouriteExerciseResponse.builder()
	                        .id(favouriteExercise.getId())
	                        .exercise(new ExerciseResponse(favouriteExercise.getExercise().getExerciseId(),
	                        		favouriteExercise.getExercise().getExerciseName(),
	                        		favouriteExercise.getExercise().getInstructions(),
	                        		favouriteExercise.getExercise().getImg(),
	                        		favouriteExercise.getExercise().getIsCalistenics(),
	                        		favouriteExercise.getExercise().getMuscleGroup(),
	                        		favouriteExercise.getExercise().getUser().getId()))
	                        .userId(favouriteExercise.getUser().getId())
	                        .build();
	            })
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(favouriteExerciseResponses);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteFavouriteExercisesByUserId(@PathVariable Long id) {
	    try {
	        favouriteExerciseService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } catch (Exception e) {
	    	String errorMessage = "Error deleting the routine: " + e.getMessage();
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
    
	@PostMapping("/{userId}")
	public  ResponseEntity<?> createFavouriteExercise(@PathVariable Integer userId, @RequestBody Exercise exercise) {
		FavouriteExercise createdWorkout = favouriteExerciseService.createFavouriteExercise(userId, exercise);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
		
	}
}
