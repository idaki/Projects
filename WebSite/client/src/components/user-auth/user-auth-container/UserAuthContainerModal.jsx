import React, { useState } from 'react';
import Modal from 'react-modal';
import LoginModal from '../login/LoginModal';
import RegisterModal from '../register/RegisterModal';
import ResetPasswordModal from '../reset-password/ResetPasswordModal';
import styles from './UserAuthContainerModal.module.css';

Modal.setAppElement('#root');

export default function UserAuthContainerModal({ onClose }) {
  const [view, setView] = useState('login');

  const handleRegisterClick = () => {
    setView('register');
  };

  const handleResetPasswordClick = () => {
    setView('resetPassword');
  };

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
          />
        )}
        {view === 'register' && <RegisterModal onClose={handleClose} />}
        {view === 'resetPassword' && <ResetPasswordModal onClose={handleClose} />}
      </div>
    </Modal>
  );
}
