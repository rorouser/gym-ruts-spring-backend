package com.gymruts.business.service.exerciseProgress;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gymruts.data.dto.exerciseProgress.ExerciseProgressResponse;
import com.gymruts.data.entity.exerciseProgress.ExerciseProgress;
import com.gymruts.data.entity.weekDayExerciseDetails.WeekDayExerciseDetails;
import com.gymruts.data.repository.exerciseProgress.ExerciseProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseProgressService {
	
	@Autowired
    ExerciseProgressRepository exerciseProgressRepository;
	
    public List<ExerciseProgressResponse> getExerciseProgressByWeekDayExerciseDetailsId(Long weekDayExerciseDetailsId) {
        List<ExerciseProgress> exerciseProgressList = exerciseProgressRepository.findByWeekDayExerciseDetailsId(weekDayExerciseDetailsId);
        return exerciseProgressList.stream()
                                   .map(this::mapToResponse)
                                   .collect(Collectors.toList());
    }
    
    public ExerciseProgressResponse getLastExerciseProgressByWeekDayExerciseDetailsId(Long weekDayExerciseDetailsId) {
        Optional<ExerciseProgress> lastExerciseProgress = exerciseProgressRepository.findTopByWeekDayExerciseDetailsIdOrderByWorkoutDateDesc(weekDayExerciseDetailsId);
        return lastExerciseProgress.map(this::mapToResponse).orElse(null);
    }
    
    public ExerciseProgress createExerciseProgress(ExerciseProgressResponse exerciseProgressResponse) {
    	
    	ExerciseProgress exerciseProgress = ExerciseProgress.builder()
    			.series(exerciseProgressResponse.getSeries())
    			.reps(exerciseProgressResponse.getReps())
    			.weight(exerciseProgressResponse.getWeight())
    			.weekDayExerciseDetails(WeekDayExerciseDetails.builder()
    					.id(exerciseProgressResponse.getWeekDayExerciseDetailsId())
    					.build())
    			.build();
        return exerciseProgressRepository.save(exerciseProgress);
    }
    
    private ExerciseProgressResponse mapToResponse(ExerciseProgress exerciseProgress) {
        return ExerciseProgressResponse.builder()
                                       .id(exerciseProgress.getId())
                                       .weekDayExerciseDetailsId(exerciseProgress.getWeekDayExerciseDetails().getId())
                                       .series(exerciseProgress.getSeries())
                                       .reps(exerciseProgress.getReps())
                                       .weight(exerciseProgress.getWeight())
                                       .workoutDate(exerciseProgress.getWorkoutDate())
                                       .build();
    }

}
