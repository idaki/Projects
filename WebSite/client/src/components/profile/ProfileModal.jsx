import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import NavigationModal from '../authNavigationModal/NavigationModal ';
import SidebarModal from '../sidebar/SidebarModal'; // Assuming SidebarModal is in the same directory as ProfileModal
import MainFeedModal from '../mainfeed/mainffeedmodal'; // Assuming MainFeedModal is in the same directory as ProfileModal
import styles from './ProfileModal.module.css'; // Use relative path

const ProfileModal = () => {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
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
          <SidebarModal isOpen={sidebarOpen} toggle={toggleSidebar} />
          <MainFeedModal />
        </div>
      </div>
    </div>
  );
};

export default ProfileModal;
