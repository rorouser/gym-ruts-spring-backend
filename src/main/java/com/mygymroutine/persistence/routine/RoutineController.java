package com.mygymroutine.persistence.routine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    @Autowired
    private RoutineService routineService;
    
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<RoutineResponse>> getAllRoutines(@PathVariable Integer userId) {
        List<Routine> routines = routineService.getAllRoutinesByUserId(userId);

        List<RoutineResponse> routineResponses = routines.stream()
                .map(routine -> RoutineResponse.builder()
                        .routineId(routine.getRoutineId())
                        .routineName(routine.getRoutineName())
                        .user_id(routine.getUser().getId())
                        .build())
                .collect(Collectors.toList());

        return routineResponses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(routineResponses, HttpStatus.OK);
    }

    
    @GetMapping("/{routineId}")
    public ResponseEntity<RoutineResponse> getRoutineById(@PathVariable Long routineId) {
        Optional<Routine> routineOptional = routineService.getRoutineById(routineId);

        ResponseEntity<RoutineResponse> result = routineOptional
                .map(routine -> {
                    RoutineResponse routineResponse = RoutineResponse.builder()
                            .routineId(routine.getRoutineId())
                            .routineName(routine.getRoutineName())
                            .user_id(routine.getUser().getId())
                            .build();
                    return new ResponseEntity<>(routineResponse, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        return result;
    }



}

