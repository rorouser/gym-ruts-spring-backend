package com.gymruts.business.controller.rest.favouriteExercise;

import java.util.List;
import java.util.stream.Collectors;

import com.gymruts.business.service.favouriteExercise.FavouriteExerciseService;
import com.gymruts.data.dto.favouriteExercise.FavouriteExerciseResponse;
import com.gymruts.data.entity.exercise.Exercise;
import com.gymruts.data.entity.favouriteExercise.FavouriteExercise;
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

@RestController
@RequestMapping("/api/favouritexercises")
public class FavouriteExerciseController {
	
	@Autowired
	private FavouriteExerciseService favouriteExerciseService;

	@GetMapping("/{userId}")
	public ResponseEntity<List<FavouriteExerciseResponse>> getFavouriteExercisesByUserId(@PathVariable Integer userId) {
	    List<FavouriteExerciseResponse> favouriteExercises = favouriteExerciseService.findAllByUserId(userId);

    	return ResponseEntity.ok(favouriteExercises);
	    
	}

	@DeleteMapping("/{id}/{userId}")
	public ResponseEntity<?> deleteFavouriteExercisesByUserId(@PathVariable Long id, @PathVariable Integer userId) {
	    try {
	        favouriteExerciseService.deleteById(userId, id);
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
