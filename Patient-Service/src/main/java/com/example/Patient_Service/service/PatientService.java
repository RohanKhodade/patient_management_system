package com.example.Patient_Service.service;

import com.example.Patient_Service.dto.PatientRequestDto;
import com.example.Patient_Service.dto.PatientResponseDto;
import com.example.Patient_Service.mapper.PatientMapper;
import com.example.Patient_Service.model.Patient;
import com.example.Patient_Service.repo.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    public List<PatientResponseDto> getPatients(){
        List< Patient> patients=patientRepository.findAll();
        List<PatientResponseDto> patientResponseDtos=patients.stream()
                .map(patient-> PatientMapper.toDto(patient)).toList();
        return patientResponseDtos;
    }
    public PatientResponseDto addPatient(PatientRequestDto patientRequestDto){
        Patient patient=PatientMapper.toPatient(patientRequestDto);
        patientRepository.save(patient);
        return PatientMapper.toDto(patient);
    }
}
