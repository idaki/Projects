// src/components/authNavigationModal/AuthNavigationModal.jsx

import React, { useContext } from 'react';
import styles from './AuthNavigationModal.module.css';
import ViewContext from '../../context/viewContext'; // Ensure correct import path

export default function AuthNavigationModal() {
  const { setMainContent } = useContext(ViewContext);

  return (
    
      <div className="nav flex-column ">
        <a className={`nav-link active ${styles.navLink}`} href="#" onClick={() => setMainContent('friends')}>Friends</a>
        <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('tournaments')}>My Tournaments</a>
        <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('teams')}>My Teams</a>
        <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('watchlist')}>Watchlist</a>
        <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('create-tournament')}>Create Tournament</a>
      </div>
   
  );
}