package com.mygymroutine.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routine.RoutineCreate;
import com.mygymroutine.persistence.routine.RoutineResponse;
import com.mygymroutine.persistence.routine.RoutineService;
import com.mygymroutine.persistence.routine.routineCreation.WeekDay;
import com.mygymroutine.persistence.routine.routineCreation.WorkoutWeekdayCreate;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutCreate;
import com.mygymroutine.persistence.workout.WorkoutResponse;
import com.mygymroutine.persistence.workout.WorkoutService;

@Service
public class InitializerService {
	
	@Autowired
	private WorkoutService workoutService;
	
	@Autowired
	private RoutineService routineService;
	
	public void CreateDefaultRoutine(int userId) {
		
		Workout legs = workoutService.createWorkout(userId, new WorkoutCreate().builder()
				.workout(new WorkoutResponse().builder()
						.workoutName("PIERNA")
						.user_id(userId)
						.build())
				.exerciseList(new Exercise[] {
						new Exercise().builder()
						.exerciseId(2L)
						.build(),
						new Exercise().builder()
						.exerciseId(7L)
						.build(),
						new Exercise().builder()
						.exerciseId(13L)
						.build(),
						new Exercise().builder()
						.exerciseId(15L)
						.build(),
						new Exercise().builder()
						.exerciseId(14L)
						.build(),
				})
				.build());
		
		Workout back = workoutService.createWorkout(userId, new WorkoutCreate().builder()
				.workout(new WorkoutResponse().builder()
						.workoutName("ESPALDA")
						.user_id(userId)
						.build())
				.exerciseList(new Exercise[] {
						new Exercise().builder()
						.exerciseId(3L)
						.build(),
						new Exercise().builder()
						.exerciseId(30L)
						.build(),
						new Exercise().builder()
						.exerciseId(16L)
						.build(),
						new Exercise().builder()
						.exerciseId(24L)
						.build(),
						new Exercise().builder()
						.exerciseId(28L)
						.build(),
				})
				.build());
		
		Workout chest = workoutService.createWorkout(userId, new WorkoutCreate().builder()
				.workout(new WorkoutResponse().builder()
						.workoutName("PECHO - BICEPS")
						.user_id(userId)
						.build())
				.exerciseList(new Exercise[] {
						new Exercise().builder()
						.exerciseId(1L)
						.build(),
						new Exercise().builder()
						.exerciseId(6L)
						.build(),
						new Exercise().builder()
						.exerciseId(8L)
						.build(),
						new Exercise().builder()
						.exerciseId(12L)
						.build(),
						new Exercise().builder()
						.exerciseId(10L)
						.build(),
						new Exercise().builder()
						.exerciseId(25L)
						.build(),
				})
				.build());
		
		routineService.createRoutine(userId, new RoutineCreate().builder()
				.routine(new RoutineResponse().builder()
						.routineName("3 DIAS")
						.user_id(userId)
						.build())
				.workoutWeekdayCreateList(new WorkoutWeekdayCreate[] {
						new WorkoutWeekdayCreate().builder()
						.workout(new WorkoutResponse().builder()
								.workoutName(legs.getWorkoutName())
								.workoutId(legs.getWorkoutId())
								.build())
						.weekDay(WeekDay.MONDAY)
						.build(),
						new WorkoutWeekdayCreate().builder()
						.workout(new WorkoutResponse().builder()
								.workoutName(chest.getWorkoutName())
								.workoutId(chest.getWorkoutId())
								.build())
						.weekDay(WeekDay.WEDNESDAY)
						.build(),
						new WorkoutWeekdayCreate().builder()
						.workout(new WorkoutResponse().builder()
								.workoutName(back.getWorkoutName())
								.workoutId(back.getWorkoutId())
								.build())
						.weekDay(WeekDay.FRIDAY)
						.build(),
				})
				.build());
	}

}
