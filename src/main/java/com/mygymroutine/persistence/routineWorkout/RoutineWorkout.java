package com.mygymroutine.persistence.routineWorkout;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.workout.Workout;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoutineWorkout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "routineId")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;

}


