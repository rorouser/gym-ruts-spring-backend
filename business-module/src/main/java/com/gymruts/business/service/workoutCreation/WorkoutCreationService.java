package com.gymruts.business.service.workoutCreation;

import com.gymruts.data.dto.exercise.ExerciseResponse;
import com.gymruts.data.dto.workout.WorkoutResponse;
import com.gymruts.data.dto.workoutCreation.WorkoutCreationResponse;
import com.gymruts.data.entity.workoutCreation.WorkoutCreation;
import com.gymruts.data.repository.workoutCreation.WorkoutCreationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutCreationService {

    @Autowired
    private WorkoutCreationRepository workoutCreationRepository;

	public List<WorkoutCreationResponse> getWorkoutExerciseByWorkoutId(Long workoutId) {
		List<WorkoutCreation> workoutExercises = workoutCreationRepository.findByWorkout_WorkoutId(workoutId);
    	return workoutExercises.stream()
                .map(workoutCreation -> WorkoutCreationResponse.builder()
                        .id(workoutCreation.getId())
                        .workout(new WorkoutResponse(workoutCreation.getWorkout().getWorkoutId(),
                        		workoutCreation.getWorkout().getWorkoutName(), 
                        		workoutCreation.getWorkout().getUser().getId()))
                        .exercise(new ExerciseResponse(workoutCreation.getExercise().getExerciseId(),
                        		workoutCreation.getExercise().getExerciseName(),
                        		workoutCreation.getExercise().getInstructions(),
                        		workoutCreation.getExercise().getImg(),
                        		workoutCreation.getExercise().getMuscleGroup(),
                        		workoutCreation.getExercise().getIsCalistenics(), 
                        		workoutCreation.getWorkout().getUser().getId()))

                        .build())
                .collect(Collectors.toList());
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

