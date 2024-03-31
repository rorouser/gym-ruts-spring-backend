package com.mygymroutine.persistence.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.Workout;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    
    public Optional<Exercise> getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }
    
    public List<Exercise> getAllExercisesByUsers(List<Integer> userIds) {
        return exerciseRepository.findAllByUserIdIn(userIds);
    }
    
    public Exercise createExercise(Exercise newExercise) {
        Exercise createdExercise = exerciseRepository.save(newExercise);
        return createdExercise;
    }
}

