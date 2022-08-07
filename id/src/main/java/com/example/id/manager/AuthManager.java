package com.example.id.manager;

import com.example.id.dto.AuthRequestDTO;
import com.example.id.dto.AuthResponseDTO;
import com.example.id.dto.VerificationRequestDTO;
import com.example.id.dto.VerificationResponseDTO;
import com.example.id.entity.TokenEntity;
import com.example.id.entity.UserEntity;
import com.example.id.exception.ForbiddenException;
import com.example.id.exception.TokenNotFoundException;
import com.example.id.exception.UserLoginNotFoundException;
import com.example.id.exception.UserPasswordNotMatchesException;
import com.example.id.repository.TokenRepository;
import com.example.id.repository.UserRepository;
import com.example.id.security.Authentication;
import com.example.id.security.Roles;
import com.example.id.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
@RequiredArgsConstructor
public class AuthManager {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public AuthResponseDTO auth(final Authentication authentication, final AuthRequestDTO requestDTO) {
        // TODO: ROLE_ANONYMOUS

        final UserEntity userEntity = userRepository.findByLogin(requestDTO.getLogin())
                .orElseThrow(UserLoginNotFoundException::new);
        if (!passwordEncoder.matches(requestDTO.getPassword(), userEntity.getPassword())) {
            throw new UserPasswordNotMatchesException();
        }

        final String token = tokenGenerator.generate();

        final TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .user(userEntity)
                .expire(Instant.now().plus(1, ChronoUnit.HOURS))
                .build();
        tokenRepository.save(tokenEntity);

        return new AuthResponseDTO(token);
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void removeOldTokens() {
        tokenRepository.deleteByExpireBefore(Instant.now());
    }

    public Authentication authenticateByToken(final String token) {
        final TokenEntity tokenEntity = tokenRepository.findById(token)
                .orElseThrow(TokenNotFoundException::new);
        final UserEntity userEntity = tokenEntity.getUser();
        return Authentication.builder()
                .id(userEntity.getId())
                .roles(new ArrayList<>(userEntity.getRoles()))
                .build();
    }

    public VerificationResponseDTO verify(final Authentication authentication, final VerificationRequestDTO requestDTO) {
        if (!authentication.hasRole(Roles.ROLE_SERVICE)) {
            throw new ForbiddenException();
        }

        final TokenEntity tokenEntity = tokenRepository.findById(requestDTO.getToken())
                .orElseThrow(TokenNotFoundException::new);
        final UserEntity userEntity = tokenEntity.getUser();
        return new VerificationResponseDTO(userEntity.getId(), userEntity.getLogin(), userEntity.getRoles());
    }
}
