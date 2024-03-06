package com.mygymroutine.persistence.routineWorkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mygymroutine.persistence.workoutExercise.WorkoutExercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineWorkoutRepository extends JpaRepository<RoutineWorkout, Long> {

	List<RoutineWorkout> findByRoutine_RoutineId(Long routineId);
	
	void deleteByRoutine_RoutineId(Long routineId);
	void deleteByWorkout_WorkoutId(Long workouId);

    
}

