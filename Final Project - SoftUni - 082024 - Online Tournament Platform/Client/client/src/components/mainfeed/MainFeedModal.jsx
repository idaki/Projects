// src/components/mainfeed/MainFeedModal.jsx

import React, { useState, useEffect, useContext } from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import MyTournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import FriendsContainer from '../friends/FriendsContainer';
import TeamContainer from '../teams/TeamContainer';
import WatchlistContainer from '../tournaments/watchlist/WatchListContainer';
import CreateTournamentModal from '../tournaments/tournament-create/CreateTournamentModal';
import TournamentProductPageContainer from '../tournaments/tournament-product-page/TournamentProductPageContainer/TournamentProducPageContainer';
import ViewContext from '../../context/viewContext'; // Ensure correct import path
import EditUser from '../admin-panel/edit-users/EditUserModal';


export default function MainFeedModal() {
  const { mainContent, setMainContent } = useContext(ViewContext);
  const [reload, setReload] = useState(false);
  const [selectedTournament, setSelectedTournament] = useState(null);

  useEffect(() => {
    if (mainContent === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [mainContent]);

  const handleCreate = (tournament) => {
    console.log('Tournament created!', tournament);
    setSelectedTournament(tournament);
    setMainContent('tournament-page');
  };

  const handleClose = () => {
    setMainContent('tournaments'); // Switch to the tournament view
  };

  const handleLearnMore = (tournament) => {
    setSelectedTournament(tournament);
    setMainContent('tournament-page');
  };

  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{mainContent?.toUpperCase()}</h5>
        </div>
      </div>
      {mainContent === 'tournaments' && <MyTournamentsContainer onLearnMore={handleLearnMore} />}
      {mainContent === 'friends' && <FriendsContainer reload={reload} />}
      {mainContent === 'teams' && <TeamContainer />}
      {mainContent === 'watchlist' && <WatchlistContainer />}
      {mainContent === 'edit-users' && <EditUser />}
      {mainContent === 'create-tournament' && (
        <CreateTournamentModal onCreate={handleCreate} onClose={handleClose} />
      )}
      {mainContent === 'tournament-page' && selectedTournament && (
        <TournamentProductPageContainer tournament={selectedTournament} />
      )}
    </div>
  );
}
