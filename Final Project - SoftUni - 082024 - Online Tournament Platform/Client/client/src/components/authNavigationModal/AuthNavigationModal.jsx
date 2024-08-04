import React, { useContext } from 'react';
import styles from './AuthNavigationModal.module.css';
import ViewContext from '../../context/viewContext';
import AuthContext from '../../context/authContext';

export default function AuthNavigationModal() {
  const { setMainContent } = useContext(ViewContext);
  const { auth } = useContext(AuthContext);

  if (!auth || !auth.roles) {
    return <p>Unauthorized</p>;
  }

  const handleLinkClick = (content) => {
    console.log(`Navigating to: ${content}`); // Debugging: log the navigation content
    setMainContent(content);
  };

  const renderAdminUserLinks = () => (
    <>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('friends')}>Friends</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('tournaments')}>My Tournaments</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('teams')}>My Teams</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('watchlist')}>Watchlist</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('create-tournament')}>Create Tournament</a>
    </>
  );

  const renderAdminSuperLinks = () => (
    <>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => handleLinkClick('edit-users')}>Edit Users</a>
    </>
  );

  const renderDefaultLinks = () => (
    <h1>Oh no. We've got an issue. Hold on please!</h1>
  );

  return (
    <div className="nav flex-column">
      {auth.roles.includes('ROLE_ADMIN_USER') && renderAdminUserLinks()}
      {auth.roles.includes('ROLE_ADMIN_SUPER') && renderAdminSuperLinks()}
      {!auth.roles.includes('ROLE_ADMIN_USER') && !auth.roles.includes('ROLE_ADMIN_SUPER') && renderDefaultLinks()}
    </div>
  );
}