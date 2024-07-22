import React from 'react';
import ContactDetailsModal from '../contact-details/ContactDetailsModal';
import UploadProfileImageModal from '../uploade-profile-image/UploadProfileImageModal';
import ChangePasswordModal from '../change-password/ChangePasswordModal';
import 'bootstrap/dist/css/bootstrap.min.css';

const SettingsContainer = ({ isOpen, toggle }) => {
  if (!isOpen) return null;

  return (
    <div className="container">
      <div className="row">
        <div className="col-12">
          <div className="my-5">
            <h3>My Profile</h3>
            <hr />
          </div>
          <form className="file-upload">
            <div className="row mb-5 gx-5">
              <ContactDetailsModal />
              <UploadProfileImageModal />
            </div>
            <div className="row mb-5 gx-5">
              <ChangePasswordModal />
            </div>
            <div className="gap-3 d-flex justify-content-center text-center">
              <button type="button" className="btn btn-danger btn-lg">Delete profile</button>
              <button type="button" className="btn btn-primary btn-lg">Update profile</button>
              <button type="button" className="btn btn-primary btn-lg">Close</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SettingsContainer;
