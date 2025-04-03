// SearchBar.jsx
import React from 'react';

const SearchBar = ({ keyword, onChange }) => {
  return (
    <input
      type="text"
      className="form-control form-control-sm" // Add form-control-sm for smaller input
      placeholder="Search by name"
      value={keyword}  // Bind the value to the parent state
      onChange={onChange}  // Call the parent's onChange function when the user types
    />
  );
};

export default SearchBar;
