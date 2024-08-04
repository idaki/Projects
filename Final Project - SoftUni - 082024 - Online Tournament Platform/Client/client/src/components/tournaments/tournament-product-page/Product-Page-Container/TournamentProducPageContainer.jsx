import React, { useState, useEffect } from "react";
import TournamentProductPageNavContainer from '../Nav-Container/TournamentProductPageNavContainer';
import TournamentProductPageInfoContainer from '../Info-Container/TournamentProductPageInfoContainer';
import TournamentProductPageGameContainer from '../Game-Container/TournamentProductPageGameContainer';
import TournamentProductPageTeamContainer from '../Team-Container/TournamentProductTeamContainer';
import { getAll as getTeams, getTeamsByTournamentId } from '../../../../services/teamService';
import { getCsrfToken, fetchCsrfToken } from '../../../../utils/csrfUtils';  


export default function TournamentProductPageContainer({ tournament }) {
  const [mainContent, setMainContent] = useState('info');
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    if (mainContent === 'teams') {
      fetchTeams();
    }
  }, [mainContent]);

  const fetchTeams = async () => {
    try {
      const csrfToken = getCsrfToken();
      if (!csrfToken) {
        await fetchCsrfToken();
      }
      const data = await getTeamsByTournamentId(tournament.id);
      setTeams(data);
    } catch (error) {
      console.error('Failed to fetch teams:', error);
    }
  };

  return (
    <div className="container mt-4">
      <TournamentProductPageNavContainer setMainContent={setMainContent} />
      <div className="mt-4">
        {mainContent === 'info' && <TournamentProductPageInfoContainer info={tournament} />}
        {mainContent === 'game' && <TournamentProductPageGameContainer tournament={tournament} />}
        {mainContent === 'teams' && <TournamentProductPageTeamContainer teams={teams} />}
      </div>
    </div>
  );
}