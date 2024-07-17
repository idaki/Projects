import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './UpdatePasswordModal.module.css';

Modal.setAppElement('#root'); // Ensure this is set correctly for accessibility

function UpdatePasswordModal() {
  const location = useLocation();
  const navigate = useNavigate();
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    if (location.pathname === '/newpassword') {
      setModalIsOpen(true);
    } else {
      setModalIsOpen(false);
    }
  }, [location]);

  const handleCloseModal = () => {
    setModalIsOpen(false);
    navigate('/'); // Redirect to the homepage or a relevant page
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleConfirmPasswordChange = (e) => {
    setConfirmPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }
    // TODO: Implement actual password update logic here
    console.log('Password successfully updated'); // Placeholder for actual implementation
    handleCloseModal();
  };

  return (
    <Modal
      isOpen={modalIsOpen}
      onRequestClose={handleCloseModal}
      contentLabel="Update Password"
      overlayClassName={styles.overlay}
      className={styles.modal}
    >
      <div className={styles.modalContent}>
        {error && <p className="alert alert-danger">{error}</p>}
        <form onSubmit={handleSubmit} className={styles.userAuthContent}>
          <div className="form-outline mb-4">
            <input
              type="password"
              className="form-control"
              id="password"
              value={password}
              onChange={handlePasswordChange}
              placeholder="New Password"
            />
          </div>
          <div className="form-outline mb-4">
            <input
              type="password"
              className="form-control"
              id="confirm-password"
              value={confirmPassword}
              onChange={handleConfirmPasswordChange}
              placeholder="Confirm New Password"
            />
          </div>
          {error && <div className="alert alert-danger">{error}</div>}
          <div className="d-flex justify-content-center">
            <button type="submit" className="btn btn-primary mb-4">
              Change Password
            </button>
          </div>
        </form>
      </div>
    </Modal>
  );
}

export default UpdatePasswordModal;
