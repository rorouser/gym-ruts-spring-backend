package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import java.util.List;

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
@RequestMapping("/api/weekDayExerciseDetails")
public class WeekDayExerciseDetailsController {
	
    @Autowired
    private WeekDayExerciseDetailsService weekDayExerciseDetailsService;

    @GetMapping("/{exerciseId}/{routineCreationId}")
    public ResponseEntity<List<WeekDayExerciseDetailsResponse>> getExerciseDetailsByExerciseIdAndRoutineCreationId(
            @PathVariable Long exerciseId, @PathVariable Long routineCreationId) {
        List<WeekDayExerciseDetailsResponse> detailsList = weekDayExerciseDetailsService.findByExerciseIdAndRoutineWorkoutId(exerciseId, routineCreationId);
        if (detailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }
    
    @GetMapping("/{exerciseId}/{routineId}/{workoutId}")
    public ResponseEntity<List<WeekDayExerciseDetailsResponse>> getExerciseDetailsByExerciseIdAndRoutineCreationId(
            @PathVariable Long exerciseId, @PathVariable Long routineId, @PathVariable Long workoutId) {
        List<WeekDayExerciseDetailsResponse> detailsList = weekDayExerciseDetailsService.findByWorkoutIdAndRoutineId(exerciseId, routineId, workoutId);
        if (detailsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<WeekDayExerciseDetails> createWeekDayExerciseDetails(@RequestBody WeekDayExerciseDetailsResponse details) {
    	WeekDayExerciseDetails createdDetails = weekDayExerciseDetailsService.create(details);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    }
}
