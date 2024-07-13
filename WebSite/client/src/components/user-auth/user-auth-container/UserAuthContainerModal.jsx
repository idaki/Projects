import React, { useState } from 'react';
import Modal from 'react-modal';
import LoginModal from '../login/LoginModal';
import RegisterModal from '../register/RegisterModal';
import ResetPasswordModal from '../reset-password/ResetPasswordModal';
import styles from './UserAuthContainerModal.module.css';

Modal.setAppElement('#root');

export default function UserAuthContainerModal({ onClose }) {
    const [view, setView] = useState('login'); // Use a single state variable for managing views

    const handleRegisterClick = () => {
        setView('register');
    };

    const handleResetPasswordClick = () => {
        setView('resetPassword');
    };

    const handleClose = () => {
        onClose(); // Call the onClose function provided by the parent component
        setView(''); // Reset the view state
    };

    const handleOverlayClick = (event) => {
        if (event.target === event.currentTarget) {
            handleClose();
        }
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
            <div onClick={handleOverlayClick} className={styles.overlay}>
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
            </div>
        </Modal>
    );
}
