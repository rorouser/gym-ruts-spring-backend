package com.mygymroutine.persistence.exercise;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Exercise(String exerciseName, String instructions, String img) {
		super();
		this.exerciseName = exerciseName;
		this.instructions = instructions;
		this.img = img;
	}
    
    

}

