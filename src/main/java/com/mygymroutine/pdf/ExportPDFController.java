package com.mygymroutine.pdf;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygymroutine.persistence.routine.RoutineService;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationResponse;
import com.mygymroutine.persistence.routine.routineCreation.RoutineCreationService;

@RestController
@RequestMapping("/api/pdf")
public class ExportPDFController {
	
    @Autowired
    private ExportPDFService exportPDFService;
    
    @Autowired
    private RoutineCreationService routineCreationService;
    
    @Autowired
    private RoutineService routineService;

    @PostMapping("/{routineId}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long routineId, @RequestBody boolean withWeights) {
        try {
            List<RoutineCreationResponse> routineWorkouts = routineCreationService.getRoutineWorkoutByRoutineId(routineId);
            String routineName = routineService.getRoutineById(routineId).get().getRoutineName();
            byte[] pdfContent = exportPDFService.exportToPDFA4(routineName, routineWorkouts, withWeights);

            ByteArrayResource resource = new ByteArrayResource(pdfContent);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + "rutina" + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    @PostMapping("/generated/{routineId}")
    public ResponseEntity<String> generatePdf(@PathVariable Long routineId, @RequestBody Blob withWeights) {
        String routineName = routineService.getRoutineById(routineId).get().getRoutineName();
        String fileName = routineName + ".pdf";
        String filePath = "/path/to/save/pdf/" + fileName;

        // Lógica para generar y guardar el PDF en filePath

        // Devolver la URL del archivo PDF generado
        String fileUrl = "http://tuservidor.com/path/to/save/pdf/" + fileName;
        return ResponseEntity.ok(fileUrl);
    }

}
