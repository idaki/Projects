import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const ContactDetailsModal = () => {
  return (
    <div className="col-xxl-8 mb-5 mb-xxl-0">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <div className="row g-3">
          <h4 className="mb-4 mt-0">Contact detail</h4>
          <div className="col-md-6">
            <label className="form-label">First Name *</label>
            <input type="text" className="form-control" placeholder="" aria-label="First name" defaultValue="Last name" />
          </div>
          <div className="col-md-6">
            <label className="form-label">Last Name *</label>
            <input type="text" className="form-control" placeholder="" aria-label="Last name" defaultValue="Last name" />
          </div>
    
          <div className="col-md-6">
            <label htmlFor="inputEmail4" className="form-label">Email *</label>
            <input type="email" className="form-control" id="inputEmail4" defaultValue="Confirm Email" />
          </div>
          <div className="col-md-6">
            <label className="form-label">Confirm Email *</label>
            <input type="text" className="form-control" placeholder="" aria-label="Skype" defaultValue="Confirm Email " />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ContactDetailsModal;
