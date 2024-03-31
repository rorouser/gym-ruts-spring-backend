package com.mygymroutine.persistence.exercise.favouriteExercise;

import com.mygymroutine.persistence.exercise.Exercise;
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
public class FavouriteExercise {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   

}
