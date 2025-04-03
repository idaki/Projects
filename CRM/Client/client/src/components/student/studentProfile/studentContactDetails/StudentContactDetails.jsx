import React from 'react';

const StudentContactDetails = ({ userDetails }) => {
  return (
    <div className="col-xxl-8 mb-5 mb-xxl-0">
      <div className="bg-secondary-soft px-4 py-5 rounded">
        <div className="row g-3">
          <h4 className="mb-4 mt-0">Contact Details</h4>
          <div className="col-md-6">
            <label className="form-label">First Name *</label>
            <input
              type="text"
              className="form-control"
              placeholder="First name"
              aria-label="First name"
              defaultValue={userDetails ? userDetails.firstName : ''}
            />
          </div>
          <div className="col-md-6">
            <label className="form-label">Last Name *</label>
            <input
              type="text"
              className="form-control"
              placeholder="Last name"
              aria-label="Last name"
              defaultValue={userDetails ? userDetails.lastName : ''}
            />
          </div>
          <div className="col-md-6">
            <label htmlFor="inputEmail4" className="form-label">Email *</label>
            <input
              type="email"
              className="form-control"
              id="inputEmail4"
              defaultValue={userDetails ? userDetails.email : ''}
            />
          </div>
          <div className="col-md-6">
            <label className="form-label">Confirm Email *</label>
            <input
              type="email"
              className="form-control"
              placeholder="Confirm Email"
              aria-label="Confirm Email"
              defaultValue={userDetails ? userDetails.email : ''}
            />
          </div>
          <div className="col-md-6">
            <label className="form-label">Username *</label>
            <input
              type="text"
              className="form-control"
              placeholder="Username"
              aria-label="Username"
              defaultValue={userDetails ? userDetails.username : ''}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentContactDetails;
