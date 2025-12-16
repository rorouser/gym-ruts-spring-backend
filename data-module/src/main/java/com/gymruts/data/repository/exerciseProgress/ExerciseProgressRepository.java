package com.gymruts.data.repository.exerciseProgress;

import java.util.List;
import java.util.Optional;

import com.gymruts.data.entity.exerciseProgress.ExerciseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseProgressRepository extends JpaRepository<ExerciseProgress, Long>{
	
	List<ExerciseProgress> findByWeekDayExerciseDetailsId(Long weekDayExerciseDetailsId);
	
	Optional<ExerciseProgress> findTopByWeekDayExerciseDetailsIdOrderByWorkoutDateDesc(Long weekDayExerciseDetailsId);

}
