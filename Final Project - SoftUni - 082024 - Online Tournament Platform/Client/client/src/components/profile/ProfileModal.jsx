import React, { useState, useEffect, useContext } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import SidebarModal from '../sidebar/SidebarModal';
import MainFeedModal from '../../components/mainfeed/mainffeedmodal';
import styles from './ProfileModal.module.css';
import SettingsContainer from '../../components/settings/settings-container/SettingsModal';
import NavigationModal from '../../components/authNavigationModal/NavigationModal ';
import AuthContext from '../../context/authContext';
import { getUserDetails } from '../../services/userDetailsService';
import FriendsModal from '../friends/friends-container/FriendsModal';

const ProfileModal = () => {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [settingsOpen, setSettingsOpen] = useState(false);
  const [userProfile, setUserProfile] = useState(null);
  const [error, setError] = useState(null);
  const { auth } = useContext(AuthContext);

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const profile = await getUserDetails();
        setUserProfile(profile);
      } catch (error) {
        setError('Failed to fetch user profile');
      }
    };

    if (auth && auth.accessToken) {
      fetchUserProfile();
    }
  }, [auth]);

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const openSettings = () => {
    setSettingsOpen(true);
  };

  const closeSettings = () => {
    setSettingsOpen(false);
  };

  if (!auth) {
    return <div>Please log in to see your profile.</div>;
  }

  return (
    <div className="container-fluid">
      <div className={`row align-items-center ${styles.profileHeader}`}>
        <div className="col text-center">
          <img
            src="https://static.wikia.nocookie.net/felixthecat/images/4/49/Poindexter.gif/revision/latest?cb=20100428191852"
            alt="Profile"
            className="rounded-circle"
            height="100"
            onClick={toggleSidebar}
            style={{ cursor: 'pointer' }}
          />
          {userProfile ? (
            <h2>{userProfile.username}</h2>
          ) : (
            <p>Loading...</p>
          )}
          {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
      </div>

      <div className="row">
        <div className="col-md-3">
          <NavigationModal />
        </div>
        <div className="col-md-9">
         <SidebarModal isOpen={sidebarOpen} toggle={toggleSidebar} openSettings={openSettings} />
          {!settingsOpen && <MainFeedModal />}
          <SettingsContainer isOpen={settingsOpen} toggle={closeSettings} />
         
        </div>
      </div>
    </div>
  );
};

export default ProfileModal;