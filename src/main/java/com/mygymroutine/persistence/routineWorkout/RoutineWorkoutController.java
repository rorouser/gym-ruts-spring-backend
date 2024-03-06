package com.mygymroutine.persistence.routineWorkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.routine.RoutineCreate;
import com.mygymroutine.persistence.routine.RoutineResponse;
import com.mygymroutine.persistence.routine.RoutineService;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;
import com.mygymroutine.persistence.workoutExercise.WorkoutExercise;
import com.mygymroutine.persistence.workoutExercise.WorkoutExerciseResponse;
import com.mygymroutine.user.Role;
import com.mygymroutine.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/routineworkouts")
public class RoutineWorkoutController {

    @Autowired
    private RoutineWorkoutService routineWorkoutService;
    
    @Autowired
    private RoutineService routineService;

    @GetMapping("/{routineId}")
    public ResponseEntity<List<RoutineWorkoutResponse>> getWorkoutExerciseByWorkoutId(@PathVariable Long routineId) {
        List<RoutineWorkout> routineWorkouts = routineWorkoutService.getRoutineWorkoutByRoutineId(routineId);

        List<RoutineWorkoutResponse> routineWorkoutResponses = routineWorkouts.stream()
                .map(routineWorkout -> RoutineWorkoutResponse.builder()
                        .id(routineWorkout.getId())
                        .workout(new WorkoutResponse(routineWorkout.getWorkout().getWorkoutId(), routineWorkout.getWorkout().getWorkoutName(), routineWorkout.getWorkout().getUser().getId()))
                        .routineId(routineWorkout.getRoutine().getRoutineId())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(routineWorkoutResponses, HttpStatus.OK);
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<Routine> createWorkout(@PathVariable Integer userId, 
            @RequestBody RoutineCreate routineCreate) {
        RoutineResponse routineResponse = routineCreate.getRoutine();
        WorkoutResponse[] workoutResponseList = routineCreate.getWorkoutResponseList();
        
        Routine routineToCreate = responseToRoutine(userId, routineResponse);

        Routine createdRoutine = routineService.createRoutine(userId, routineToCreate);

        responseToRoutineWorkout(userId, workoutResponseList, createdRoutine);

        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }


    @DeleteMapping("/{routineId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long routineId) {
        try {
            routineWorkoutService.deleteByRoutineId(routineId);
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

            RoutineWorkout routineWorkout = RoutineWorkout.builder()
                    .routine(routine)
                    .workout(workoutToCreate)
                    .build();
            routineWorkoutService.save(routineWorkout);

            
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
        	routineWorkoutService.deleteById(routineWorkoutId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Error deleting the workout: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

