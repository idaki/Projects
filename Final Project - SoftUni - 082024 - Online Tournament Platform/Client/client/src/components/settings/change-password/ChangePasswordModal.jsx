import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useTranslation } from 'react-i18next'; // Import useTranslation hook

const ChangePasswordModal = () => {
  const { t } = useTranslation(); // Use the useTranslation hook

  return (
    <div className="col-xxl-6">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <div className="row g-3">
          <h4 className="my-4">{t('changePassword.title')}</h4>
          <div className="col-md-6">
            <label htmlFor="oldPassword" className="form-label">{t('changePassword.oldPassword')}</label>
            <input type="password" className="form-control" id="oldPassword" />
          </div>
          <div className="col-md-6">
            <label htmlFor="newPassword" className="form-label">{t('changePassword.newPassword')}</label>
            <input type="password" className="form-control" id="newPassword" />
          </div>
          <div className="col-md-12">
            <label htmlFor="confirmPassword" className="form-label">{t('changePassword.confirmPassword')}</label>
            <input type="password" className="form-control" id="confirmPassword" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChangePasswordModal;