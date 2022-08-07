package com.example.music.filter;

import com.example.music.client.IdServiceClient;
import com.example.music.dto.VerificationRequestDTO;
import com.example.music.dto.VerificationResponseDTO;
import com.example.music.security.Authentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthFilter extends HttpFilter {
    //    private final AuthManager authManager;
    private final IdServiceClient idServiceClient;
    private final String appToken;

    public AuthFilter(
            final IdServiceClient idServiceClient,
            @Value("${app.token}") final String appToken) {
        this.idServiceClient = idServiceClient;
        this.appToken = appToken;
    }

    // log - logger
    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException, ServletException {
        final String token = req.getHeader("X-Token");
        log.debug("token: {}", token);

        if (token == null) {
            final Authentication authentication = Authentication.anonymous();
            req.setAttribute("authentication", authentication);
        } else {
            try {
                final VerificationResponseDTO responseDTO = idServiceClient.verify(
                        appToken,
                        new VerificationRequestDTO(token)
                );
                final Authentication authentication = new Authentication(
                        responseDTO.getId(), responseDTO.getRoles()
                );
                req.setAttribute("authentication", authentication);
            } catch (RuntimeException e) {
                // TODO: handle exception
                res.setStatus(401);
                res.getWriter().write("Not authenticated");
                return; // чтобы не попало в chain.doFilter
            }
        }

        chain.doFilter(req, res);
    }
}
