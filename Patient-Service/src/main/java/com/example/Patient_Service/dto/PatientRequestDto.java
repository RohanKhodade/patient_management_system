package com.example.Patient_Service.dto;

import com.example.Patient_Service.dto.groups.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {

    @NotBlank(message="Name is required")
    @Size(max=100,message="Name cannot exceed 100 chaacters")
    private String name;

    @NotBlank(message="email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message="address is required")
    private String address;

    @NotBlank(message =" Date of Birth is Required")
    private String dateOfBirth;

    @NotBlank(groups= CreatePatientValidationGroup.class, message="Registered date is required")
    private String registered_date;
}
