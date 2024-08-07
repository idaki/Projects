package bg.softuni.authenticationservice.service.impl;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.repository.TokenRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogoutServiceImplTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private LogoutServiceImpl logoutService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    private Token token;

    @BeforeEach
    public void setUp() {
        // Create a mock Token
        token = new Token();
        token.setToken("sampleToken");
        token.setExpired(false);
        token.setRevoked(false);
    }

    @Test
    public void testLogout_NoAuthorizationHeader() {
        when(request.getHeader("Authorization")).thenReturn(null);

        logoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any(Token.class));
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void testLogout_InvalidAuthorizationHeader() {
        when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

        logoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any(Token.class));
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void testLogout_TokenFound() {
        when(request.getHeader("Authorization")).thenReturn("Bearer sampleToken");
        when(tokenRepository.findByToken("sampleToken")).thenReturn(Optional.of(token));

        logoutService.logout(request, response, authentication);

        verify(tokenRepository).findByToken("sampleToken");
        verify(tokenRepository).save(token);
        assertThat(token.isExpired()).isTrue();
        assertThat(token.isRevoked()).isTrue();
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void testLogout_TokenNotFound() {
        when(request.getHeader("Authorization")).thenReturn("Bearer sampleToken");
        when(tokenRepository.findByToken("sampleToken")).thenReturn(Optional.empty());

        logoutService.logout(request, response, authentication);

        verify(tokenRepository).findByToken("sampleToken");
        verify(tokenRepository, never()).save(any(Token.class));
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}
