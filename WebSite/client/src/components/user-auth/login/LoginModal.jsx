import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary

export default function LoginModal({ onClose, onRegisterClick, onForgotPasswordClick }) {
  const { loginSubmitHandler } = useContext(AuthContext); // Get login handler from context

  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: ''
  });

  const [errors, setErrors] = useState({
    username: '',
    password: '',
    general: ''
  });

  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    if (!loginFormData.username || !loginFormData.password) {
      setErrors({
        ...errors,
        general: 'Username and password are required'
      });
      return;
    }
    try {
      await loginSubmitHandler({ username: loginFormData.username, password: loginFormData.password }); // Use context login handler
      onClose();
    } catch (error) {
      setErrors(prevState => ({
        ...prevState,
        general: 'Invalid username or password',
      }));
    }
  };

  return (
    <div>
      <form onSubmit={handleLoginSubmit}>
        <div className="form-outline mb-4">
          <input
            type="text"
            id="login-username"
            className={`form-control ${errors.username && 'is-invalid'}`}
            name="username"
            value={loginFormData.username}
            onChange={handleLoginChange}
            placeholder="Username"
          />
          {errors.username && <div className="invalid-feedback">{errors.username}</div>}
        </div>

        <div className="form-outline mb-4">
          <input
            type="password"
            id="login-password"
            className={`form-control ${errors.password && 'is-invalid'}`}
            name="password"
            value={loginFormData.password}
            onChange={handleLoginChange}
            placeholder="Password"
          />
          {errors.password && <div className="invalid-feedback">{errors.password}</div>}
        </div>

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary row mb-4">
            Login
          </button>
        </div>
      </form>

      {errors.general && <div className="alert alert-danger">{errors.general}</div>}

      <div className="text-center mb-3">
        <a href="#!" onClick={onForgotPasswordClick}>Forgot password?</a>
      </div>

      <div className="text-center">
        <p>Not a member? <a href="#!" onClick={onRegisterClick}>Register</a></p>
      </div>
    </div>
  );
}
