package com.mygymroutine.persistence.exercise;


import java.util.List;

import com.mygymroutine.persistence.exercise.favouriteExercise.FavouriteExercise;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreation;

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
public class Exercise {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    private String exerciseName;
    
    private String instructions;
    
    private String img;
    
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;
    
    @Enumerated(EnumType.STRING)
    private IsCalistenics isCalistenics;
    
    @ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
    
	@OneToMany(mappedBy = "exercise", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<FavouriteExercise> favouriteExercises;
	
	@OneToMany(mappedBy = "exercise", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<WorkoutCreation> workoutCreation;
	
	@OneToMany(mappedBy = "exercise", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<WeekDayExerciseDetails> weekDayExerciseDetails;
    
	public Exercise(String exerciseName, String instructions, String img, IsCalistenics isCalistenics, MuscleGroup muscleGroup, User user) {
		super();
		this.exerciseName = exerciseName;
		this.instructions = instructions;
		this.img = img;
		this.isCalistenics = isCalistenics;
		this.muscleGroup = muscleGroup;
		this.user = user;
	}
    
    

}

