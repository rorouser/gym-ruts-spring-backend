package com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreation;

@Service
public class WeekDayExerciseDetailsService {

	@Autowired
	private WeekDayExerciseDetailsRepository weekDayExerciseDetailsRepository;

	public void deleteById(Long id) {
		weekDayExerciseDetailsRepository.deleteById(id);
	}

	public WeekDayExerciseDetails create(WeekDayExerciseDetailsResponse weekDayExerciseDetailsResponse) {
		
		WeekDayExerciseDetails weekDayExerciseDetails = WeekDayExerciseDetails.builder()
                .id(weekDayExerciseDetailsResponse.getId())
                .series(weekDayExerciseDetailsResponse.getSeries())
                .reps(weekDayExerciseDetailsResponse.getReps())
                .weight(weekDayExerciseDetailsResponse.getWeight())
                .exercise(Exercise.builder()
                		.exerciseId(weekDayExerciseDetailsResponse.getExerciseId())
                		.build())
                .routineCreation(RoutineCreation.builder()
                		.id(weekDayExerciseDetailsResponse.getRoutineCreationId())
                		.build())
                .build();
        
		WeekDayExerciseDetails createdWeekDayExerciseDetails = weekDayExerciseDetailsRepository.save(weekDayExerciseDetails);

        
        if(createdWeekDayExerciseDetails!=null) {
        	return createdWeekDayExerciseDetails;
        } else {
        	return null;
        }
	}

	public WeekDayExerciseDetails update(WeekDayExerciseDetails details) {
		return weekDayExerciseDetailsRepository.save(details);
	}

	public List<WeekDayExerciseDetails> getAll() {
		return weekDayExerciseDetailsRepository.findAll();
	}

	public Optional<WeekDayExerciseDetails> getById(Long id) {
		return weekDayExerciseDetailsRepository.findById(id);
	}
	
    public List<WeekDayExerciseDetailsResponse> findByExerciseIdAndRoutineWorkoutId(Long exerciseId, Long routineCreationId) {
        List<WeekDayExerciseDetails> weekDayExerciseDetailsList = weekDayExerciseDetailsRepository
                .findByExercise_ExerciseIdAndRoutineCreation_Id(exerciseId, routineCreationId);

        return weekDayExerciseDetailsList.stream()
                .map(details -> WeekDayExerciseDetailsResponse.builder()
                        .id(details.getId())
                        .series(details.getSeries())
                        .reps(details.getReps())
                        .weight(details.getWeight())
                        .exerciseId(details.getExercise().getExerciseId())
                        .routineCreationId(details.getRoutineCreation().getId())
                        .build())
                .collect(Collectors.toList());
    }
    
    public List<WeekDayExerciseDetailsResponse> findByWorkoutIdAndRoutineId(Long exerciseId, Long routineId, Long workoutId) {
        List<WeekDayExerciseDetails> weekDayExerciseDetailsList = weekDayExerciseDetailsRepository
                .findByExerciseIdAndRoutineIdAndWorkoutId(exerciseId, routineId, workoutId);

        return weekDayExerciseDetailsList.stream()
                .map(details -> WeekDayExerciseDetailsResponse.builder()
                        .id(details.getId())
                        .series(details.getSeries())
                        .reps(details.getReps())
                        .weight(details.getWeight())
                        .exerciseId(details.getExercise().getExerciseId())
                        .routineCreationId(details.getRoutineCreation().getId())
                        .build())
                .collect(Collectors.toList());
    }
	
}
