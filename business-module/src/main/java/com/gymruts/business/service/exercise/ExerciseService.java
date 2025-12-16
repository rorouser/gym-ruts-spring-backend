package com.gymruts.business.service.exercise;

import com.gymruts.data.dto.exercise.ExerciseResponse;
import com.gymruts.data.entity.exercise.Exercise;
import com.gymruts.data.enums.Role;
import com.gymruts.data.entity.user.User;
import com.gymruts.data.repository.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    
    public Optional<Exercise> getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }
    
    public List<ExerciseResponse> getAllExercisesByUsers(Integer user) {
    	List<Integer> userIds = new ArrayList<>();
        userIds.add(1);
        userIds.add(user);
        
    	List<Exercise> exercises = exerciseRepository.findAllByUserIdIn(userIds);
    	
        return exercises.stream()
        		.map(exercise -> ExerciseResponse.builder()
        				.exerciseId(exercise.getExerciseId())
        				.exerciseName(exercise.getExerciseName())
        				.instructions(exercise.getInstructions())
        				.img(exercise.getImg())
        				.isCalistenics(exercise.getIsCalistenics())
        				.muscleGroup(exercise.getMuscleGroup())
        				.userId(exercise.getUser().getId())
        				.build())
        		.collect(Collectors.toList());
    }
    
    public Exercise createExercise(Integer userId, ExerciseResponse exerciseResponse) {
    	
        Exercise exerciseToCreate = Exercise.builder()
				.exerciseId(exerciseResponse.getExerciseId())
				.exerciseName(exerciseResponse.getExerciseName())
				.instructions(exerciseResponse.getInstructions())
				.img(exerciseResponse.getImg())
				.isCalistenics(exerciseResponse.getIsCalistenics())
				.muscleGroup(exerciseResponse.getMuscleGroup())
                .user(User.builder().id(userId).role(Role.USER).build())
                .build();
        
        Exercise createdExercise = exerciseRepository.save(exerciseToCreate);
        
        if(createdExercise!=null) {
        	return createdExercise;
        } else {
        	return null;
        }
        
    }
}

