package com.gymruts.business.controller.rest.exerciseProgress;

import java.util.List;

import com.gymruts.business.service.exerciseProgress.ExerciseProgressService;
import com.gymruts.data.dto.exerciseProgress.ExerciseProgressResponse;
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
@RequestMapping("/api/exerciseProgress")
public class ExerciseProgressController {
	
    @Autowired
    ExerciseProgressService exerciseProgressService;

    @GetMapping("/byWeekDayExerciseDetailsId/{id}")
    public ResponseEntity<List<ExerciseProgressResponse>> getExerciseProgressByWeekDayExerciseDetailsId(@PathVariable Long id) {
    	List<ExerciseProgressResponse> response = exerciseProgressService.getExerciseProgressByWeekDayExerciseDetailsId(id);
        
    	if(response.isEmpty()) {
    		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    	} else {
    		return new ResponseEntity<>(response, HttpStatus.CREATED);
    		
    	}
    }
    
    @GetMapping("/lastByWeekDayExerciseDetailsId/{id}")
    public ResponseEntity<ExerciseProgressResponse> getLastExerciseProgressByWeekDayExerciseDetailsId(@PathVariable Long id) {
        ExerciseProgressResponse response = exerciseProgressService.getLastExerciseProgressByWeekDayExerciseDetailsId(id);
        
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<ExerciseProgressResponse> createExerciseProgress(@RequestBody ExerciseProgressResponse exerciseProgress) {
    	exerciseProgressService.createExerciseProgress(exerciseProgress);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    

}
