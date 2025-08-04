package com.example.Patient_Service.controller;

import com.example.Patient_Service.dto.PatientRequestDto;
import com.example.Patient_Service.dto.PatientResponseDto;
import com.example.Patient_Service.service.PatientService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> patients=patientService.getPatients();
        return ResponseEntity.ok(patients);
    }
    @PostMapping
    public ResponseEntity<PatientResponseDto> addPatient(@Valid
                                                         @RequestBody
                                                         PatientRequestDto patientRequestDto){
        PatientResponseDto patientDto=patientService.addPatient(patientRequestDto);
        return ResponseEntity.ok(patientDto);
    }
}
