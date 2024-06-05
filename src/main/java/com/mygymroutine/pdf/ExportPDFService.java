package com.mygymroutine.pdf;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationResponse;
import com.mygymroutine.persistence.routine.routineCreation.WeekDay;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetailsResponse;
import com.mygymroutine.persistence.routine.routineCreation.weekDayExerciseDetails.WeekDayExerciseDetailsService;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreationResponse;
import com.mygymroutine.persistence.workout.workoutCreation.WorkoutCreationService;

@Service
public class ExportPDFService {

    private WorkoutCreationService workoutExerciseService;
    private WeekDayExerciseDetailsService weekdayDetailsService;

    public ExportPDFService(WorkoutCreationService workoutExerciseService, WeekDayExerciseDetailsService weekdayDetailsService) {
        this.workoutExerciseService = workoutExerciseService;
        this.weekdayDetailsService = weekdayDetailsService;
    }

    public byte[] exportToPDFA4(String routineName, List<RoutineCreationResponse> routineWorkouts, boolean conPesos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        
        String logoImage = "src/main/resources/static/Logo-GymRuts-Negro-Horizontal.jpg";
        ImageData imageData = ImageDataFactory.create(logoImage);
        Image logo = new Image(imageData);
        logo.setFixedPosition(11, 770, 70);  
        document.add(logo);

        
        if (routineName != null && !routineName.isEmpty()) {
            document.setFont(PdfFontFactory.createFont("Helvetica-Oblique"));
            document.setFontSize(25);
            document.add(new Paragraph(routineName).setFixedPosition(100, 750, 400));
        }

        document.setFont(PdfFontFactory.createFont("Helvetica"));
        document.setFontSize(15);
        int startY = 700;

        for (RoutineCreationResponse routineWorkout : routineWorkouts) {
            if (routineWorkout.getWorkout().getWorkoutId() != null) {
                List<WorkoutCreationResponse> exercises = workoutExerciseService.getWorkoutExerciseByWorkoutId(routineWorkout.getWorkout().getWorkoutId());
                            
                Table table = new Table(new float[]{4, 4, 2});
                table.addHeaderCell(routineWorkout.getWorkout().getWorkoutName() + " | " + routineWorkout.getWeekday());
                table.addHeaderCell("Series / Reps");
                table.addHeaderCell("Peso");
                
                for (WorkoutCreationResponse exercise : exercises) {
                    WeekDayExerciseDetailsResponse weekdayDetails = weekdayDetailsService.findByWorkoutIdAndRoutineId(
                            exercise.getExercise().getExerciseId(),
                            routineWorkout.getRoutineId(),
                            routineWorkout.getWorkout().getWorkoutId()
                    );

                    Object[] tableRow;
                    if (conPesos) {
                        tableRow = new Object[]{
                                exercise.getExercise().getExerciseName(),
                                weekdayDetails.getSeries() + " series / " + weekdayDetails.getReps() + " reps",
                                weekdayDetails.getWeight() + " kg"
                        };
                    } else {
                        tableRow = new Object[]{
                                exercise.getExercise().getExerciseName(),
                                weekdayDetails.getSeries() + " series / " + weekdayDetails.getReps() + " reps",
                                " "
                        };
                    }

                    
                    for (Object cell : tableRow) {
                        table.addCell(cell.toString());
                    }
  
                    startY -= 40;  
                }
                
                document.add(table.setFixedPosition(20, startY, 550));
            }
        }

        document.close();
        
        return baos.toByteArray();
    }

}

