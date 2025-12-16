package com.gymruts.business.service.routineCreation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gymruts.data.dto.routineCreation.RoutineCreationResponse;
import com.gymruts.data.dto.weekDay.WeekDayCountDTO;
import com.gymruts.data.dto.workout.WorkoutResponse;
import com.gymruts.data.entity.routineCreation.RoutineCreation;
import com.gymruts.data.enums.WeekDay;
import com.gymruts.data.repository.routineCreation.RoutineCreationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RoutineCreationService {

    @Autowired
    private RoutineCreationRepository routineCreationRepository;
    
    public List<RoutineCreationResponse> getRoutineWorkoutByRoutineId(Long routineId) {
    	
    	List<RoutineCreation> routineCreations = routineCreationRepository.findByRoutine_RoutineId(routineId);
    	
    	if(!routineCreations.isEmpty()) {
            return routineCreations.stream()
                    .map(routineCreation -> RoutineCreationResponse.builder()
                            .id(routineCreation.getId())
                            .workout(new WorkoutResponse(routineCreation.getWorkout().getWorkoutId(), routineCreation.getWorkout().getWorkoutName(), routineCreation.getWorkout().getUser().getId()))
                            .routineId(routineCreation.getRoutine().getRoutineId())
                            .weekday(routineCreation.getWeekday())
                            .build())
                    .collect(Collectors.toList());
    	} else {
    		return null;
    	}
    	
    	
	}
    
    public RoutineCreation save(RoutineCreation routineCreation) {
    	return routineCreationRepository.save(routineCreation);
    }
    
    public RoutineCreationResponse getRoutineCreationById(Long routineCreationId) {
        Optional<RoutineCreation> routineCreationOptional = routineCreationRepository.findById(routineCreationId);

        if (routineCreationOptional.isPresent()) {
            RoutineCreation routineCreation = routineCreationOptional.get();
            return RoutineCreationResponse.builder()
                    .id(routineCreation.getId())
                    .workout(new WorkoutResponse(routineCreation.getWorkout().getWorkoutId(), routineCreation.getWorkout().getWorkoutName(), routineCreation.getWorkout().getUser().getId()))
                    .routineId(routineCreation.getRoutine().getRoutineId())
                    .weekday(routineCreation.getWeekday())
                    .build();
        } else {
            return null; 
        }
    }
    
    
    public List<WeekDayCountDTO> getCountByWeekday() {
        List<Object[]> results = routineCreationRepository.countByWeekday();
        return results.stream()
            .map(result -> WeekDayCountDTO.builder()
                .weekday((WeekDay) result[0])
                .count(((Number) result[1]).longValue())
                .build())
            .collect(Collectors.toList());
    };
    
    @Transactional
    public void deleteByRoutineId(Long routineId) {
    	routineCreationRepository.deleteByRoutine_RoutineId(routineId);
    }
    
    @Transactional
    public void deleteByWorkoutId(Long workoutId) {
    	routineCreationRepository.deleteByWorkout_WorkoutId(workoutId);
    }
    
    @Transactional
    public void deleteById(Long routineWorkoutId) {
    	routineCreationRepository.deleteById(routineWorkoutId);
    }

}

