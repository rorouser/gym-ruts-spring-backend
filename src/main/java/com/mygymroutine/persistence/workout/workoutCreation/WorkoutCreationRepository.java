package com.mygymroutine.persistence.workout.workoutCreation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutCreationRepository extends JpaRepository<WorkoutCreation, Long> {
    
    List<WorkoutCreation> findByWorkout_WorkoutId(Long workoutId);
    
    void deleteByWorkout_WorkoutId(Long workoutId);
}

