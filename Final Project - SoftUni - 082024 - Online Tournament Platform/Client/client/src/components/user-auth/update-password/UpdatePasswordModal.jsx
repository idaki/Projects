import React, { useState, useEffect, useContext } from 'react';
import Modal from 'react-modal';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './UpdatePasswordModal.module.css';
import { updatePasswordAndLogin } from '../../../services/authService';
import AuthContext from '../../../context/authContext';

Modal.setAppElement('#root'); // Ensure this is set correctly for accessibility

function UpdatePasswordModal() {
  const location = useLocation();
  const navigate = useNavigate();
  const { setAuth } = useContext(AuthContext);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const [token, setToken] = useState('');

  useEffect(() => {
    if (location.pathname === '/newpassword') {
      const params = new URLSearchParams(location.search);
      const token = params.get('token');
      if (token) {
        setToken(token);
        setModalIsOpen(true);
      } else {
        setModalIsOpen(false);
        navigate('/');
      }
    } else {
      setModalIsOpen(false);
    }
  }, [location, navigate]);

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

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }
    try {
      const authData = await updatePasswordAndLogin(token, password);
      console.log('Password successfully updated and logged in', authData);
      setAuth(authData);
      handleCloseModal();
    } catch (err) {
      setError(err.message || 'An error occurred while updating the password. Please try again.');
    }
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
