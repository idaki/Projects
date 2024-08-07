package bg.softuni.tournamentservice.service;


public class DuplicateTournamentException extends RuntimeException {
    public DuplicateTournamentException(String message) {
        super(message);
    }
}