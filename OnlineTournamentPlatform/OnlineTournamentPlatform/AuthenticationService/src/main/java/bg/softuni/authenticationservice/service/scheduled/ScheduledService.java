package bg.softuni.authenticationservice.service.scheduled;


import bg.softuni.authenticationservice.repositry.TokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ScheduledService {

    private final TokenRepository tokenRepository;

    public ScheduledService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(fixedRate = 1000*60*60*12) // Execute every 12 hours
    @Transactional
    public void doCleanInvalidTokensTask() {
        tokenRepository.deleteExpiredTokens();
        tokenRepository.deleteRevokedTokens();
    }
}
