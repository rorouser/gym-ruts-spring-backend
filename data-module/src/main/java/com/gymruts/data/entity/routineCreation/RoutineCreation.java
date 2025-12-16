package com.gymruts.data.entity.routineCreation;

import java.util.List;

import com.gymruts.data.entity.routine.Routine;
import com.gymruts.data.entity.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.gymruts.data.entity.workout.Workout;
import com.gymruts.data.enums.WeekDay;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RoutineCreation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private WeekDay weekday;
	
    @ManyToOne
    @JoinColumn(name = "routineId")
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workout;
    
    @OneToMany(mappedBy = "routineCreation", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<WeekDayExerciseDetails> weekDayExerciseDetails;

}


