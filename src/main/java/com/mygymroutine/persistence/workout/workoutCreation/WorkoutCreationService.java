package com.mygymroutine.persistence.workout.workoutCreation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class WorkoutCreationService {

    @Autowired
    private WorkoutCreationRepository workoutCreationRepository;

	public List<WorkoutCreation> getWorkoutExerciseByWorkoutId(Long workoutId) {
    	return workoutCreationRepository.findByWorkout_WorkoutId(workoutId);
	}

	public WorkoutCreation save(WorkoutCreation workoutExercise) {
		return workoutCreationRepository.save(workoutExercise);
		
	}
	
    @Transactional
    public void deleteByWorkoutId(Long workouId) {
    	workoutCreationRepository.deleteByWorkout_WorkoutId(workouId);
    }
    
    @Transactional
    public void deleteById(Long workoutExerciseId) {
    	workoutCreationRepository.deleteById(workoutExerciseId);
    }

}

