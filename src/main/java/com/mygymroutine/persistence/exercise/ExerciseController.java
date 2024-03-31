package com.mygymroutine.persistence.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreation;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseService.getExerciseById(exerciseId);

        return exerciseOptional
                .map(exercise -> new ResponseEntity<>(exercise, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/all/{user}")
    public ResponseEntity<List<ExerciseResponse>> getAllExercisesByUsers(@PathVariable Integer user) {
    	List<Integer> userIds = new ArrayList<>();
        userIds.add(1);
        userIds.add(user);
        
        List<Exercise> exercises = exerciseService.getAllExercisesByUsers(userIds);
        
        List<ExerciseResponse> exercisesResponses = exercises.stream()
        		.map(exercise -> ExerciseResponse.builder()
        				.exerciseId(exercise.getExerciseId())
        				.exerciseName(exercise.getExerciseName())
        				.instructions(exercise.getInstructions())
        				.img(exercise.getImg())
        				.isCalistenics(exercise.getIsCalistenics())
        				.muscleGroup(exercise.getMuscleGroup())
        				.userId(exercise.getUser().getId())
        				.build())
        		.collect(Collectors.toList());

        return exercisesResponses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(exercisesResponses, HttpStatus.OK);
    }

    
    @PostMapping("/{userId}")
    public ResponseEntity<Exercise> createExercise(@PathVariable Integer userId, 
            @RequestBody ExerciseResponse exerciseResponse) {
        
        Exercise exerciseToCreate = Exercise.builder()
				.exerciseId(exerciseResponse.getExerciseId())
				.exerciseName(exerciseResponse.getExerciseName())
				.instructions(exerciseResponse.getInstructions())
				.img(exerciseResponse.getImg())
				.isCalistenics(exerciseResponse.getIsCalistenics())
				.muscleGroup(exerciseResponse.getMuscleGroup())
                .user(User.builder().id(userId).role(Role.USER).build())
                .build();

        Exercise createdExercise = exerciseService.createExercise(exerciseToCreate);


        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }


}

