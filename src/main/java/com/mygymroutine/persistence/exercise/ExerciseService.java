package com.mygymroutine.persistence.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }
    public Optional<Exercise> getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }

}

