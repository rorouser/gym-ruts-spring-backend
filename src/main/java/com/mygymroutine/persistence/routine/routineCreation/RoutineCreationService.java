package com.mygymroutine.persistence.routine.routineCreation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RoutineCreationService {

    @Autowired
    private RoutineCreationRepository routineCreationRepository;
    
    public List<RoutineCreation> getRoutineWorkoutByRoutineId(Long routineId) {
    	return routineCreationRepository.findByRoutine_RoutineId(routineId);
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

