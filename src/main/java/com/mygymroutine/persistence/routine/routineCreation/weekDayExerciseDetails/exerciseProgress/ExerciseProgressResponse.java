package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationResponse;
import com.mygymroutine.persistence.routine.routineCreation.WeekDay;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.mygymroutine.persistence.workout.WorkoutResponse;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseProgressResponse {
	
    private Long id;
	
    private Long weekDayExerciseDetailsId;
	
	private int series;
	
	private int reps;
	
	private double weight;
	
	private Date workoutDate;

}
