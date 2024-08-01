import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the import path as necessary
import { useTranslation } from 'react-i18next'; 

export default function ResetPasswordModal({ onClose }) {
  const { t } = useTranslation(); // Use the useTranslation hook
  const { resetPasswordHandler } = useContext(AuthContext);

  const [email, setEmail] = useState('');
  const [localError, setLocalError] = useState('');

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
    if (localError) {
      setLocalError('');
    }
  };

  const handleResetSubmit = async (e) => {
    e.preventDefault();
    if (!email) {
      setLocalError(t('errors.emailRequired'));
      return;
    }
    try {
      await resetPasswordHandler(email);
      onClose();
    } catch (err) {
      setLocalError(err.message || t('errors.resetPasswordError'));
    }
  };

  return (
    <div>
      <form onSubmit={handleResetSubmit}>
        <div className="form-outline mb-4">
          <input
            type="email"
            id="reset-email"
            className={`form-control ${localError && 'is-invalid'}`}
            name="email"
            value={email}
            onChange={handleEmailChange}
            placeholder={t('forms.email')}
          />
        </div>

        {localError && <div className="alert alert-danger">{localError}</div>}

        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary mb-4">
            {t('buttons.resetPassword')}
          </button>
        </div>
      </form>
    </div>
  );
}