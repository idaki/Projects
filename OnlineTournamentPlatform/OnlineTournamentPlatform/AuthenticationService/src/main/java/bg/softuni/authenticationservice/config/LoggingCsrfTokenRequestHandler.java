package bg.softuni.authenticationservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;

import java.io.IOException;
import java.util.function.Supplier;

public class LoggingCsrfTokenRequestHandler implements CsrfTokenRequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingCsrfTokenRequestHandler.class);


    public void handle(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Supplier<CsrfToken> csrfTokenSupplier) throws ServletException, IOException {
        CsrfToken csrfToken = csrfTokenSupplier.get();
        if (csrfToken != null) {
            LOGGER.info("CSRF Token in request: {}={}", csrfToken.getHeaderName(), csrfToken.getToken());
        } else {
            LOGGER.warn("CSRF Token is missing in request");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfTokenSupplier) {
        CsrfToken csrfToken = csrfTokenSupplier.get();
        if (csrfToken != null) {
            LOGGER.info("CSRF Token in request: {}={}", csrfToken.getHeaderName(), csrfToken.getToken());
        } else {
            LOGGER.warn("CSRF Token is missing in request");
        }
    }

    @Override
    public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
        return csrfToken != null ? csrfToken.getToken() : null;
    }


    public void storeToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        // Implementation for storing token (if needed)
    }

  
    public CsrfToken generateToken(HttpServletRequest request) {
        // Implementation for generating token (if needed)
        return null;
    }
}
