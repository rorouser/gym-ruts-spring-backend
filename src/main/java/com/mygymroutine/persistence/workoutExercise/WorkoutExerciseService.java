package com.mygymroutine.persistence.workoutExercise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class WorkoutExerciseService {

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

	public List<WorkoutExercise> getWorkoutExerciseByWorkoutId(Long workoutId) {
    	return workoutExerciseRepository.findByWorkout_WorkoutId(workoutId);
	}

	public WorkoutExercise save(WorkoutExercise workoutExercise) {
		return workoutExerciseRepository.save(workoutExercise);
		
	}
	
    @Transactional
    public void deleteByWorkoutId(Long workouId) {
    	workoutExerciseRepository.deleteByWorkout_WorkoutId(workouId);
    }
    
    @Transactional
    public void deleteById(Long workoutExerciseId) {
    	workoutExerciseRepository.deleteById(workoutExerciseId);
    }

}

