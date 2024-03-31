package com.mygymroutine.persistence.workout.workoutCreation;

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
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationService;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;
import com.mygymroutine.persistence.workout.WorkoutService;

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
        List<WorkoutCreation> workoutExercises = workoutCreationService.getWorkoutExerciseByWorkoutId(workoutId);

        List<WorkoutCreationResponse> workoutExerciseResponses = workoutExercises.stream()
                .map(workoutCreation -> WorkoutCreationResponse.builder()
                        .id(workoutCreation.getId())
                        .workout(new WorkoutResponse(workoutCreation.getWorkout().getWorkoutId(), 
                        		workoutCreation.getWorkout().getWorkoutName(), 
                        		workoutCreation.getWorkout().getUser().getId()))
                        .exercise(new ExerciseResponse(workoutCreation.getExercise().getExerciseId(),
                        		workoutCreation.getExercise().getExerciseName(),
                        		workoutCreation.getExercise().getInstructions(),
                        		workoutCreation.getExercise().getImg(),
                        		workoutCreation.getExercise().getIsCalistenics(),
                        		workoutCreation.getExercise().getMuscleGroup(),
                        		workoutCreation.getExercise().getUser().getId()))
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(workoutExerciseResponses, HttpStatus.OK);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Workout> createWorkout(@PathVariable Integer userId, 
            @RequestBody WorkoutCreate workoutCreate) {
        WorkoutResponse workoutResponse = workoutCreate.getWorkout();
        Exercise[] exerciseList = workoutCreate.getExerciseList();
        
        Workout workoutToCreate = Workout.builder()
                .workoutName(workoutResponse.getWorkoutName())
                .user(User.builder().id(userId).role(Role.USER).build())
                .build();

        Workout createdWorkout = workoutService.createWorkout(userId, workoutToCreate);

        for (Exercise exercise : exerciseList) {
        	WorkoutCreation workoutExercise = WorkoutCreation.builder()
                    .workout(createdWorkout)
                    .exercise(exercise)
                    .build();

        	workoutCreationService.save(workoutExercise);
        }

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

