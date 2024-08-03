import React, { useState, useContext } from 'react';
import ContactDetailsModal from '../contact-details/ContactDetailsModal';
import ChangePasswordModal from '../change-password/ChangePasswordModal';
import AuthContext from '../../../context/authContext';
import { deleteUserById } from '../../../services/userDetailsService';

import { Modal, Button } from 'react-bootstrap'; // Correctly import Modal and Button
import {getCsrfToken} from '../../../utils/utils';
import {fetchCsrfToken} from '../../../utils/csrfUtils';
import { logout } from '../../../services/authService';

const SettingsContainer = ({ isOpen, toggle, userDetails }) => {
  const { setAuth } = useContext(AuthContext);

  const handleDelete = async () => {
    try {
      let csrfToken = getCsrfToken();
      console.log(csrfToken);
   if (!csrfToken) {
    csrfToken = await fetchCsrfToken();
    
  }
      
      if (userDetails && userDetails.id) {
        await deleteUserById(userDetails.id); // Pass the user ID to the delete function
        setAuth(null);
        localStorage.removeItem('authData');
        logout();
      } else {
        console.error('User ID is not available');
      }
    } catch (error) {
      console.error('Failed to delete user:', error);
    }
  };

  return (
    isOpen && (
      <div className="settings-container">
        <form className="file-upload">
          <div className="row mb-5 gx-5">
            <ContactDetailsModal userDetails={userDetails} />
          </div>
          <div className="row mb-5 gx-5">
            <ChangePasswordModal />
          </div>
          <div className="gap-3 d-flex justify-content-center text-center">
            <Button variant="danger" onClick={handleDelete}>Delete profile</Button>
            <Button variant="primary">Update profile</Button>
          </div>
        </form>
      </div>
    )
  );
};

export default SettingsContainer;