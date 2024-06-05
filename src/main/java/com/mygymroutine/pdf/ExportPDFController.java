package com.mygymroutine.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    private final Map<String, byte[]> tempFileMap = new HashMap<>();
    
//    @PostMapping("/{routineId}")
//    public ResponseEntity<String> downloadPdf(@PathVariable Long routineId, @RequestBody boolean withWeights) {
//        try {
//            List<RoutineCreationResponse> routineWorkouts = routineCreationService.getRoutineWorkoutByRoutineId(routineId);
//            String routineName = routineService.getRoutineById(routineId).get().getRoutineName();
//            byte[] pdfContent = exportPDFService.exportToPDFA4(routineName, routineWorkouts, withWeights);
//
//            String uniqueId = UUID.randomUUID().toString();
//            String tempFilePath = generateTempPdfFile(pdfContent, uniqueId);
//
//            String url = "pdf/download-pdf/" + uniqueId;
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(url);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    } 
//    
//    private String generateTempPdfFile(byte[] pdfContent, String uniqueId) throws IOException {
//
//        String tempFileName = uniqueId + ".pdf";
//        Path tempFilePath = Paths.get(tempFileName); 
//        tempFileMap.put(uniqueId, pdfContent);
//        Files.write(tempFilePath, pdfContent);
//
//        return tempFilePath.toString(); 
//    }
//
//    @GetMapping("/download-pdf/{uniqueId}")
//    public ResponseEntity<Resource> downloadPdfFromUrl(@PathVariable String uniqueId) {
//        try {
//            if (!tempFileMap.containsKey(uniqueId)) {
//                return ResponseEntity.notFound().build();
//            }
//
//            byte[] tempFilePath = tempFileMap.get(uniqueId);
//
//            ByteArrayResource resource = new ByteArrayResource(tempFilePath);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;");
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(resource);
//        } finally {
//            tempFileMap.remove(uniqueId);
//        }
//    }
    
    @PostMapping()
    public ResponseEntity<String> downloadPdf(@RequestBody byte[] pdf) {
        try {
            String uniqueId = UUID.randomUUID().toString();
            String tempFilePath = generateTempPdfFile(pdf, uniqueId);

            String url = "pdf/download-pdf/" + uniqueId;

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(url);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } 
    
    private String generateTempPdfFile(byte[] pdfContent, String uniqueId) throws IOException {

        String tempFileName = uniqueId + ".pdf";
        Path tempFilePath = Paths.get(tempFileName); 
        tempFileMap.put(uniqueId, pdfContent);
        Files.write(tempFilePath, pdfContent);

        return tempFilePath.toString(); 
    }

    @GetMapping("/download-pdf/{uniqueId}")
    public ResponseEntity<Resource> downloadPdfFromUrl(@PathVariable String uniqueId) {
        try {
            if (!tempFileMap.containsKey(uniqueId)) {
                return ResponseEntity.notFound().build();
            }

            byte[] tempFilePath = tempFileMap.get(uniqueId);

            ByteArrayResource resource = new ByteArrayResource(tempFilePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } finally {
            tempFileMap.remove(uniqueId);
        }
    }
    
 

}
