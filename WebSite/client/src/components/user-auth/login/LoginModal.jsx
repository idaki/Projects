import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary

export default function LoginModal({ onClose, onRegisterClick, onForgotPasswordClick }) {
  const { loginHandler } = useContext(AuthContext);

  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: ''
  });

  const [localError, setLocalError] = useState('');

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginFormData((prevState) => ({
      ...prevState,
      [name]: value
    }));
    if (localError) {
      setLocalError('');
    }
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    if (!loginFormData.username || !loginFormData.password) {
      setLocalError('Username and password are required');
      return;
    }
    try {
      await loginHandler(loginFormData.username, loginFormData.password);
      onClose();
    } catch (err) {
      setLocalError(err.message || 'An error occurred during login. Please try again.');
    }
  };

  return (
    <div>
      <form onSubmit={handleLoginSubmit}>
        <div className="form-outline mb-4">
          <input
            type="text"
            id="login-username"
            className={`form-control ${localError && 'is-invalid'}`}
            name="username"
            value={loginFormData.username}
            onChange={handleLoginChange}
            placeholder="Username"
          />
        </div>

        <div className="form-outline mb-4">
          <input
            type="password"
            id="login-password"
            className={`form-control ${localError && 'is-invalid'}`}
            name="password"
            value={loginFormData.password}
            onChange={handleLoginChange}
            placeholder="Password"
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            Login
          </button>
        </div>
      </form>

      <div className="text-center mb-3">
        <a href="#!" onClick={onForgotPasswordClick}>
          Forgot password?
        </a>
      </div>

      <div className="text-center">
        <p>
          Not a member?{' '}
          <a href="#!" onClick={onRegisterClick}>
            Register
          </a>
        </p>
      </div>
    </div>
  );
}
