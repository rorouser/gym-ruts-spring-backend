package com.mygymroutine.persistence.exercise;


import com.mygymroutine.persistence.user.User;

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
public class Exercise {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    private String exerciseName;
    
    private String instructions;
    
    private String img;
    
    private String isCalistenics;
    
    private String muscleGroup;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    
	public Exercise(String exerciseName, String instructions, String img, String isCalistenics, String muscleGroup, User user) {
		super();
		this.exerciseName = exerciseName;
		this.instructions = instructions;
		this.img = img;
		this.isCalistenics = isCalistenics;
		this.muscleGroup = muscleGroup;
		this.user = user;
	}
    
    

}

