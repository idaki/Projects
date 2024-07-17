import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary

export default function UpdatePasswordModal({ onClose }) {
  const { changePasswordHandler } = useContext(AuthContext);

  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [localError, setLocalError] = useState('');

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    if (localError) {
      setLocalError('');
    }
  };

  const handleConfirmPasswordChange = (e) => {
    setConfirmPassword(e.target.value);
    if (localError) {
      setLocalError('');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!password || !confirmPassword) {
      setLocalError('Both password fields are required');
      return;
    }
    if (password !== confirmPassword) {
      setLocalError('Passwords do not match');
      return;
    }
    try {
      await changePasswordHandler(password);
      onClose();
    } catch (err) {
      setLocalError(err.message || 'An error occurred while changing the password. Please try again.');
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div className="form-outline mb-4">
          <input
            type="password"
            id="new-password"
            className={`form-control ${localError && 'is-invalid'}`}
            name="password"
            value={password}
            onChange={handlePasswordChange}
            placeholder="New Password"
          />
        </div>
        <div className="form-outline mb-4">
          <input
            type="password"
            id="confirm-password"
            className={`form-control ${localError && 'is-invalid'}`}
            name="confirmPassword"
            value={confirmPassword}
            onChange={handleConfirmPasswordChange}
            placeholder="Confirm Password"
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            Change Password
          </button>
        </div>
      </form>
    </div>
  );
}
