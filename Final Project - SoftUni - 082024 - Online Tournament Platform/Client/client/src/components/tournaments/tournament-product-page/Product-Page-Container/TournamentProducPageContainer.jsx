import React, { useState, useEffect } from "react";
import TournamentProductPageNavContainer from '../Nav-Container/TournamentProductPageNavContainer';
import TournamentProductPageInfoContainer from '../Info-Container/TournamentProductPageInfoContainer';
import TournamentProductPageGameContainer from '../Game-Container/TournamentProductPageGameContainer';
import TournamentProductPageTeamContainer from '../Team-Container/TournamentProduactTeamContainer';
import { getTournamentById } from '../../../../services/api/tournamentService';



export default function TournamentProductPageContainer({ tournament }) {
  const [mainContent, setMainContent] = useState('info');

  return (
    <div className="container mt-4">
      <TournamentProductPageNavContainer setMainContent={setMainContent} />
      <div className="mt-4">
        {mainContent === 'info' && <TournamentProductPageInfoContainer info={tournament} />}
        {mainContent === 'game' && <TournamentProductPageGameContainer tournament={tournament} />}
        {mainContent === 'teams' && <TournamentProductPageTeamContainer teams={tournament.teams} />}
      </div>
    </div>
  );
}