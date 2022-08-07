package com.example.id.controller;

import com.example.id.dto.UserRequestDTO;
import com.example.id.dto.UserResponseDTO;
import com.example.id.manager.UserManager;
import com.example.id.security.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor // генерирует конструктор только для final non-static полей
public class UserController {
    private final UserManager manager;

    @GetMapping("/users")
    public List<UserResponseDTO> getAll(
            @RequestAttribute final Authentication authentication
    ) {
        final List<UserResponseDTO> responseDTO = manager.getAll(authentication);
        return responseDTO;
    }

    // TODO: http://localhost:8080/users/1
    // ConstraintViolationException
    @GetMapping("/users/{id}")
    public UserResponseDTO getById(
            @RequestAttribute final Authentication authentication,
            @Min(1) @PathVariable final long id
    ) {
        final UserResponseDTO responseDTO = manager.getById(authentication, id);
        return responseDTO;
    }

    @PostMapping("/users")
    public UserResponseDTO create(
            @RequestAttribute final Authentication authentication,
            @Valid @RequestBody final UserRequestDTO requestDTO
    ) {
        final UserResponseDTO responseDTO = manager.create(authentication, requestDTO);
        return responseDTO;
    }

    @PutMapping("/users")
    public UserResponseDTO update(
            @RequestAttribute final Authentication authentication,
            @Valid @RequestBody final UserRequestDTO requestDTO
    ) {
        final UserResponseDTO responseDTO = manager.update(authentication, requestDTO);
        return responseDTO;
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(
            @RequestAttribute final Authentication authentication,
            @Min(1) @PathVariable final long id
    ) {
        manager.deleteById(authentication, id);
    }
}
