package com.mygymroutine.persistence.routineWorkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.workoutExercise.WorkoutExercise;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineWorkoutService {

    @Autowired
    private RoutineWorkoutRepository routineWorkoutRepository;
    
    public List<RoutineWorkout> getRoutineWorkoutByRoutineId(Long routineId) {
    	return routineWorkoutRepository.findByRoutine_RoutineId(routineId);
	}
    
    public RoutineWorkout save(RoutineWorkout routineWorkout) {
    	return routineWorkoutRepository.save(routineWorkout);
    }
    
    @Transactional
    public void deleteByRoutineId(Long routineId) {
    	 routineWorkoutRepository.deleteByRoutine_RoutineId(routineId);
    }
    
    @Transactional
    public void deleteByWorkoutId(Long workoutId) {
    	 routineWorkoutRepository.deleteByWorkout_WorkoutId(workoutId);
    }
    
    @Transactional
    public void deleteById(Long routineWorkoutId) {
    	 routineWorkoutRepository.deleteById(routineWorkoutId);
    }

}

