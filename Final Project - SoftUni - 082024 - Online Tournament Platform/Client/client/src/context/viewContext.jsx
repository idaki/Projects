// src/context/viewContext.jsx

import React, { createContext, useState } from 'react';

const ViewContext = createContext();

export const ViewProvider = ({ children }) => {
  const [mainContent, setMainContent] = useState('tournaments');

  return (
    <ViewContext.Provider value={{ mainContent, setMainContent }}>
      {children}
    </ViewContext.Provider>
  );
};

export default ViewContext;
