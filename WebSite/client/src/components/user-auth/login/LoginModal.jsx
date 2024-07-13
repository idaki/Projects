import React, { useState, useContext, useEffect } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary

export default function LoginModal({ onClose, onRegisterClick, onForgotPasswordClick }) {
  const { loginSubmitHandler, loading } = useContext(AuthContext);

  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: ''
  });

  const [localError, setLocalError] = useState(''); // Manage error state locally

  // Clears the error when the modal is closed and reopened
  useEffect(() => {
    return () => {
      setLocalError('');
      setLoginFormData({ username: '', password: '' }); // Clear form data on modal close
    };
  }, []);

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
    if (localError) {
      setLocalError('');  // Clear error when user starts typing again
    }
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    if (!loginFormData.username || !loginFormData.password) {
      setLocalError('Username and password are required');
      return;
    }
    try {
      await loginSubmitHandler(loginFormData.username, loginFormData.password);
      onClose(); // Close the modal on successful login
    } catch (err) {
      setLocalError(err.message || 'An error occurred during login. Please try again.');  // Display user-friendly error
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
            disabled={loading}
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
            disabled={loading}
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}  

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4" disabled={loading}>
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </div>
      </form>

      <div className="text-center mb-3">
        <a href="#!" onClick={onForgotPasswordClick}>Forgot password?</a>
      </div>

      <div className="text-center">
        <p>Not a member? <a href="#!" onClick={onRegisterClick}>Register</a></p>
      </div>
    </div>
  );
}
