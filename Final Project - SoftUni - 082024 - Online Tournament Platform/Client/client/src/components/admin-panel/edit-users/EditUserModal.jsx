import React, { useState } from 'react';
import SearchBar from '../../Search/SearchBar';
import { getUserDetailsByProfileInfo } from '../../../services/userDetailsService';
import SettingsContainer from '../../settings/settings-container/SettingsModal';

function EditUserContainer() {
    const [searchTerm, setSearchTerm] = useState('');
    const [userDetails, setUserDetails] = useState(null);
    const [error, setError] = useState(null);
    const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  
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
        setIsSettingsOpen(true);
      } catch (error) {
        setError('Failed to get user details');
        setUserDetails(null);
        setIsSettingsOpen(false);
      }
    };
  
    const toggleSettings = () => {
      setIsSettingsOpen(!isSettingsOpen);
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
          <SettingsContainer isOpen={isSettingsOpen} toggle={toggleSettings} userDetails={userDetails} />
        )}
      </div>
    );
  }
  
  export default EditUserContainer;