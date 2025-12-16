package com.gymruts.data.repository.routine;

import com.gymruts.data.entity.routine.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    void deleteById(Long routineId);
    
    default void updateById(Long routineId, Routine updatedRoutine) {
        Routine existingRoutine = findById(routineId).orElse(null);
        if (existingRoutine != null) {
            existingRoutine.setRoutineName(updatedRoutine.getRoutineName());            
            save(existingRoutine);
        }
    }
    
    List<Routine> findAllByUser_Id(Integer userId);
}

