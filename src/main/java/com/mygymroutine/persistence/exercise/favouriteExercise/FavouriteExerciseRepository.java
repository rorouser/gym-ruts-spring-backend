package com.mygymroutine.persistence.exercise.favouriteExercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavouriteExerciseRepository extends JpaRepository<FavouriteExercise, Long>{

	List<FavouriteExercise> findAllByUser_Id(Integer userId);

    void deleteById(Long id);
}
