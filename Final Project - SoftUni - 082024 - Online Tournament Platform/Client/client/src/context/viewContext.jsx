import React, { createContext, useState, useEffect, useContext } from 'react';
import AuthContext from './authContext';

const ViewContext = createContext();

export const ViewProvider = ({ children }) => {
  const { auth } = useContext(AuthContext);
  const [mainContent, setMainContent] = useState('tournaments');

  useEffect(() => {
    if (auth) {
      if (auth.roles.includes('ROLE_ADMIN_SUPER')) {
        setMainContent('edit-users');
      } else {
        setMainContent('tournaments');
      }
    }
  }, [auth]);

  return (
    <ViewContext.Provider value={{ mainContent, setMainContent }}>
      {children}
    </ViewContext.Provider>
  );
};

export default ViewContext;
