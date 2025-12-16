package com.gymruts.data.repository.workoutCreation;

import java.util.List;

import com.gymruts.data.entity.workoutCreation.WorkoutCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutCreationRepository extends JpaRepository<WorkoutCreation, Long> {
    
    List<WorkoutCreation> findByWorkout_WorkoutId(Long workoutId);
    
    void deleteByWorkout_WorkoutId(Long workoutId);
}

