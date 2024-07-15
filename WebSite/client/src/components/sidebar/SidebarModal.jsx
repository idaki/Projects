import React from 'react';
import styles from './SidebarModal.module.css'; // Assuming SidebarModal.module.css is in the same directory

const SidebarModal = ({ isOpen, toggle }) => {
  return (
    isOpen && (
      <div className={`${styles.sidebar} ${styles.popup}`}>
        <nav className="nav flex-column">
          <a className={`nav-link ${styles.navLink}`} href="#"><i className="bi bi-person"></i> Profile</a>
          <a className={`nav-link ${styles.navLink}`} href="#"><i className="bi bi-gear"></i> Settings</a>
          <a className={`nav-link ${styles.navLink}`} href="#"><i className="bi bi-question-circle"></i> Help</a>
          <a className={`nav-link ${styles.navLink}`} href="#"><i className="bi bi-box-arrow-right"></i> Logout</a>
        </nav>
      </div>
    )
  );
};

export default SidebarModal;
