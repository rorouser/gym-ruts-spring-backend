package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDayExerciseDetailsRepository extends JpaRepository<WeekDayExerciseDetails, Long> {

}
