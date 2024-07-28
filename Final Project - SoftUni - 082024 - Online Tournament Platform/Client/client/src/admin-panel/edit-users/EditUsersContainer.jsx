import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { getUserDetailsByProfileInfo } from '../../services/userDetailsService';

export default function EditUsersModal() {
  const [searchTerm, setSearchTerm] = useState('');
  const [userDetails, setUserDetails] = useState(null);
  const [error, setError] = useState(null);

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearchClick = async () => {
    try {
      const userDetails = await getUserDetailsByProfileInfo();
      setUserDetails(userDetails);
      setError(null); // Clear previous error
    } catch (error) {
      setError('Failed to fetch user details.');
      setUserDetails(null); // Clear previous user details
    }
  };

  return (
    <div className="container mt-5">
      <div className="input-group">
        <div className="form-outline">
          <input
            type="search"
            id="form1"
            className="form-control"
            value={searchTerm}
            onChange={handleInputChange}
          />
          <label className="form-label" htmlFor="form1">Search</label>
        </div>
        <button type="button" className="btn btn-primary" onClick={handleSearchClick}>
          <i className="bi bi-search"></i>
        </button>
      </div>

      {error && <div className="alert alert-danger mt-3">{error}</div>}

      {userDetails && (
        <div className="mt-3">
          <h5>User Details:</h5>
          <pre>{JSON.stringify(userDetails, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}
