package com.mygymroutine.persistence.routine;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.user.User;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    public List<Routine> getAllRoutinesByUserId(Integer userId) {
        return routineRepository.findAllByUser_Id(userId);
    }

    public Optional<Routine> getRoutineById(Long routineId) {
        return routineRepository.findById(routineId);
    }
    
    public Routine createRoutine(Integer userId, Routine newRoutine) {
    	User user = new User();
    	user.setId(userId);
        newRoutine.setUser(user);
        Routine createdRoutine = routineRepository.save(newRoutine);
        return createdRoutine;
    }

    public void deleteRoutineById(Long routineId) {
        routineRepository.deleteById(routineId);
    }

    public void updateRoutineById(Long routineId, Routine updatedRoutine) {
        routineRepository.updateById(routineId, updatedRoutine);
    }
}

