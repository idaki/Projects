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

  const renderAdminUserLinks = () => (
    <>
      <a className={`nav-link active ${styles.navLink}`} href="#" onClick={() => setMainContent('friends')}>Friends</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('tournaments')}>My Tournaments</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('teams')}>My Teams</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('watchlist')}>Watchlist</a>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('create-tournament')}>Create Tournament</a>
    </>
  );

  const renderAdminSuperLinks = () => (
    <>
      <a className={`nav-link ${styles.navLink}`} href="#" onClick={() => setMainContent('edit-users')}>Edit Users</a>
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