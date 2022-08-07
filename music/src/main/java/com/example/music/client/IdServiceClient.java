package com.example.music.client;

import com.example.music.dto.VerificationRequestDTO;
import com.example.music.dto.VerificationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "id", url = "http://localhost:8001")
public interface IdServiceClient {
    @PostMapping("/auth/verify")
    VerificationResponseDTO verify(
            @RequestHeader ("X-Token") String serviceToken,
            @RequestBody VerificationRequestDTO requestDTO
    );
}
