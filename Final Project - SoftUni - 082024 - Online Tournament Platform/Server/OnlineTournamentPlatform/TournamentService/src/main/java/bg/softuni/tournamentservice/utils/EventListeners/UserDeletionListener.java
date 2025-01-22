package bg.softuni.tournamentservice.utils.EventListeners;

import bg.softuni.userservice.models.entity.user.User;

import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.Team;
import bg.softuni.tournamentservice.repository.TournamentRepository;
import bg.softuni.tournamentservice.repository.TeamRepository;
import bg.softuni.userservice.utils.events.UserDeleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDeletionListener {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @EventListener
    public void handleUserDeletion(UserDeleteEvent event) {
        User user = event.getUser();
        Long userId = user.getId();
        String username = user.getUsername();

        // Handle tournaments where the user is a manager
        List<Tournament> managedTournaments = tournamentRepository.findByManagerId(userId);
        for (Tournament tournament : managedTournaments) {
            tournament.setManager(null);
            tournamentRepository.save(tournament);
        }

        // Handle tournaments where the user is a follower
        List<Tournament> followedTournaments = tournamentRepository.findByFollowersUsername(username);
        for (Tournament tournament : followedTournaments) {
            tournament.getFollowers().removeIf(follower -> follower.getUsername().equals(username));
            tournamentRepository.save(tournament);
        }

        // Handle teams where the user is a member
        List<Team> userTeams = teamRepository.findByUsersId(userId);
        for (Team team : userTeams) {
            team.getUsers().removeIf(teamUser -> teamUser.getId().equals(userId));
            teamRepository.save(team);
        }
    }
}
