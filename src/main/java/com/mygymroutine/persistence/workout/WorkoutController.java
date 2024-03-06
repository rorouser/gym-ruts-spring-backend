package com.mygymroutine.persistence.workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.routine.RoutineResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<WorkoutResponse>> getWorkoutById(@PathVariable Integer userId) {
        List<Workout> workouts = workoutService.getAllWorkoutsByUserId(userId);
        
        List<WorkoutResponse> workoutsResponses = workouts.stream()
                .map(workout -> WorkoutResponse.builder()
                        .workoutId(workout.getWorkoutId())
                        .workoutName(workout.getWorkoutName())
                        .user_id(workout.getUser().getId())
                        .build())
                .collect(Collectors.toList());

        return workoutsResponses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(workoutsResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@PathVariable Integer userId, @RequestBody Workout newWorkout) {
        Workout createdWorkout = workoutService.createWorkout(userId, newWorkout);
        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{workoutId}")
    public ResponseEntity<Void> deleteWorkoutById( @PathVariable Long workoutId) {
        workoutService.deleteWorkoutById(workoutId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}/{workoutId}")
    public ResponseEntity<Void> updateWorkoutById( @PathVariable Long workoutId, @RequestBody Workout updatedWorkout) {
        workoutService.updateWorkoutById(workoutId, updatedWorkout);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
