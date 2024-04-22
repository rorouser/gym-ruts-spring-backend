package com.mygymroutine.persistence.exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    //Hacer la conversion para que funcione
    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseService.getExerciseById(exerciseId);

        return exerciseOptional
                .map(exercise -> new ResponseEntity<>(exercise, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/all/{user}")
    public ResponseEntity<List<ExerciseResponse>> getAllExercisesByUsers(@PathVariable Integer user) {
        
        List<ExerciseResponse> exercises = exerciseService.getAllExercisesByUsers(user);

        if (exercises.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    
    @PostMapping("/{userId}")
    public ResponseEntity<Exercise> createExercise(@PathVariable Integer userId, 
            @RequestBody ExerciseResponse exerciseResponse) {

        Exercise createdExercise = exerciseService.createExercise(userId, exerciseResponse);
        
        if (createdExercise!=null)
        	return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
    }


}

