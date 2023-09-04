package com.example.fitApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPlanDTO {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Email
    private String email;
}
