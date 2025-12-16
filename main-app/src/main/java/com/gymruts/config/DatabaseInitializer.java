package com.gymruts.config;

import java.util.Arrays;
import java.util.List;

import com.gymruts.business.service.user.UserService;
import com.gymruts.data.entity.exercise.Exercise;
import com.gymruts.data.enums.IsCalistenics;
import com.gymruts.data.enums.MuscleGroup;
import com.gymruts.data.enums.Role;
import com.gymruts.data.entity.user.User;
import com.gymruts.data.repository.exercise.ExerciseRepository;
import com.gymruts.data.repository.routine.RoutineRepository;
import com.gymruts.data.repository.routineCreation.RoutineCreationRepository;
import com.gymruts.data.repository.user.UserRepository;
import com.gymruts.data.repository.workout.WorkoutRepository;
import com.gymruts.data.repository.workoutCreation.WorkoutCreationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gymruts.auth.AuthenticationService;
import com.gymruts.auth.RegisterRequest;

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
	private RoutineCreationRepository routineCreationRepository;

	@Autowired
	private WorkoutCreationRepository workoutCreationRepository;

	@Autowired
	private AuthenticationService service;

	@Autowired
	private UserService userService;

	@Value("${admin.firstname}")
	private String adminFirstname;

	@Value("${admin.lastname}")
	private String adminLastname;

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	@PostConstruct
	@Transactional
	public void initializeExercises() {

		var admin = RegisterRequest.builder()
				.firstname(adminFirstname)
				.lastname(adminLastname)
				.email(adminEmail)
				.password(adminPassword)
				.password2(adminPassword)
				.role(Role.ADMIN)
				.build();

		var user0 = RegisterRequest.builder()
				.firstname("Iratxe")
				.lastname("Llaga")
				.email("user@user.com")
				.userWeight(50)
				.userHeight(160)
				.password("usuario")
				.password2("usuario")
				.role(Role.USER)
				.build();


		service.register(admin);
		User user = userRepository.findById(1).orElse(null);

		if (user == null) {
			throw new IllegalStateException("User with ID 1 not found");
		}

		List<Exercise> exercises = Arrays.asList(
				new Exercise("Flexión",
						"Realiza flexiones con la forma correcta, involucrando tu núcleo y manteniendo una alineación corporal recta durante todo el movimiento.",
						"flexion.avif", IsCalistenics.SI, MuscleGroup.PECHO, user),
				new Exercise("Sentadilla con mancuerna",
						"Realiza sentadillas con una postura a la anchura de los hombros, asegurando una profundidad adecuada mientras mantienes tus rodillas alineadas con tus dedos de los pies.",
						"sentadilla-barrell.avif", IsCalistenics.SI, MuscleGroup.PIERNA,  user),
				new Exercise("Dominada",
						"Realiza dominadas con un movimiento controlado, enfocándote en involucrar los músculos de la espalda y manteniendo una posición corporal recta durante el ejercicio.",
						"dominadas.avif", IsCalistenics.SI, MuscleGroup.ESPALDA, user),
				new Exercise("Flys",
						"Realiza flys en una máquina, enfocándote en el movimiento controlado para dirigir eficazmente los músculos del pecho.",
						"flys-maquina.avif", IsCalistenics.NO, MuscleGroup.PECHO, user),
				new Exercise("Crunches",
						"Completa crunches con la forma adecuada, involucrando tus músculos abdominales y evitando la tensión en el cuello manteniendo la barbilla recogida.",
						"crunch.avif", IsCalistenics.SI, MuscleGroup.CORE, user),
				new Exercise("Bíceps Brachial",
						"Participa en un entrenamiento de cuerpo completo combinando sentadillas, flexiones y saltos, dirigiendo múltiples grupos musculares simultáneamente.",
						"biceps-brachial.avif", IsCalistenics.NO, MuscleGroup.BRAZOS, user),
				new Exercise("Gemelos",
						"Fortalece tus pantorrillas con ejercicios dinámicos de escalador de montaña, involucrando tu núcleo y la parte inferior del cuerpo para estabilidad.",
						"calves.avif", IsCalistenics.SI, MuscleGroup.PIERNA, user),
				new Exercise("Curls de bíceps",
						"Realiza curls de bíceps usando mancuernas, asegurando un movimiento controlado y una alineación adecuada de las muñecas durante todo el ejercicio.",
						"biceps-brachial.avif", IsCalistenics.NO, MuscleGroup.BRAZOS, user),
				new Exercise("Peso muerto",
						"Realiza peso muerto con una técnica de levantamiento adecuada, enfocándote en mantener una columna neutral e involucrando tus músculos de la cadena posterior.",
						"rumano.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Pull Over",
						"Fortalece tu parte superior del cuerpo con ejercicios de pull-over, enfocándote en un movimiento controlado e involucrando eficazmente tus músculos del pecho y la espalda.",
						"pull-over.avif", IsCalistenics.NO, MuscleGroup.PECHO, user),
				new Exercise("Patada de glúteo",
						"Realiza patadas de glúteo para fortalecer tus glúteos y isquiotibiales.",
						"glute_kickbacks.avif", IsCalistenics.NO, MuscleGroup.GLUTEOS, user),
				new Exercise("Press de banca",
						"Realiza press de banca con la forma adecuada.",
						"bench_press.avif", IsCalistenics.NO, MuscleGroup.PECHO, user),
				new Exercise("Prensa",
						"Realiza prensa de piernas con peso y forma adecuados.",
						"leg_press.avif", IsCalistenics.NO, MuscleGroup.PIERNA, user),
				new Exercise("Extensiones de cuádriceps",
						"Realiza extensiones de cuádriceps con la forma adecuada.",
						"quadriceps_extensions.avif", IsCalistenics.NO, MuscleGroup.PIERNA, user),
				new Exercise("Reverse Hack Squat",
						"Realiza reverse hack squats con la forma adecuada.",
						"reverse_hack_squat.avif", IsCalistenics.NO, MuscleGroup.PIERNA, user),
				new Exercise("Remo con cable sentado",
						"Este ejercicio implica tirar de un accesorio de cable hacia tu cuerpo mientras estás sentado, usando una máquina de cables.",
						"seated_cable_row.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Remo alto con polea de rodillas",
						"Realiza un ejercicio de remo alto con polea mientras estás de rodillas, enfocándote en tirar del asa hacia tu sección media.",
						"kneeling_high_pulley_row.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Elevación lateral con mancuernas",
						"Levanta mancuernas lateralmente a la altura de los hombros mientras mantienes los brazos rectos.",
						"dumbbell_lateral_raise.avif", IsCalistenics.NO, MuscleGroup.BRAZOS, user),
				new Exercise("Buenos días con mancuernas",
						"Buenos días con mancuernas es un ejercicio de entrenamiento de fuerza que dirige los músculos de la parte baja de la espalda, isquiotibiales y glúteos.",
						"goodmorning.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Curl de bíceps en predicador con mancuernas",
						"Se realiza en un banco de curl predicador, que permite el aislamiento de los bíceps y ayuda a reducir la participación de otros grupos musculares en el movimiento.",
						"DumbbellPreacherCurl.avif", IsCalistenics.NO, MuscleGroup.BRAZOS, user),
				new Exercise("Remo vertical con mancuernas",
						"Fortalecer los músculos del trapecio ayuda a estabilizar tu cuello y espalda, así como a reducir la tensión en tus músculos del cuello y los hombros.",
						"dumbbell_upright_row.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Crunch con cable de pie",
						"El crunch con cable de pie es un ejercicio efectivo que dirige los músculos abdominales, particularmente el recto abdominal. Implica el uso de una máquina de cables para proporcionar resistencia durante todo el movimiento.",
						"standing_cable_crunch.avif", IsCalistenics.NO, MuscleGroup.CORE, user),
				new Exercise("Curl de muñeca con cable a un brazo",
						"Realiza curls de muñeca con cable a un brazo mientras estás acostado en el suelo.",
						"cable_one_arm_wrist_curl_on_floor.avif", IsCalistenics.NO, MuscleGroup.BRAZOS, user),
				new Exercise("Dominadas asistidas",
						"Realiza dominadas asistidas usando una banda de resistencia o una máquina.",
						"assisted_pullup.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Pec Deck Fly",
						"La máquina actúa como estabilizador aquí, enfatizando el músculo pectoral mayor del pecho y el músculo pectoral menor justo debajo.",
						"pec_deck_fly.avif", IsCalistenics.NO, MuscleGroup.PECHO, user),
				new Exercise("Dragon Flag",
						"Para realizar Dragon Flags correctamente, necesitas un control preciso sobre el movimiento de tu cuerpo, tanto durante las fases de bajada como de subida.",
						"dragon_flag.avif", IsCalistenics.SI, MuscleGroup.CORE, user),
				new Exercise("Sentadilla pistola",
						"El ejercicio de sentadilla pistola requiere un alto nivel de equilibrio, coordinación, estabilidad básica y fuerza en las piernas.",
						"pistol_squat.avif", IsCalistenics.SI, MuscleGroup.PIERNA, user),
				new Exercise("Pulldown trasero con cable",
						"Este ejercicio dirige principalmente los músculos en la parte superior de la espalda, especialmente el latissimus dorsi (lats), que son responsables de la apariencia ancha y en forma de V de la espalda.",
						"cable_rear_pulldown.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Pulldown de dorsales",
						"Realiza pulldown de dorsales con la forma adecuada. El pulldown de dorsales es un ejercicio de tirón que dirige principalmente los músculos latissimus dorsi (comúnmente conocidos como “lats”) en tu espalda.",
						"lat_pulldown.avif", IsCalistenics.NO, MuscleGroup.ESPALDA, user),
				new Exercise("Press militar sentado",
						"Siéntate en un banco con respaldo, sostén una barra con las manos ligeramente más anchas que los hombros.",
						"press_militar_sentado.avif", IsCalistenics.NO, MuscleGroup.HOMBRO, user),
				new Exercise("Elevación lateral",
						"Ajusta la polea en la posición más baja y agarra la manija con la mano opuesta al lado de la polea, manteniendo el brazo ligeramente doblado.",
						"elevacion_lateral_hombros.avif", IsCalistenics.NO, MuscleGroup.HOMBRO, user),
				new Exercise("Press militar polea",
						"Sujeta el agarre, mantén una postura erguida y eleva los hombros hacia las orejas contrayendo las escápulas, sin flexionar los codos.",
						"elevacion_polea_escapulas.avif", IsCalistenics.NO, MuscleGroup.HOMBRO, user),
				new Exercise("Elevación lateral polea",
						"La elevación lateral con polea para hombro es un ejercicio que se realiza de pie junto a una máquina de poleas. Ajusta la polea en la posición más baja y agarra la manija con la mano opuesta al lado de la polea, manteniendo el brazo ligeramente doblado..",
						"elevacion_polea_lateral_hombros.avif", IsCalistenics.NO, MuscleGroup.HOMBRO, user),
				new Exercise("Elevación frontal",
						"Sujeta una mancuerna en cada mano, palmas hacia abajo. Eleva los brazos rectos hacia adelante hasta la altura de los hombros.",
						"frontal_hombros.avif", IsCalistenics.NO, MuscleGroup.HOMBRO, user)
		);


		exerciseRepository.saveAll(exercises);


		service.register(user0);



		for (int i = 2; i < 12; i++) {
			String email = "user" + i + "@user.com";
			RegisterRequest registerRequest = RegisterRequest.builder()
					.firstname("Manuel")
					.lastname("Montoya")
					.email(email)
					.password("user")
					.password2("user")
					.role(Role.USER)
					.build();

			service.register(registerRequest);
		}
	}
}
