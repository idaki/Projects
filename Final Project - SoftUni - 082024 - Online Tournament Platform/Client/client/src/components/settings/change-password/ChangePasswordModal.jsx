import React, { useContext, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthContext from '../../../context/authContext';
import { Button } from 'react-bootstrap';

const ChangePasswordModal = () => {
  const { auth } = useContext(AuthContext);
  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const changePasswordHandler = (event) => {
    event.preventDefault();

    if (newPassword !== confirmPassword) {
      alert("New password and confirm password do not match!");
      return;
    }

    // Handle password change logic here
    console.log("Old Password:", oldPassword);
    console.log("New Password:", newPassword);
    console.log("Confirm Password:", confirmPassword);

    // Reset form fields after submission
    setOldPassword('');
    setNewPassword('');
    setConfirmPassword('');
  };

  const renderAdminUser = () => (
    <div className="col-md-12">
      <label htmlFor="oldPassword" className="form-label">Old password *</label>
      <input
        type="password"
        className="form-control"
        id="oldPassword"
        value={oldPassword}
        onChange={(e) => setOldPassword(e.target.value)}
      />
    </div>
  );

  return (
    <div className="col-xxl-6">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <form onSubmit={changePasswordHandler}>
          <div className="row g-3">
            <h4 className="my-4">Change Password</h4>
            {auth.roles.includes('ROLE_ADMIN_USER') && renderAdminUser()}
            <div className="col-md-12">
              <label htmlFor="newPassword" className="form-label">New password *</label>
              <input
                type="password"
                className="form-control"
                id="newPassword"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
            </div>
            <div className="col-md-12">
              <label htmlFor="confirmPassword" className="form-label">Confirm Password *</label>
              <input
                type="password"
                className="form-control"
                id="confirmPassword"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
            </div>
          </div>
          <Button variant="primary" type="submit" className="mt-4">Change Password</Button>
        </form>
      </div>
    </div>
  );
};

export default ChangePasswordModal;
