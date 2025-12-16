package com.gymruts.business.controller.rest.workout;

import com.gymruts.business.service.workout.WorkoutService;
import com.gymruts.data.dto.workout.WorkoutResponse;
import com.gymruts.data.entity.workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<WorkoutResponse>> getWorkoutById(@PathVariable Integer userId) {
        List<WorkoutResponse> workouts = workoutService.getAllWorkoutsByUserId(userId);

        return workouts.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(workouts, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Workout> createWorkout(@PathVariable Integer userId, @RequestBody Workout newWorkout) {
//        Workout createdWorkout = workoutService.createWorkout(userId, newWorkout);
//        return new ResponseEntity<>(createdWorkout, HttpStatus.CREATED);
//    }

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
