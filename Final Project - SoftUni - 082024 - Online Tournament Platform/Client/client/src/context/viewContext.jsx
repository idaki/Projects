import React, { createContext, useState, useEffect, useContext } from 'react'; // Ensure correct import
import AuthContext from './authContext'; // Ensure correct import

const ViewContext = createContext();

export const ViewProvider = ({ children }) => {
  const { auth } = useContext(AuthContext); // Access auth context

  const [mainContent, setMainContent] = useState('tournaments'); // Default value

  useEffect(() => {
    if (auth) {
      if (auth.roles.includes('ROLE_ADMIN_SUPER')) {
        setMainContent('edit_users');
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
