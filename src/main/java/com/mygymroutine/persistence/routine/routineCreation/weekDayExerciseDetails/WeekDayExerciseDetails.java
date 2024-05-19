package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import java.util.List;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreation;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress.ExerciseProgress;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeekDayExerciseDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private int series;
	
	private int reps;
	
	private double weight;
	
	@ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
	
	@ManyToOne
    @JoinColumn(name = "routineCreationId")
    private RoutineCreation routineCreation;
	
	@OneToMany(mappedBy = "weekDayExerciseDetails", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<ExerciseProgress> exerciseProgresses;
	
    public WeekDayExerciseDetails(int series, int reps, double weight, Exercise exercise, RoutineCreation routineCreation) {
        this.series = series;
        this.reps = reps;
        this.weight = weight;
        this.exercise = exercise;
        this.routineCreation = routineCreation;
    }

}
