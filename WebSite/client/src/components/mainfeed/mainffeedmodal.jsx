import React from 'react';
import styles from './MainFeedModal.module.css'; // Assuming MainFeedModal.module.css is in the same directory
import TournamentsContainer from '../tournament-container/TournamentsContainer';

const MainFeedModal = () => {
  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">TOURNAMENTS</h5>
        </div>
      </div>
      <TournamentsContainer/>
    </div>
  );
};

export default MainFeedModal;
