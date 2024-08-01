import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext';
import { useTranslation } from 'react-i18next';


export default function RegisterModal({ onClose }) {
  const { t } = useTranslation(); // Use the useTranslation hook
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
      setLocalError(t('errors.allFieldsRequired'));
      return;
    }
    try {
      await registerHandler(registerFormData.username, registerFormData.password, registerFormData.email);
      onClose();
    } catch (err) {
      setLocalError(err.message || t('errors.registrationError'));
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
            placeholder={t('forms.username')}
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
            placeholder={t('forms.password')}
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
            placeholder={t('forms.email')}
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            {t('buttons.register')}
          </button>
        </div>
      </form>
    </div>
  );
}