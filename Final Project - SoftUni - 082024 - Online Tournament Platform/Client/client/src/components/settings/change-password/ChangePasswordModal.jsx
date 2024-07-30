import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const ChangePasswordModal = () => {
  return (
    <div className="col-xxl-6">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <div className="row g-3">
          <h4 className="my-4">Change Password</h4>
          <div className="col-md-6">
            <label htmlFor="oldPassword" className="form-label">Old password *</label>
            <input type="password" className="form-control" id="oldPassword" />
          </div>
          <div className="col-md-6">
            <label htmlFor="newPassword" className="form-label">New password *</label>
            <input type="password" className="form-control" id="newPassword" />
          </div>
          <div className="col-md-12">
            <label htmlFor="confirmPassword" className="form-label">Confirm Password *</label>
            <input type="password" className="form-control" id="confirmPassword" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChangePasswordModal;
