package com.gymruts.data.entity.exercise;


import com.gymruts.data.entity.favouriteExercise.FavouriteExercise;
import com.gymruts.data.entity.user.User;
import com.gymruts.data.entity.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.gymruts.data.entity.workoutCreation.WorkoutCreation;
import com.gymruts.data.enums.IsCalistenics;
import com.gymruts.data.enums.MuscleGroup;
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

