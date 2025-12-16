package com.gymruts.data.entity.workout;

import java.util.List;

import com.gymruts.data.entity.routineCreation.RoutineCreation;
import com.gymruts.data.entity.user.User;
import com.gymruts.data.entity.workoutCreation.WorkoutCreation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workout_id")
	private Long workoutId;

	private String workoutName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "workout", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<WorkoutCreation> workoutCreation;
	
	@OneToMany(mappedBy = "workout", cascade={CascadeType.REMOVE}, orphanRemoval=true)
	private List<RoutineCreation> routineCreation;

}
