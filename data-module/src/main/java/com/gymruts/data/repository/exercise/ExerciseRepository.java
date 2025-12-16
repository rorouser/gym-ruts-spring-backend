package com.gymruts.data.repository.exercise;

import java.util.List;

import com.gymruts.data.entity.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

	List<Exercise> findAllByUserIdIn(List<Integer> userIds);
	
    void deleteById(Long exerciseId);
    
    default void updateById(Long exerciseId, Exercise updatedExercise) {
    	Exercise existingExercise = findById(exerciseId).orElse(null);
        if (existingExercise != null) {
        	existingExercise.setExerciseName(updatedExercise.getExerciseName());
        	existingExercise.setImg(updatedExercise.getImg());
        	existingExercise.setInstructions(updatedExercise.getInstructions());
        	existingExercise.setIsCalistenics(updatedExercise.getIsCalistenics());
        	existingExercise.setMuscleGroup(updatedExercise.getMuscleGroup());
            save(existingExercise);
        }
    }
}

