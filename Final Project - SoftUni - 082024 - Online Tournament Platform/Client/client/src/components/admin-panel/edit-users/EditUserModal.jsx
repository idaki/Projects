import React, { useState } from 'react';
import SearchBar from '../../Search/SearchBar';
import { getUserDetailsByProfileInfo } from '../../../services/userDetailsService';
function EditUserContainer() {
  const [searchTerm, setSearchTerm] = useState('');
  const [userDetails, setUserDetails] = useState(null);
  const [error, setError] = useState(null);

  const handleSearchChange = (term) => {
    setSearchTerm(term);
  };

  const handleSearchClick = async () => {
    console.log('Search button clicked');
    console.log('Search term:', searchTerm);
    try {
      const result = await getUserDetailsByProfileInfo(searchTerm);
      setUserDetails(result);
      setError(null);
    } catch (error) {
      setError('Failed to get user details');
      setUserDetails(null);
    }
  };

  return (
    <div className="container mt-5">
      <div className="input-group">
        <SearchBar keyword={searchTerm} onChange={handleSearchChange} />
        <button type="button" className="btn btn-primary" onClick={handleSearchClick}>
          Search
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

export default EditUserContainer;
