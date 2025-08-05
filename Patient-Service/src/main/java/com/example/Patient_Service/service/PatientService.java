package com.example.Patient_Service.service;

import com.example.Patient_Service.dto.PatientRequestDto;
import com.example.Patient_Service.dto.PatientResponseDto;
import com.example.Patient_Service.exception.EmailAlreadyExistsException;
import com.example.Patient_Service.exception.PatientNotFoundException;
import com.example.Patient_Service.mapper.PatientMapper;
import com.example.Patient_Service.model.Patient;
import com.example.Patient_Service.repo.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists "
                    + patientRequestDto.getEmail()
            );
        }
        Patient patient=PatientMapper.toPatient(patientRequestDto);
        patientRepository.save(patient);
        return PatientMapper.toDto(patient);
    }
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new PatientNotFoundException("Patient Not found with id " +id));
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        Patient updatedPatient=patientRepository.save(patient);

        return PatientMapper.toDto(updatedPatient);
    }
}
