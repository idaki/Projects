import React from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import TournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import FriendsContainer from '../friends/FriendsContainer'
import TeamContainer from '../teams/TeamContainer';


export default function MainFeedModal({ currentView }) {
 
 
  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{currentView.toUpperCase()}</h5>
        </div>
      </div>
      {currentView === 'tournaments' && <TournamentsContainer />}
      {currentView === 'friends' && <FriendsContainer />}
      {currentView === 'teams' && <TeamContainer />}
      {/* Add other views as needed */}
    </div>
  );
}