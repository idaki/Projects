// UserAuthContainerModal.jsx
import React, { useState } from 'react';
import Modal from 'react-modal';
import LoginModal from '../login/LoginModal';
import RegisterModal from '../register/RegisterModal';
import ResetPasswordModal from '../reset-password/ResetPasswordModal';
import styles from './UserAuthContainerModal.module.css';

Modal.setAppElement('#root');

export default function UserAuthContainerModal({ onClose }) {
    const [showLogin, setShowLogin] = useState(true);
    const [showRegister, setShowRegister] = useState(false);
    const [showResetPassword, setShowResetPassword] = useState(false);



    const handleRegisterClick = () => {
        setShowLogin(false);
        setShowRegister(true);
        setShowResetPassword(false);
    };

    const handleResetPasswordClick = () => {
        setShowLogin(false);
        setShowRegister(false);
        setShowResetPassword(true);
    };

    const handleClose = () => {
        onClose(); // Call the onClose function provided by the parent component
        setShowLogin(false);
        setShowRegister(false);
        setShowResetPassword(false);
    };

    const handleOverlayClick = (event) => {
        if (event.target === event.currentTarget) {
            handleClose();
        }
    };

    return (
        <Modal
            isOpen={showLogin || showRegister || showResetPassword}
            onRequestClose={handleClose}
            contentLabel="User Authentication"
            className={styles.modalContent}
            overlayClassName={styles.overlay}
            shouldCloseOnOverlayClick={true}
        >
            <div onClick={handleOverlayClick} className={styles.overlay}>
                <div className={styles.modalContent}>
                    {showLogin && <LoginModal onClose={handleClose} onRegisterClick={handleRegisterClick} onForgotPasswordClick={handleResetPasswordClick} />}
                    {showRegister && <RegisterModal onClose={handleClose} />}
                    {showResetPassword && <ResetPasswordModal onClose={handleClose} />}
                
    
                </div>
            </div>
        </Modal>
    );
}
