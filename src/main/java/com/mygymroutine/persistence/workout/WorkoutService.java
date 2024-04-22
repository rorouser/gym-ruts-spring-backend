package com.mygymroutine.persistence.workout;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.user.Role;
import com.mygymroutine.persistence.user.User;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreation;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreationService;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;
    
    @Autowired
    private WorkoutCreationService workoutCreationService;

    public List<WorkoutResponse> getAllWorkoutsByUserId(Integer userId) {
    	
    	List<Workout> workouts = workoutRepository.findAllByUser_Id(userId);
    	
        List<WorkoutResponse> workoutsResponses = workouts.stream()
                .map(workout -> WorkoutResponse.builder()
                        .workoutId(workout.getWorkoutId())
                        .workoutName(workout.getWorkoutName())
                        .user_id(workout.getUser().getId())
                        .build())
                .collect(Collectors.toList());
        
        return workoutsResponses;
    }

    public Optional<Workout> getWorkoutById(Long workoutId) {
        return workoutRepository.findById(workoutId);
    }
    
    public Workout createWorkout(Integer userId, WorkoutCreate workoutCreate) {
    	
    	 WorkoutResponse workoutResponse = workoutCreate.getWorkout();
         Exercise[] exerciseList = workoutCreate.getExerciseList();
         
         Workout workoutToCreate = Workout.builder()
                 .workoutName(workoutResponse.getWorkoutName())
                 .user(User.builder().id(userId).role(Role.USER).build())
                 .build();

         Workout createdWorkout = workoutRepository.save(workoutToCreate);

         for (Exercise exercise : exerciseList) {
         	WorkoutCreation workoutExercise = WorkoutCreation.builder()
                     .workout(createdWorkout)
                     .exercise(exercise)
                     .build();

         	workoutCreationService.save(workoutExercise);
         }
         
         return workoutToCreate;
    }

    public void deleteWorkoutById(Long workoutId) {
        workoutRepository.deleteById(workoutId);
    }

    public void updateWorkoutById(Long workoutId, Workout updatedWorkout) {
        workoutRepository.updateById(workoutId, updatedWorkout);
    }
}

