package com.example.music.controller;

import com.example.music.dto.MusicResponseDTO;
import com.example.music.security.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @GetMapping
    public List<MusicResponseDTO> getAll(
            @RequestAttribute final Authentication authentication
    ) {
       return Collections.emptyList();
    }
}
