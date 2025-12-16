package com.gymruts.business.controller.rest.weekDayExerciseDetails;

import java.util.List;

import com.gymruts.business.service.weekDayExerciseDetails.WeekDayExerciseDetailsService;
import com.gymruts.data.dto.weekDayExerciseDetails.WeekDayExerciseDetailsResponse;
import com.gymruts.data.entity.weekDayExerciseDetails.WeekDayExerciseDetails;
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
    public ResponseEntity<WeekDayExerciseDetailsResponse> getExerciseDetailsByExerciseIdAndRoutineCreationId(
            @PathVariable Long exerciseId, @PathVariable Long routineId, @PathVariable Long workoutId) {
        WeekDayExerciseDetailsResponse detailsList = weekDayExerciseDetailsService.findByWorkoutIdAndRoutineId(exerciseId, routineId, workoutId);
        if (detailsList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<WeekDayExerciseDetails> createWeekDayExerciseDetails(@RequestBody WeekDayExerciseDetailsResponse details) {
    	WeekDayExerciseDetails createdDetails = weekDayExerciseDetailsService.create(details);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<WeekDayExerciseDetails> updateWeekDayExerciseDetails(@RequestBody WeekDayExerciseDetailsResponse details) {
    	weekDayExerciseDetailsService.update(details.getId(), details.getSeries(), details.getReps(), details.getWeight());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
