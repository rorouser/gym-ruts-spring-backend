package com.mygymroutine.persistence.routine.routineCreation;

import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.persistence.workout.Workout;

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
public class RoutineCreation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String weekday;
	
    @ManyToOne
    @JoinColumn(name = "routineId")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;

}


