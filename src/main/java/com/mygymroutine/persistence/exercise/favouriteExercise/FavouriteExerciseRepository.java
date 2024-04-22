package com.mygymroutine.persistence.exercise.favouriteExercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface FavouriteExerciseRepository extends JpaRepository<FavouriteExercise, Long>{

	List<FavouriteExercise> findAllByUser_Id(Integer userId);
	
//	@Transactional
//	void deleteByUser_IdAndExercise_ExerciseId(Integer userId, Long exerciseId);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM FavouriteExercise f WHERE f.user.id = ?1 AND f.exercise.exerciseId = ?2")
    void deleteByUserIdAndExerciseId(Integer userId, Long exerciseId);
}
