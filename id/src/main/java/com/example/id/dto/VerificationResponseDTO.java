package com.example.id.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerificationResponseDTO {
    private long id;
    private String login;
    private List<String> roles;
}
