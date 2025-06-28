package com.mygymroutine.persistence.routine;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.exercise.IsCalistenics;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreation;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationService;
import com.mygymroutine.persistence.routine.routineCreation.WorkoutWeekdayCreate;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetailsResponse;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetailsService;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress.ExerciseProgress;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress.ExerciseProgressResponse;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.exerciseProgress.ExerciseProgressService;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.user.UserService;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreationResponse;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreationService;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;
    
    @Autowired
    private WorkoutCreationService workoutCreationService;
    
    @Autowired
    private WeekDayExerciseDetailsService weekDayExerciseDetailsService;
    
    @Autowired
    private ExerciseProgressService exerciseProgressService;
    
    @Autowired
    private RoutineCreationService routineCreationService;
    
    @Autowired 
    private UserService userService;

    public List<RoutineResponse> getAllRoutinesByUserId(Integer userId) {
    	List<Routine> routines = routineRepository.findAllByUser_Id(userId);
        return routines.stream()
                .map(routine -> RoutineResponse.builder()
                        .routineId(routine.getRoutineId())
                        .routineName(routine.getRoutineName())
                        .user_id(routine.getUser().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<RoutineResponse> getRoutineById(Long routineId) {
        Optional<Routine> routineOptional = routineRepository.findById(routineId);
        return routineOptional.map(routine -> {
            RoutineResponse routineResponse = RoutineResponse.builder()
                    .routineId(routine.getRoutineId())
                    .routineName(routine.getRoutineName())
                    .user_id(routine.getUser().getId())
                    .build();
            return routineResponse;
        });
    }

    
    public Routine createRoutine(Integer userId, RoutineCreate routineCreate) {
    	
    	RoutineResponse routineResponse = routineCreate.getRoutine();
        WorkoutWeekdayCreate[] workoutWeekdayCreateList = routineCreate.getWorkoutWeekdayCreateList();
        
        Routine routineToCreate = responseToRoutine(userId, routineResponse);
        
        Routine createdRoutine = routineRepository.save(routineToCreate);

        responseToRoutineWorkout(userId, workoutWeekdayCreateList, createdRoutine);
        
//        for (WorkoutWeekdayCreate workoutWeekdayCreate : workoutWeekdayCreateList) {
//        	List<WorkoutCreationResponse> workoutCreationList = workoutCreationService.
//        			getWorkoutExerciseByWorkoutId(workoutWeekdayCreate.getWorkout().getWorkoutId());
//        	
//        	for (WorkoutCreationResponse workoutCreationResponse : workoutCreationList) {
//        		workoutCreationResponse.getExercise().getExerciseId();
//        		
//        	}
//        	
//        }


        return createdRoutine;
    }
    
	private void responseToRoutineWorkout(Integer userId, WorkoutWeekdayCreate[] workoutWeekdayCreateList,
			Routine routine) {
		
		for (WorkoutWeekdayCreate workoutWeekdayCreate : workoutWeekdayCreateList) {
			
            Workout workoutToCreate = Workout.builder()
            		.workoutId(workoutWeekdayCreate.getWorkout().getWorkoutId())
                    .workoutName(workoutWeekdayCreate.getWorkout().getWorkoutName())
                    .user(User.builder().id(userId).role(Role.USER).build())
                    .build();

					
            RoutineCreation routineWorkout = RoutineCreation.builder()
                    .routine(routine)
                    .workout(workoutToCreate)
                    .weekday(workoutWeekdayCreate.getWeekDay())
                    .build();
            
            RoutineCreation routineCreation = routineCreationService.save(routineWorkout);
            
            List<WorkoutCreationResponse> workoutCreationList = workoutCreationService.
        			getWorkoutExerciseByWorkoutId(workoutWeekdayCreate.getWorkout().getWorkoutId());
            
            Optional<User> existingUser = userService.getUserByIdCheck(userId);
            
            for (WorkoutCreationResponse workoutCreationResponse : workoutCreationList) {
            	
            	WeekDayExerciseDetailsResponse weekDayExerciseDetailsToCreate = new WeekDayExerciseDetailsResponse();
        		
            	if(workoutCreationResponse.getExercise().getIsCalistenics() == IsCalistenics.SI) {
            		weekDayExerciseDetailsToCreate = WeekDayExerciseDetailsResponse.builder()
            				.series(4)
            				.reps(12)
            				.weight(existingUser.get().getUserWeight())
            				.exerciseId(workoutCreationResponse.getExercise().getExerciseId())
            				.routineCreationId(routineCreation.getId())
            				.build();
            	} else {
            		weekDayExerciseDetailsToCreate = WeekDayExerciseDetailsResponse.builder()
            				.series(4)
            				.reps(12)
            				.weight(40)
            				.exerciseId(workoutCreationResponse.getExercise().getExerciseId())
            				.routineCreationId(routineCreation.getId())
            				.build();
            	}
        		weekDayExerciseDetailsService.create(weekDayExerciseDetailsToCreate);
        		
        		if(userId == 2) {
        		    WeekDayExerciseDetailsResponse weekDayExerciseDetailsResponse = weekDayExerciseDetailsService.findByWorkoutIdAndRoutineId(
        		            workoutCreationResponse.getExercise().getExerciseId(),
        		            routine.getRoutineId(),
        		            workoutCreationResponse.getWorkout().getWorkoutId()
        		    );

        		    Random random = new Random();

        		    int constin = 50;
        		    
        		    for (int i = 0; i < 10; i++) { 
        		    	constin +=2;
        		        int randomWeight = constin + random.nextInt(5);

        		        ExerciseProgressResponse exerciseProgress = ExerciseProgressResponse.builder()
        		                .series(weekDayExerciseDetailsResponse.getSeries())
        		                .reps(weekDayExerciseDetailsResponse.getReps())
        		                .weight(randomWeight)
        		                .weekDayExerciseDetailsId(weekDayExerciseDetailsResponse.getId())
        		                .build();

        		        exerciseProgressService.createExerciseProgress(exerciseProgress);
        		    }
        		}
        	}
        }
	}

	private Routine responseToRoutine(Integer userId, RoutineResponse routineResponse) {
		Routine routineToCreate = Routine.builder()
				.routineId(routineResponse.getRoutineId())
                .routineName(routineResponse.getRoutineName())
                .user(User.builder()
                		.id(userId)
                		.role(Role.USER)
                		.build())
                .build();
		return routineToCreate;
	}

    public void deleteRoutineById(Long routineId) {
        routineRepository.deleteById(routineId);
    }

    public void updateRoutineById(Long routineId, Routine updatedRoutine) {
        routineRepository.updateById(routineId, updatedRoutine);
    }
}

