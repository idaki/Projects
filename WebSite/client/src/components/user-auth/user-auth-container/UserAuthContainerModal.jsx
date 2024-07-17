// UserAuthContainerModal.jsx
import React, { useState } from 'react';
import Modal from 'react-modal';
import LoginModal from '../login/LoginModal';
import RegisterModal from '../register/RegisterModal';
import ResetPasswordModal from '../reset-password/ResetPasswordModal';
import NewPasswordModal from '../update-password/UpdatePasswordModal';
import styles from './UserAuthContainerModal.module.css';

// Set the element that the modal is attached to. This helps in accessibility by managing focus.
Modal.setAppElement('#root');

export default function UserAuthContainerModal({ onClose }) {
    const [view, setView] = useState('login');

    // Each of these handlers updates the state to show the respective modal view.
    const handleRegisterClick = () => {
        setView('register');
    };

    const handleResetPasswordClick = () => {
        setView('resetPassword');
    };

    const handleNewPasswordClick = () => {
        setView('newPassword');
    };

    // Resets view and calls onClose prop, allowing the parent component to handle additional cleanup.
    const handleClose = () => {
        setView(''); // Reset view to clear modal content
        onClose();  // Optionally handle additional cleanup
    };

    return (
        <Modal
          isOpen={view !== ''}
          onRequestClose={handleClose}
          contentLabel="User Authentication"
          className={styles.modalContent}
          overlayClassName={styles.overlay}
          shouldCloseOnOverlayClick={true}
        >
          <div className={styles.modalContent}>
            {view === 'login' && (
              <LoginModal
                onClose={handleClose}
                onRegisterClick={handleRegisterClick}
                onForgotPasswordClick={handleResetPasswordClick}
                onChangePasswordClick={handleNewPasswordClick} // Trigger for New Password Modal
              />
            )}
            {view === 'register' && <RegisterModal onClose={handleClose} />}
            {view === 'resetPassword' && <ResetPasswordModal onClose={handleClose} />}
            {view === 'newPassword' && <NewPasswordModal onClose={handleClose} />}
          </div>
        </Modal>
    );
}
