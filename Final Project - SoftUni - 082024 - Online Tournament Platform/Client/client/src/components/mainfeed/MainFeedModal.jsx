import React, { useState, useEffect } from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import MyTournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import FriendsContainer from '../friends/FriendsContainer';
import TeamContainer from '../teams/TeamContainer';
import WatchlistContainer from '../tournaments/watchlist/WatchListContainer';
import CreateTournamentModal from '../tournaments/tournament-create/CreateTournamentModal';
import TournamentProductPageContainer from '../tournaments/tournament-product-page/TournamentProductPageContainer/TournamentProducPageContainer';

export default function MainFeedModal({ currentView, setMainContent }) {
  const [reload, setReload] = useState(false);
  const [selectedTournament, setSelectedTournament] = useState(null);

  useEffect(() => {
    if (currentView === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [currentView]);

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
          <h5 className="card-title">{currentView.toUpperCase()}</h5>
        </div>
      </div>
      {currentView === 'tournaments' && <MyTournamentsContainer onLearnMore={handleLearnMore} />}
      {currentView === 'friends' && <FriendsContainer reload={reload} />}
      {currentView === 'teams' && <TeamContainer />}
      {currentView === 'watchlist' && <WatchlistContainer />}
      {currentView === 'create-tournament' && (
        <CreateTournamentModal onCreate={handleCreate} onClose={handleClose} />
      )}
      {currentView === 'tournament-page' && selectedTournament && (
        <TournamentProductPageContainer tournament={selectedTournament} />
      )}
    </div>
  );
}