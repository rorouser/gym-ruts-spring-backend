package com.gymruts.business.controller.rest.routine;

import com.gymruts.business.service.routine.RoutineService;
import com.gymruts.data.dto.routine.RoutineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    @Autowired
    private RoutineService routineService;
    
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<RoutineResponse>> getAllRoutines(@PathVariable Integer userId) {
        List<RoutineResponse> routines = routineService.getAllRoutinesByUserId(userId);

        return routines.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(routines, HttpStatus.OK);
    }

    
    @GetMapping("/{routineId}")
    public ResponseEntity<Optional<RoutineResponse>> getRoutineById(@PathVariable Long routineId) {
    	Optional<RoutineResponse> routineResponse = routineService.getRoutineById(routineId);

    	return routineResponse.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(routineResponse, HttpStatus.OK);
    }



}

