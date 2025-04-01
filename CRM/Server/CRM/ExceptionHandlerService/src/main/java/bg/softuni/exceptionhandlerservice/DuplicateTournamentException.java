package bg.softuni.exceptionhandlerservice;


public class DuplicateTournamentException extends RuntimeException {
    public DuplicateTournamentException(String message) {
        super(message);
    }
}