	package com.gymruts.business.controller.rest.routineCreation;

import java.util.List;

import com.gymruts.business.service.routine.RoutineService;
import com.gymruts.business.service.routineCreation.RoutineCreationService;
import com.gymruts.business.service.weekDayExerciseDetails.WeekDayExerciseDetailsService;
import com.gymruts.data.dto.routine.RoutineCreate;
import com.gymruts.data.dto.routineCreation.RoutineCreationResponse;
import com.gymruts.data.dto.weekDay.WeekDayCountDTO;
import com.gymruts.data.entity.routine.Routine;
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
@RequestMapping("/api/routineworkouts")
public class RoutineCreationController {

    @Autowired
    private RoutineCreationService routineCreationService;
    
    @Autowired
    private RoutineService routineService;
    
    @Autowired
    private WeekDayExerciseDetailsService weekDayExerciseDetailsService;

    @GetMapping("/{routineId}")
    public ResponseEntity<List<RoutineCreationResponse>> getRoutineWorkoutByRoutineId(@PathVariable Long routineId) {
        List<RoutineCreationResponse> routineCreations = routineCreationService.getRoutineWorkoutByRoutineId(routineId);

    	if(!routineCreations.isEmpty()) {
    		return ResponseEntity.ok(routineCreations);
    	} else {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    }
    @GetMapping("/one/{routineId}")
    public ResponseEntity<RoutineCreationResponse> getRoutineCreationById(@PathVariable Long routineId) {
        RoutineCreationResponse routineCreations = routineCreationService.getRoutineCreationById(routineId);

    	if(routineCreations!= null) {
    		return ResponseEntity.ok(routineCreations);
    	} else {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping("/countByWeekday")
    public ResponseEntity<List<WeekDayCountDTO>> countByWeekday() {
    	List<WeekDayCountDTO> routineCreations = routineCreationService.getCountByWeekday();
        return ResponseEntity.ok(routineCreations);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Routine> createRoutine(@PathVariable Integer userId,
                                                 @RequestBody RoutineCreate routineCreate) {

        Routine createdRoutine = routineService.createRoutine(userId, routineCreate);
        
        if(createdRoutine!=null) {
        	return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
        } else {
        	return new ResponseEntity<>(createdRoutine, HttpStatus.NOT_FOUND);
        }

        
    }


    @DeleteMapping("/{routineId}")
    public ResponseEntity<?> deleteRoutine(@PathVariable Long routineId) {
        try {
        	routineCreationService.deleteByRoutineId(routineId);
            routineService.deleteRoutineById(routineId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the routine: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
	
    @DeleteMapping("/workout/{routineWorkoutId}")
    public ResponseEntity<?> deleteWorkoutofRoutine(@PathVariable Long routineWorkoutId) {
        try {
        	routineCreationService.deleteById(routineWorkoutId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the workout: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

