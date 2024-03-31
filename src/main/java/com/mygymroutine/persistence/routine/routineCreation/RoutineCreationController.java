	package com.mygymroutine.persistence.routine.routineCreation;

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

import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.routine.RoutineCreate;
import com.mygymroutine.persistence.routine.RoutineResponse;
import com.mygymroutine.persistence.routine.RoutineService;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutResponse;

@RestController
@RequestMapping("/api/routineworkouts")
public class RoutineCreationController {

    @Autowired
    private RoutineCreationService routineCreationService;
    
    @Autowired
    private RoutineService routineService;

    @GetMapping("/{routineId}")
    public ResponseEntity<List<RoutineCreationResponse>> getWorkoutExerciseByWorkoutId(@PathVariable Long routineId) {
        List<RoutineCreation> routineCreations = routineCreationService.getRoutineWorkoutByRoutineId(routineId);

        List<RoutineCreationResponse> routineWorkoutResponses = routineCreations.stream()
                .map(routineCreation -> RoutineCreationResponse.builder()
                        .id(routineCreation.getId())
                        .workout(new WorkoutResponse(routineCreation.getWorkout().getWorkoutId(), routineCreation.getWorkout().getWorkoutName(), routineCreation.getWorkout().getUser().getId()))
                        .routineId(routineCreation.getRoutine().getRoutineId())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(routineWorkoutResponses, HttpStatus.OK);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Routine> createRoutine(@PathVariable Integer userId, 
            @RequestBody RoutineCreate routineCreate) {
        RoutineResponse routineResponse = routineCreate.getRoutine();
        WorkoutResponse[] workoutResponseList = routineCreate.getWorkoutResponseList();
        
        Routine routineToCreate = responseToRoutine(userId, routineResponse);

        Routine createdRoutine = routineService.createRoutine(userId, routineToCreate);

        responseToRoutineWorkout(userId, workoutResponseList, createdRoutine);

        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
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

    
	private void responseToRoutineWorkout(Integer userId, WorkoutResponse[] workoutResponseList,
			Routine routine) {
		for (WorkoutResponse workoutResponse : workoutResponseList) {
            Workout workoutToCreate = Workout.builder()
            		.workoutId(workoutResponse.getWorkoutId())
                    .workoutName(workoutResponse.getWorkoutName())
                    .user(User.builder().id(userId).role(Role.USER).build())
                    .build();

            RoutineCreation routineWorkout = RoutineCreation.builder()
                    .routine(routine)
                    .workout(workoutToCreate)
                    .build();
            routineCreationService.save(routineWorkout);

            
        }
	}

	private Routine responseToRoutine(Integer userId, RoutineResponse routineResponse) {
		Routine routineToCreate = Routine.builder()
				.routineId(routineResponse.getRoutineId())
                .routineName(routineResponse.getRoutineName())
                .user(User.builder().id(userId).role(Role.USER).build())
                .build();
		return routineToCreate;
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

