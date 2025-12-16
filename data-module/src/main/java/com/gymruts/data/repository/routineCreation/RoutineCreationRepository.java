package com.gymruts.data.repository.routineCreation;

import java.util.List;

import com.gymruts.data.entity.routineCreation.RoutineCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineCreationRepository extends JpaRepository<RoutineCreation, Long> {

	List<RoutineCreation> findByRoutine_RoutineId(Long routineId);
	
	void deleteByRoutine_RoutineId(Long routineId);
	
	void deleteByWorkout_WorkoutId(Long workouId);
	
	@Query("SELECT r.weekday, COUNT(r.id) FROM RoutineCreation r GROUP BY r.weekday")
	List<Object[]> countByWeekday();


    
}

