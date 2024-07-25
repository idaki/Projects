package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.authenticationservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;

@RestController
@RequestMapping("/api")

public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;
    private final JwtService jwtService;

    public LoginController(LoginService loginService, JwtService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (token != null) {
            LOGGER.debug("CSRF Token in request: {}", token.getToken());
            LOGGER.info("Attempting login with CSRF Token - {}={}", token.getHeaderName(), token.getToken());
        } else {
            LOGGER.warn("CSRF Token is missing in request");
        }

        if (!loginService.login(loginDTO)) {
            LOGGER.error("Invalid login attempt for username: {}", loginDTO.getUsernameOrEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username/email or password");
        }

        String jwt = jwtService.generateToken(loginDTO.getUsernameOrEmail());
        LOGGER.info("Login successful, JWT Token generated");

        return ResponseEntity.ok(jwt);
    }
}
