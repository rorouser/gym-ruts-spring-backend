package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayExerciseDetailsRepository extends JpaRepository<WeekDayExerciseDetails, Long> {

	void deleteById(Long id);

	List<WeekDayExerciseDetails> findByExercise_ExerciseIdAndRoutineCreation_Id(Long exerciseId, Long routineCreationId);
	
    @Query("SELECT w FROM WeekDayExerciseDetails w " +
            "JOIN w.routineCreation rc " +
            "WHERE w.exercise.exerciseId = :exerciseId " +
            "AND rc.routine.id = :routineId " +
            "AND rc.workout.id = :workoutId")
     List<WeekDayExerciseDetails> findByExerciseIdAndRoutineIdAndWorkoutId(
         @Param("exerciseId") Long exerciseId,
         @Param("routineId") Long routineId,
         @Param("workoutId") Long workoutId
     );

}
