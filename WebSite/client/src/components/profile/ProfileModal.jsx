import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import NavigationModal from '../authNavigationModal/NavigationModal ';
import SidebarModal from '../sidebar/SidebarModal';
import MainFeedModal from '../mainfeed/mainffeedmodal';
import styles from './ProfileModal.module.css';
import SettingsContainer from '../settings/settings-container/SettingsModal';

const ProfileModal = () => {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [settingsOpen, setSettingsOpen] = useState(false);

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const openSettings = () => {
    setSettingsOpen(true);
  };

  const closeSettings = () => {
    setSettingsOpen(false);
  };

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
          <h2>Kevin Anderson</h2>
          <h3>Web Designer</h3>
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
