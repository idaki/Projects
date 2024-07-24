import React from 'react';
import styles from './AuthNavigationModal.module.css'; // Assuming NavigationModal.module.css is in the same directory

export default function AuthNavigationModal({ setMainContent }) {
  return (
    <div className={styles.navigation}>
      <div className="nav flex-column">
        <a className="nav-link active" href="#" onClick={() => setMainContent('friends')}>Friends</a>
        <a className="nav-link" href="#" onClick={() => setMainContent('tournaments')}>My Tournaments</a>
        <a className="nav-link" href="#">My Teams</a>
        <a className="nav-link" href="#">My Favorites</a>
      </div>
    </div>
  );
}