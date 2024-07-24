import React from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import TournamentsContainer from '../tournament-container/TournamentsContainer';
import FriendsModal from '../friends/friends-container/FriendsModal';

export default function MainFeedModal({ currentView }) {
  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{currentView.toUpperCase()}</h5>
        </div>
      </div>
      {currentView === 'tournaments' && <TournamentsContainer />}
      {currentView === 'friends' && <FriendsModal />}
      {/* Add other views as needed */}
    </div>
  );
}