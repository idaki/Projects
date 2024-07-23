package bg.softuni.authenticationservice.contoller;

import bg.softuni.authenticationservice.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogoutController {
    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response,
            @AuthenticationPrincipal Authentication authentication
    ) {
        this.logoutService.logout(request, response, authentication);
        return "Logged out successfully";
    }
}