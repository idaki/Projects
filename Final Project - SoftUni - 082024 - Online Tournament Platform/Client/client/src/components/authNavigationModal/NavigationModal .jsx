import React from 'react';
import styles from './NavigationModal.module.css'; // Assuming NavigationModal.module.css is in the same directory

const NavigationModal = () => {
  return (
    <div className={styles.navigation}>
      <div className="nav flex-column">
        <a className="nav-link active" href="#">Firends</a>
        <a className="nav-link" href="#">My Tournaments</a>
        <a className="nav-link" href="#">My Teams</a>
        <a className="nav-link" href="#">My Favorites</a>
      </div>
    </div>
  );
};

export default NavigationModal;
