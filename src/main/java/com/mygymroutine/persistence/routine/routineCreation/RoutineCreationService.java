package com.mygymroutine.persistence.routine.routineCreation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.workout.WorkoutResponse;

import jakarta.transaction.Transactional;

@Service
public class RoutineCreationService {

    @Autowired
    private RoutineCreationRepository routineCreationRepository;
    
    public List<RoutineCreationResponse> getRoutineWorkoutByRoutineId(Long routineId) {
    	
    	List<RoutineCreation> routineCreations = routineCreationRepository.findByRoutine_RoutineId(routineId);
    	
    	if(!routineCreations.isEmpty()) {
            return routineCreations.stream()
                    .map(routineCreation -> RoutineCreationResponse.builder()
                            .id(routineCreation.getId())
                            .workout(new WorkoutResponse(routineCreation.getWorkout().getWorkoutId(), routineCreation.getWorkout().getWorkoutName(), routineCreation.getWorkout().getUser().getId()))
                            .routineId(routineCreation.getRoutine().getRoutineId())
                            .weekday(routineCreation.getWeekday())
                            .build())
                    .collect(Collectors.toList());
    	} else {
    		return null;
    	}
    	
    	
	}
    
    public RoutineCreation save(RoutineCreation routineCreation) {
    	return routineCreationRepository.save(routineCreation);
    }
    
    @Transactional
    public void deleteByRoutineId(Long routineId) {
    	routineCreationRepository.deleteByRoutine_RoutineId(routineId);
    }
    
    @Transactional
    public void deleteByWorkoutId(Long workoutId) {
    	routineCreationRepository.deleteByWorkout_WorkoutId(workoutId);
    }
    
    @Transactional
    public void deleteById(Long routineWorkoutId) {
    	routineCreationRepository.deleteById(routineWorkoutId);
    }

}

