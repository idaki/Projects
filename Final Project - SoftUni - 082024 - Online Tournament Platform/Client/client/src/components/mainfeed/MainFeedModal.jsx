import React, { useState, useEffect } from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import TournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import FriendsContainer from '../friends/FriendsContainer';
import TeamContainer from '../teams/TeamContainer';

export default function MainFeedModal({ currentView }) {
  const [reload, setReload] = useState(false);

  useEffect(() => {
    if (currentView === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [currentView]);

  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{currentView.toUpperCase()}</h5>
        </div>
      </div>
      {currentView === 'tournaments' && <TournamentsContainer />}
      {currentView === 'friends' && <FriendsContainer reload={reload} />}
      {currentView === 'teams' && <TeamContainer />}
      {/* Add other views as needed */}
    </div>
  );
}
