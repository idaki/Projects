import React, { useState, useEffect, useContext } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './ProfileModal.module.css';
import AuthContext from '../../context/authContext';
import ViewContext from '../../context/viewContext';
import { getUserDetails } from '../../services/userDetailsService';
import SidebarModal from '../sidebar/SidebarContainer';
import MainFeedContainer from '../mainfeed/MainFeedContainer';
import AuthNavigationModal from '../authNavigationModal/AuthNavigationModal';
import { getCsrfToken } from '../../utils/utils';
import { fetchCsrfToken } from '../../utils/csrfUtils';
import Sidebar from '../sidebar/SidebarContainer';

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
      

      <div className="row">
    
          <Sidebar/>
       
          <MainFeedContainer userDetails={userDetails} />
      
      </div>
    </div>
  );
};

export default ProfileModal;
