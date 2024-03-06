package com.mygymroutine.persistence.workoutExercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    
    List<WorkoutExercise> findByWorkout_WorkoutId(Long workoutId);
    
    void deleteByWorkout_WorkoutId(Long workoutId);
}

