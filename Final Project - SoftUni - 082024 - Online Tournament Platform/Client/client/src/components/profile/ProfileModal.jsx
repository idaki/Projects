import React, { useState, useEffect, useContext } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './ProfileModal.module.css';
import AuthContext from '../../context/authContext';
import ViewContext from '../../context/viewContext';
import { getUserDetails } from '../../services/userDetailsService';
import SidebarModal from '../sidebar/SidebarModal';
import MainFeedModal from '../mainfeed/MainFeedModal';
import AuthNavigationModal from '../authNavigationModal/AuthNavigationModal';
import {  getCsrfToken } from '../../utils/utils';

import {fetchCsrfToken} from '../../utils/csrfUtil';


const ProfileModal = () => {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [userProfile, setUserProfile] = useState(null);
  const [userDetails, setUserDetails] = useState(null);
  const [error, setError] = useState(null);
  const { auth, setAuth } = useContext(AuthContext);
  const { mainContent, setMainContent } = useContext(ViewContext);

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

  const openSettings = async () => {
    try {

      let csrfToken = getCsrfToken();
      console.log(csrfToken);
   if (!csrfToken) {
    csrfToken = await fetchCsrfToken();
    
  }
      const details = await getUserDetails();
      setUserDetails(details);
      setMainContent('settings');
    } catch (error) {
      console.error('Failed to fetch user details:', error);
    }
  };

  const handleLogout = () => {
    setAuth(null);
    setUserProfile(null);
    setUserDetails(null);
  };

  if (!auth) {
    return <div>Please log in to see your profile.</div>;
  }

  return (
    <div className="container-fluid">
      <div className={`row align-items-center ${styles.profileHeader}`}>
        <div className="col text-center">
          <img
            src={userProfile ? userProfile.avatar : "https://static.wikia.nocookie.net/felixthecat/images/4/49/Poindexter.gif/revision/latest?cb=20100428191852"}
            alt="Profile"
            className="rounded-circle"
            height="100"
            style={{ cursor: 'pointer' }}
            onClick={openSettings}
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
        <div className="col-md-3 bg-light">
          <AuthNavigationModal />
        </div>
        <div className="col-md-9">
          <SidebarModal isOpen={sidebarOpen} toggle={toggleSidebar} openSettings={openSettings} />
          <MainFeedModal userDetails={userDetails} />
        </div>
      </div>
    </div>
  );
};

export default ProfileModal;
