package com.mygymroutine.persistence.workout;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.routine.Routine;
import com.mygymroutine.user.User;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> getAllWorkoutsByUserId(Integer userId) {
        return workoutRepository.findAllByUser_Id(userId);
    }

    public Optional<Workout> getWorkoutById(Long workoutId) {
        return workoutRepository.findById(workoutId);
    }
    
    public Workout createWorkout(Integer userId, Workout newWorkout) {
    	User user = new User();
    	user.setId(userId);
        newWorkout.setUser(user);
        Workout createdWorkout = workoutRepository.save(newWorkout);
        return createdWorkout;
    }

    public void deleteWorkoutById(Long workoutId) {
        workoutRepository.deleteById(workoutId);
    }

    public void updateWorkoutById(Long workoutId, Workout updatedWorkout) {
        workoutRepository.updateById(workoutId, updatedWorkout);
    }
}

