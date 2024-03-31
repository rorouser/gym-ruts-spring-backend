package com.mygymroutine.persistence.workout.workoutCreation;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.exercise.ExerciseResponse;
import com.mygymroutine.persistence.routine.RoutineResponse;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutResponse;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutCreationResponse {
	
    private Long id;

    private WorkoutResponse workout;

    private ExerciseResponse exercise;

}
