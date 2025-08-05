package com.example.Patient_Service.controller;

import com.example.Patient_Service.dto.PatientRequestDto;
import com.example.Patient_Service.dto.PatientResponseDto;
import com.example.Patient_Service.dto.groups.CreatePatientValidationGroup;
import com.example.Patient_Service.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDto> addPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody
            PatientRequestDto patientRequestDto){
        PatientResponseDto patientDto=patientService.addPatient(patientRequestDto);
        return ResponseEntity.ok(patientDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable UUID id,
            @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto updatedPatient=patientService.updatePatient(id,patientRequestDto);
        return ResponseEntity.ok(updatedPatient);
    }
}
