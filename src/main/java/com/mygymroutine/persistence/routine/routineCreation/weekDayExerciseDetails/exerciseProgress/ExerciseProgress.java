package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExerciseProgress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "weekDayExerciseDetails_id")
    private WeekDayExerciseDetails weekDayExerciseDetails;
	
	private int series;
	
	private int reps;
	
	private double weight;
	
	@CreationTimestamp
	@Column(name = "workout_date", nullable = false, updatable = false)
	private Date workoutDate;

}
