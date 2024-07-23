import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const UploadProfileImageModal = () => {
  return (
    <div className="col-xxl-4 mb-5 mb-xxl-0">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <div className="row g-3">
          <h4 className="mb-4 mt-0">Upload your profile photo</h4>
          <div className="text-center">
            <div className="square position-relative display-2 mb-3">
              <i className="fas fa-fw fa-user position-absolute top-50 start-50 translate-middle text-secondary"></i>
            </div>
            <input type="file" id="customFile" name="file" hidden />
            <label className="btn btn-success-soft btn-block" htmlFor="customFile">Upload</label>
            <button type="button" className="btn btn-danger-soft">Remove</button>
            <p className="text-muted mt-3 mb-0"><span className="me-1">Note:</span>Minimum size 300px x 300px</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UploadProfileImageModal;
