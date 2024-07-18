package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.model.DTO.LoginDTO;
import bg.softuni.authenticationservice.model.DTO.UpdatePasswordDTO;
import bg.softuni.authenticationservice.service.JwtService;
import bg.softuni.authenticationservice.service.LoginService;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;
    private final JwtService jwtService;
    private final PasswordService passwordService;

    @Autowired
    public LoginController(LoginService loginService, JwtService jwtService, PasswordService passwordService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
        this.passwordService = passwordService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
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

    @PostMapping("/login-after-update")
    public ResponseEntity<?> loginAfterUpdate(@RequestBody UpdatePasswordDTO updatePasswordDTO, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            LOGGER.info("Attempting login with CSRF Token - {}={}", token.getHeaderName(), token.getToken());
        } else {
            LOGGER.warn("CSRF Token is missing in request");
        }

        if (!loginService.loginAfterPasswordUpdate(updatePasswordDTO)) {
            LOGGER.error("Invalid login attempt for token: {}", updatePasswordDTO.getToken());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or password");
        }
        Password password = passwordService.getUserByResetToken(updatePasswordDTO.getToken());
        System.out.println();
        String jwt = jwtService.generateTokenAfterPasswordUpdate(password.getUser().getUsername());

        LOGGER.info("Login successful, JWT Token generated");
        return ResponseEntity.ok(jwt);
    }
}
