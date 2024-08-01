import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary
import { useTranslation } from 'react-i18next';


export default function LoginModal({ onClose, onRegisterClick, onForgotPasswordClick }) {
  const { t } = useTranslation(); // Use the useTranslation hook
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
      setLocalError(t('errors.usernameAndPasswordRequired'));
      return;
    }
    try {
      await loginHandler(loginFormData.username, loginFormData.password);
      onClose();
    } catch (err) {
      setLocalError(err.message || t('errors.loginError'));
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
            placeholder={t('forms.username')}
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
            placeholder={t('forms.password')}
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            {t('buttons.login')}
          </button>
        </div>
      </form>

      <div className="text-center mb-3">
        <a href="#!" onClick={onForgotPasswordClick}>
          {t('links.forgotPassword')}
        </a>
      </div>

      <div className="text-center">
        <p>
          {t('links.notAMember')} <a href="#!" onClick={onRegisterClick}>{t('links.register')}</a>
        </p>
      </div>
    </div>
  );
}