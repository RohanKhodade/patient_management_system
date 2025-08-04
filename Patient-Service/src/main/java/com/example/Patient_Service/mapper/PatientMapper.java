package com.example.Patient_Service.mapper;

import com.example.Patient_Service.dto.PatientRequestDto;
import com.example.Patient_Service.dto.PatientResponseDto;
import com.example.Patient_Service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto patientDto=new PatientResponseDto();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName().toString());
        patientDto.setEmail(patient.getEmail().toString());
        patientDto.setAddress(patient.getAddress().toString());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDto;
    }
    public static Patient toPatient(PatientRequestDto patientRequestDto){
        Patient patient =new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegistered_date(LocalDate.parse(patientRequestDto.getRegistered_date()));
        return patient;
    }
}
