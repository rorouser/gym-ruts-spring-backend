package com.mygymroutine.persistence.workoutExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routineWorkout.RoutineWorkoutService;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;
import com.mygymroutine.persistence.workout.WorkoutService;
import com.mygymroutine.user.Role;
import com.mygymroutine.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workoutexercises")
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;
    
    @Autowired
    private WorkoutService workoutService;
    
    @Autowired
    private RoutineWorkoutService routineWorkoutService;

    @GetMapping("/{workoutId}")
    public ResponseEntity<List<WorkoutExerciseResponse>> getWorkoutExerciseByWorkoutId(@PathVariable Long workoutId) {
        List<WorkoutExercise> workoutExercises = workoutExerciseService.getWorkoutExerciseByWorkoutId(workoutId);

        List<WorkoutExerciseResponse> workoutExerciseResponses = workoutExercises.stream()
                .map(workoutExercise -> WorkoutExerciseResponse.builder()
                        .id(workoutExercise.getId())
                        .workout(new WorkoutResponse(workoutExercise.getWorkout().getWorkoutId(), workoutExercise.getWorkout().getWorkoutName(), workoutExercise.getWorkout().getUser().getId()))
                        .exercise(workoutExercise.getExercise())
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
            WorkoutExercise workoutExercise = WorkoutExercise.builder()
                    .workout(createdWorkout)
                    .exercise(exercise)
                    .build();

            workoutExerciseService.save(workoutExercise);
        }

        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long workoutId) {
        try {
        	workoutExerciseService.deleteByWorkoutId(workoutId);
        	routineWorkoutService.deleteByWorkoutId(workoutId);
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
        	workoutExerciseService.deleteById(workoutExerciseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the workout: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

