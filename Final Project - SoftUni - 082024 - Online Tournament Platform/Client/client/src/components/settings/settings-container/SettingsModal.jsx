import React, { useState, useContext } from 'react';
import ContactDetailsModal from '../contact-details/ContactDetailsModal';
import ChangePasswordModal from '../change-password/ChangePasswordModal';
import AuthContext from '../../../context/authContext';
import { deleteUser } from '../../../services/userDetailsService';
import { Modal, Button } from 'react-bootstrap'; // Correctly import Modal and Button

const SettingsContainer = ({ isOpen, toggle, userDetails }) => {
  const { setAuth } = useContext(AuthContext);

  const handleDelete = async () => {
    try {
      await deleteUser();
      // Log out the user after successful deletion
      setAuth(null);
      localStorage.removeItem('authData');
    } catch (error) {
      console.error('Failed to delete user:', error);
    }
  };

  return (
    <Modal show={isOpen} onHide={toggle}>
      <Modal.Header closeButton>
        <Modal.Title>My Profile</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form className="file-upload">
          <div className="row mb-5 gx-5">
            {userDetails && <ContactDetailsModal userDetails={userDetails} />}
          </div>
          <div className="row mb-5 gx-5">
            <ChangePasswordModal />
          </div>
          <div className="gap-3 d-flex justify-content-center text-center">
            <Button variant="danger" onClick={handleDelete}>Delete profile</Button>
            <Button variant="primary">Update profile</Button>
            <Button variant="primary" onClick={toggle}>Close</Button>
          </div>
        </form>
      </Modal.Body>
    </Modal>
  );
};

export default SettingsContainer;
