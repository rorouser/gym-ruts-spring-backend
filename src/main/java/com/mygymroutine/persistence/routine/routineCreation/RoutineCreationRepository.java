package com.mygymroutine.persistence.routine.routineCreation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineCreationRepository extends JpaRepository<RoutineCreation, Long> {

	List<RoutineCreation> findByRoutine_RoutineId(Long routineId);
	
	void deleteByRoutine_RoutineId(Long routineId);
	void deleteByWorkout_WorkoutId(Long workouId);

    
}

