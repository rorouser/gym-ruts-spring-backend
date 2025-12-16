package com.gymruts.data.repository.workout;

import com.gymruts.data.entity.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

