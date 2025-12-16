package com.gymruts.data.entity.weekDayExerciseDetails;

import com.gymruts.data.entity.exercise.Exercise;
import com.gymruts.data.entity.exerciseProgress.ExerciseProgress;
import com.gymruts.data.entity.routineCreation.RoutineCreation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
