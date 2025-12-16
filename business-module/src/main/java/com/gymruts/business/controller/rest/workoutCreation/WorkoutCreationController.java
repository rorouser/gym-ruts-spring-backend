package com.gymruts.business.controller.rest.workoutCreation;

import java.util.List;

import com.gymruts.business.service.routineCreation.RoutineCreationService;
import com.gymruts.business.service.workout.WorkoutService;
import com.gymruts.business.service.workoutCreation.WorkoutCreationService;
import com.gymruts.data.dto.workout.WorkoutCreate;
import com.gymruts.data.dto.workoutCreation.WorkoutCreationResponse;
import com.gymruts.data.entity.workout.Workout;
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
@RequestMapping("/api/workoutexercises")
public class WorkoutCreationController {

    @Autowired
    private WorkoutCreationService workoutCreationService;
    
    @Autowired
    private WorkoutService workoutService;
    
    @Autowired
    private RoutineCreationService routineCreationService;

    @GetMapping("/{workoutId}")
    public ResponseEntity<List<WorkoutCreationResponse>> getWorkoutExerciseByWorkoutId(@PathVariable Long workoutId) {
        List<WorkoutCreationResponse> workoutExercises = workoutCreationService.getWorkoutExerciseByWorkoutId(workoutId);
        
        return workoutExercises.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(workoutExercises, HttpStatus.OK);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Workout> createWorkout(@PathVariable Integer userId,
                                                 @RequestBody WorkoutCreate workoutCreate) {
    	Workout createdWorkout = workoutService.createWorkout(userId, workoutCreate);

        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long workoutId) {
        try {
        	workoutCreationService.deleteByWorkoutId(workoutId);
        	routineCreationService.deleteByWorkoutId(workoutId);
        	workoutService.deleteWorkoutById(workoutId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the workout: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/exercise/{workoutExerciseId}")
    public ResponseEntity<?> deleteExerciseofWorkout(@PathVariable Long workoutExerciseId) {
        try {
        	workoutCreationService.deleteById(workoutExerciseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the workout: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

