package com.mygymroutine;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygymroutine.persistence.exercise.Exercise;
import com.mygymroutine.persistence.exercise.ExerciseRepository;
import com.mygymroutine.persistence.routine.RoutineRepository;
import com.mygymroutine.persistence.routineWorkout.RoutineWorkoutRepository;
import com.mygymroutine.persistence.workout.Workout;
import com.mygymroutine.persistence.workout.WorkoutRepository;
import com.mygymroutine.persistence.workoutExercise.WorkoutExerciseRepository;
import com.mygymroutine.user.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class DatabaseInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WorkoutRepository workoutRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private RoutineRepository routineRepository;

	@Autowired
	private RoutineWorkoutRepository routineWorkoutRepository;

	@Autowired
	private WorkoutExerciseRepository workoutExerciseRepository;

	@PostConstruct
	@Transactional
	public void initialize() {
		List<Exercise> exercises = Arrays.asList(new Exercise("Push-ups",
				"Execute push-ups with correct form, engaging your core and maintaining a straight body alignment throughout the movement.",
				"flexion.avif"),
				new Exercise("Squats",
						"Perform squats with a shoulder-width stance, ensuring proper depth while keeping your knees aligned with your toes.",
						"sentadilla-barrell.avif"),
				new Exercise("Pull-ups",
						"Perform pull-ups with a controlled motion, focusing on engaging your back muscles and maintaining a straight body position throughout the exercise.",
						"dominadas.avif"),
				new Exercise("Flys",
						"Perform flys on a machine, focusing on controlled movement to target your chest muscles effectively.",
						"flys-maquina.avif"),
				new Exercise("Crunches",
						"Complete crunches with proper form, engaging your abdominal muscles and avoiding neck strain by keeping your chin tucked.",
						"crunch.avif"),
				new Exercise("Biceps Brachial",
						"Engage in a full-body workout by combining squats, push-ups, and jumps, targeting multiple muscle groups simultaneously.",
						"biceps-brachial.avif"),
				new Exercise("Calves",
						"Strengthen your calves with dynamic mountain climber exercises, engaging your core and lower body for stability.",
						"calves.avif"),
				new Exercise("Bicep Curls",
						"Perform bicep curls using dumbbells, ensuring controlled movement and proper wrist alignment throughout the exercise.",
						"biceps-brachial.avif"),
				new Exercise("Deadlifts",
						"Execute deadlifts with proper lifting technique, focusing on maintaining a neutral spine and engaging your posterior chain muscles.",
						"rumano.avif"),
				new Exercise("Pull Over",
						"Strengthen your upper body with pull-over exercises, focusing on controlled movement and engaging your chest and back muscles effectively.",
						"pull-over.avif"),
				
				new Exercise("Glute Kickbacks", "Perform glute kickbacks to strengthen your glutes and hamstrings.",
						"glute_kickbacks.avif"),
				new Exercise("Bench Press", "Perform bench presses with proper form.", "bench_press.avif"),
				new Exercise("Leg Press", "Perform leg presses with appropriate weight and form.", "leg_press.avif"),
				new Exercise("Quadriceps Extensions", "Perform quadriceps extensions with proper form.",
						"quadriceps_extensions.avif"),

				new Exercise("Reverse Hack Squat", "Perform reverse hack squats with proper form.",
						"reverse_hack_squat.avif"),
				new Exercise("Seated Cable Row",
						"This exercise involves pulling a cable attachment towards your body while seated, using a cable machine.",
						"Seated Cable Row.avif"),
				new Exercise("Kneeling High Pulley Row",
						"Perform a high pulley row exercise while kneeling, focusing on pulling the handle towards your midsection.",
						"kneeling_high_pulley_row.avif"),
				new Exercise("Dumbbell Lateral Raise",
						"Raise dumbbells laterally to shoulder height while keeping arms straight.",
						"dumbbell_lateral_raise.avif"),
				new Exercise("Dumbbell Good Morning",
						" Dumbbell Good Morning is a strength training exercise that targets the muscles of the lower back, hamstrings, and glutes",
						"goodmorning.avif"),
				new Exercise("Dumbbell Preacher Curl",
						" It is performed on a preacher curl bench, which allows for isolation of the biceps and helps to reduce the involvement of other muscle groups in the movement.",
						"DumbbellPreacherCurl.avif"),
				new Exercise("Dumbbell Upright Row",
						"Strengthening your trapezius muscles helps stabilize your neck and back, as well as reduce the tension in your neck and shoulder muscles.",
						"dumbbell_upright_row.avif"),

				new Exercise("Standing Cable Crunch",
						"The Standing Cable Crunch is an effective exercise that targets the abdominal muscles, particularly the rectus abdominis. It involves using a cable machine to provide resistance throughout the movement.",
						"standing_cable_crunch.avif"),

				new Exercise("Cable One-Arm Wrist Curl",
						"Perform one-arm wrist curls using a cable while lying on the floor.",
						"cable_one_arm_wrist_curl_on_floor.avif"),

				new Exercise("Assisted Pull-Up", "Perform assisted pull-ups using a resistance band or machine.",
						"assisted_pullup.avif"),

				new Exercise("Pec Deck Fly",
						"The machine acts as a stabilizer here, emphasizing the pectoralis major muscle of the chest and the pectoralis minor muscle just below it.",
						"pec_deck_fly.avif"),

				new Exercise("Dragon Flag",
						"To perform Dragon Flags correctly, you need precise control over your body’s movement, both during the lowering and raising phases.",
						"dragon_flag.avif"),

				new Exercise("Pistol Squat",
						"Pistol squat exercise requires a high level of balance, coordination, basic stability and leg strength.",
						"pistol_squat.avif"),

				new Exercise("Cable Rear Pulldown",
						"This exercise primarily targets the muscles in the upper back, especially the latissimus dorsi (lats), which are responsible for the broad and V-shaped appearance of the back.",
						"cable_rear_pulldown.avif"),

				new Exercise("Lat Pulldown",
						"Perform Lat Pulldown with proper form. The lat pulldown is a pulling exercise that primarily targets the latissimus dorsi muscles (commonly known as “lats”) in your back.",
						"lat_pulldown.avif"),

				new Exercise("Lever T-Bar",
						"T-bar rows are a strength training exercise that mimics the movement of rowing a boat.",
						"lever_tbar.avif"));

		exerciseRepository.saveAll(exercises);
	}

}
