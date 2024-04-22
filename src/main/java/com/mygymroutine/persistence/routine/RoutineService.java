package com.mygymroutine.persistence.routine;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.routine.routineCreation.RoutineCreation;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationService;
import com.mygymroutine.persistence.routine.routineCreation.WorkoutWeekdayCreate;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.Workout;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;
    
    @Autowired
    private RoutineCreationService routineCreationService;

    public List<RoutineResponse> getAllRoutinesByUserId(Integer userId) {
    	List<Routine> routines = routineRepository.findAllByUser_Id(userId);
        return routines.stream()
                .map(routine -> RoutineResponse.builder()
                        .routineId(routine.getRoutineId())
                        .routineName(routine.getRoutineName())
                        .user_id(routine.getUser().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<RoutineResponse> getRoutineById(Long routineId) {
        Optional<Routine> routineOptional = routineRepository.findById(routineId);
        return routineOptional.map(routine -> {
            RoutineResponse routineResponse = RoutineResponse.builder()
                    .routineId(routine.getRoutineId())
                    .routineName(routine.getRoutineName())
                    .user_id(routine.getUser().getId())
                    .build();
            return routineResponse;
        });
    }

    
    public Routine createRoutine(Integer userId, RoutineCreate routineCreate) {
    	
    	RoutineResponse routineResponse = routineCreate.getRoutine();
        WorkoutWeekdayCreate[] workoutWeekdayCreateList = routineCreate.getWorkoutWeekdayCreateList();
        
        Routine routineToCreate = responseToRoutine(userId, routineResponse);
        
        Routine createdRoutine = routineRepository.save(routineToCreate);

        responseToRoutineWorkout(userId, workoutWeekdayCreateList, createdRoutine);

        return createdRoutine;
    }
    
	private void responseToRoutineWorkout(Integer userId, WorkoutWeekdayCreate[] workoutWeekdayCreateList,
			Routine routine) {
		
		for (WorkoutWeekdayCreate workoutWeekdayCreate : workoutWeekdayCreateList) {
			
            Workout workoutToCreate = Workout.builder()
            		.workoutId(workoutWeekdayCreate.getWorkout().getWorkoutId())
                    .workoutName(workoutWeekdayCreate.getWorkout().getWorkoutName())
                    .user(User.builder().id(userId).role(Role.USER).build())
                    .build();

					
            RoutineCreation routineWorkout = RoutineCreation.builder()
                    .routine(routine)
                    .workout(workoutToCreate)
                    .weekday(workoutWeekdayCreate.getWeekDay())
                    .build();
            routineCreationService.save(routineWorkout);
       
        }
	}

	private Routine responseToRoutine(Integer userId, RoutineResponse routineResponse) {
		Routine routineToCreate = Routine.builder()
				.routineId(routineResponse.getRoutineId())
                .routineName(routineResponse.getRoutineName())
                .user(User.builder()
                		.id(userId)
                		.role(Role.USER)
                		.build())
                .build();
		return routineToCreate;
	}

    public void deleteRoutineById(Long routineId) {
        routineRepository.deleteById(routineId);
    }

    public void updateRoutineById(Long routineId, Routine updatedRoutine) {
        routineRepository.updateById(routineId, updatedRoutine);
    }
}

