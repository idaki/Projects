package bg.softuni.tournamentservice.utils.events;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class UserDeletionListener {

    private static final Logger logger = LoggerFactory.getLogger(UserDeletionListener.class);

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Async
    @EventListener
    @Transactional
    public void handleUserDeletion(UserDeleteEvent event) {
        logger.debug("Received UserDeleteEvent for user: {}", event.getUser().getUsername());

        User user = event.getUser();
        Long userId = user.getId();
        String username = user.getUsername();

        // Handle tournaments where the user is a manager
        List<Tournament> managedTournaments = tournamentRepository.findByManagerId(userId);
        for (Tournament tournament : managedTournaments) {
            logger.debug("Deleting tournament: {}", tournament.getName());
            tournamentRepository.delete(tournament);
        }

        // Handle teams where the user is a manager
        List<Team> managedTeams = teamRepository.findByManagerId(userId);
        for (Team team : managedTeams) {
            logger.debug("Deleting team: {}", team.getName());
            teamRepository.delete(team);
        }

        // Handle teams where the user is a member
        List<Team> userTeams = teamRepository.findByUsersId(userId);
        for (Team team : userTeams) {
            team.getUsers().removeIf(teamUser -> teamUser.getId().equals(userId));
            if (team.getUsers().isEmpty()) {
                logger.debug("Deleting team with no members: {}", team.getName());
                teamRepository.delete(team);
            } else {
                teamRepository.save(team);
            }
        }

        // Handle tournaments where the user is a follower
        List<Tournament> followedTournaments = tournamentRepository.findByFollowersUsername(username);
        for (Tournament tournament : followedTournaments) {
            tournament.getFollowers().removeIf(follower -> follower.getUsername().equals(username));
            tournamentRepository.save(tournament);
        }

        // Finally, delete the user
        userRepository.deleteById(userId);
    }
}
