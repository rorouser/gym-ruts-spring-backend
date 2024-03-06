package com.mygymroutine.persistence.workout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mygymroutine.persistence.routine.Routine;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    void deleteById(Long workoutId);

    default void updateById(Long workoutId, Workout updatedWorkout) {
        Workout existingWorkout = findById(workoutId).orElse(null);
        if (existingWorkout != null) {
            existingWorkout.setWorkoutName(updatedWorkout.getWorkoutName());
            save(existingWorkout);
        }
    }
    
    List<Workout> findAllByUser_Id(Integer userId);
}

