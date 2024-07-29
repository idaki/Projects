import React, { useState } from 'react';
import SearchBar from '../../Search/SearchBar';
import { getUserDetailsByProfileInfo } from '../../../services/userDetailsService';
import SettingsContainer from '../../settings/settings-container/SettingsModal';


function EditUserContainer({ setUserDetails }) {
    const [searchTerm, setSearchTerm] = useState('');
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
      </div>
    );
  }
  
  export default EditUserContainer;