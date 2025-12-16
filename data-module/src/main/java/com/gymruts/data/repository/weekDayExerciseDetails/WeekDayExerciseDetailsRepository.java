package com.gymruts.data.repository.weekDayExerciseDetails;

import java.util.List;

import com.gymruts.data.entity.weekDayExerciseDetails.WeekDayExerciseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface WeekDayExerciseDetailsRepository extends JpaRepository<WeekDayExerciseDetails, Long> {

	void deleteById(Long id);

	List<WeekDayExerciseDetails> findByExercise_ExerciseIdAndRoutineCreation_Id(Long exerciseId, Long routineCreationId);
	
    @Query("SELECT w FROM WeekDayExerciseDetails w " +
            "JOIN w.routineCreation rc " +
            "WHERE w.exercise.exerciseId = :exerciseId " +
            "AND rc.routine.id = :routineId " +
            "AND rc.workout.id = :workoutId")
     WeekDayExerciseDetails findByExerciseIdAndRoutineIdAndWorkoutId(
         @Param("exerciseId") Long exerciseId,
         @Param("routineId") Long routineId,
         @Param("workoutId") Long workoutId
     );
    
    @Modifying
    @Transactional
    @Query("UPDATE WeekDayExerciseDetails w " +
            "SET w.series = :series, " +
            "w.reps = :reps, " +
            "w.weight = :weight " +
            "WHERE w.id = :id")
    void editFieldById(
            @Param("id") Long id,
            @Param("series") int series,
            @Param("reps") int reps,
            @Param("weight") double weight
    );

}
