package com.example.id.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class AuthRequestDTO {
    @NotNull // не lombok! не javax.validation/jakarta.validation
    // Фридл - регулярные выражения
    @Pattern(regexp = "[A-Za-z0-9]{3,100}")
    private String login;
    @NotNull
    @Size(min = 8, max = 64)
    private String password;
}