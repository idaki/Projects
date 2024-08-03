import React, { useState, useEffect } from "react";
import TournamentProductPageNavContainer from '../Nav-Container/TournamentProductPageNavContainer';
import TournamentProductPageInfoContainer from '../Info-Container/TournamentProductPageInfoContainer';
import TournamentProductPageGameContainer from '../Game-Container/TournamentProductPageGameContainer';
import TournamentProductPageTeamContainer from '../Team-Container/TournamentProduactTeamContainer';



export default function TournamentProductPageContainer({ tournamentId }) {
  const [mainContent, setMainContent] = useState('info');
  const [tournament, setTournament] = useState({
    info: { details: "This is a grand tournament with participants from around the world. It features several exciting matches, thrilling gameplay, and an engaging audience experience." },
    game: { title: "Example Game Title", description: "This game is an action-packed adventure with stunning visuals and immersive gameplay.", imageUrl: "https://via.placeholder.com/150" },
    teams: [{ name: "Team Alpha" }, { name: "Team Bravo" }, { name: "Team Charlie" }, { name: "Team Delta" }]
  });

  return (
    <div className="container">
      <TournamentProductPageNavContainer setMainContent={setMainContent} />
      <div className="mt-4">
        {mainContent === 'info' && <TournamentProductPageInfoContainer info={tournament.info} />}
        {mainContent === 'game' && <TournamentProductPageGameContainer game={tournament.game} />}
        {mainContent === 'teams' && <TournamentProductPageTeamContainer teams={tournament.teams} />}
      </div>
    </div>
  );
}