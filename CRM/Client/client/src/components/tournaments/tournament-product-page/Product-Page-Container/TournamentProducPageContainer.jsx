import React, { useState, useEffect, useContext } from 'react';
import TournamentProductPageNavContainer from '../Nav-Container/TournamentProductPageNavContainer';
import TournamentProductPageInfoContainer from '../Info-Container/TournamentProductPageInfoContainer';
import TournamentProductPageGameContainer from '../Game-Container/TournamentProductPageGameContainer';
import TournamentProductPageTeamContainer from '../Team-Container/TournamentProductTeamContainer';
import { getTeamsByTournamentId } from '../../../../services/teamService';
import { getCsrfToken, fetchCsrfToken } from '../../../../utils/csrfUtils';
import AuthContext from '../../../../context/authContext';
import { signupForTournament } from '../../../../services/api/tournamentService';



export default function TournamentProductPageContainer({ tournament }) {
  const [mainContent, setMainContent] = useState('info');
  const [teams, setTeams] = useState([]);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const { auth } = useContext(AuthContext);

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

  const handleSignup = async (teamName) => {
    try {
      const response = await signupForTournament(tournament.id, teamName);
      console.log('Signup successful:', response);
      setShowCreateForm(false);
      // Optionally, fetch teams again to refresh the list
      fetchTeams();
    } catch (error) {
      console.error('Signup failed:', error);
    }
  };

  if (!tournament) {
    return <div>Loading...</div>; // Handle the case where tournament is undefined
  }

  return (
    <div className="container mt-4">
      <TournamentProductPageNavContainer setMainContent={setMainContent} />
    
      <div className="mt-4">
        {mainContent === 'info' && <TournamentProductPageInfoContainer info={tournament} />}
        {mainContent === 'game' && <TournamentProductPageGameContainer tournament={tournament} />}
        {mainContent === 'teams' && <TournamentProductPageTeamContainer teams={teams} />}
        
        {auth && (
          <div className="signup-section">
            {showCreateForm && <SignupTournamentForm closeForm={() => setShowCreateForm(false)} onSubmit={handleSignup} />}
            <button className="btn btn-primary mb-3" onClick={() => setShowCreateForm(true)}>
              Sign Up
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

function SignupTournamentForm({ closeForm, onSubmit }) {
  const [teamName, setTeamName] = useState('');

  const handleTeamNameChange = (e) => {
    setTeamName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(teamName);
  };

  return (
    <div className="signup-form mb-3">
      <h2>Signup for Tournament</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="teamName">Team Name</label>
          <input
            type="text"
            id="teamName"
            name="teamName"
            value={teamName}
            onChange={handleTeamNameChange}
            className="form-control"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
        <button type="button" className="btn btn-secondary" onClick={closeForm}>
          Cancel
        </button>
      </form>
    </div>
  );
}