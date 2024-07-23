import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext';

export default function RegisterModal({ onClose }) {
  const { registerHandler } = useContext(AuthContext);

  const [registerFormData, setRegisterFormData] = useState({
    username: '',
    password: '',
    email: ''
  });

  const [localError, setLocalError] = useState('');

  const handleRegisterChange = (e) => {
    const { name, value } = e.target;
    setRegisterFormData((prevState) => ({
      ...prevState,
      [name]: value
    }));
    if (localError) {
      setLocalError('');
    }
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    if (!registerFormData.username || !registerFormData.password || !registerFormData.email) {
      setLocalError('All fields are required');
      return;
    }
    try {
      await registerHandler(registerFormData.username, registerFormData.password, registerFormData.email);
      onClose();
    } catch (err) {
      setLocalError(err.message || 'An error occurred during registration. Please try again.');
    }
  };

  return (
    <div>
      <form onSubmit={handleRegisterSubmit}>
        <div className="form-outline mb-4">
          <input
            type="text"
            id="register-username"
            className={`form-control ${localError && 'is-invalid'}`}
            name="username"
            value={registerFormData.username}
            onChange={handleRegisterChange}
            placeholder="Username"
          />
        </div>

        <div className="form-outline mb-4">
          <input
            type="password"
            id="register-password"
            className={`form-control ${localError && 'is-invalid'}`}
            name="password"
            value={registerFormData.password}
            onChange={handleRegisterChange}
            placeholder="Password"
          />
        </div>

        <div className="form-outline mb-4">
          <input
            type="email"
            id="register-email"
            className={`form-control ${localError && 'is-invalid'}`}
            name="email"
            value={registerFormData.email}
            onChange={handleRegisterChange}
            placeholder="Email"
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            Register
          </button>
        </div>
      </form>
    </div>
  );
}
