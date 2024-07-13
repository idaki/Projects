import React, { createContext, useState } from 'react';
import * as authService from '../services/authService'; // Adjust the path as needed

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState({});

  const loginSubmitHandler = async (formData) => {
    const result = await authService.login(formData.username, formData.password);
    setAuth(result);
  };

  const registerConsumerSubmitHandler = async (formData) => {
    const result = await authService.registerConsumer(formData.username, formData.password, formData.email);
    setAuth(result);
  };

  const logoutHandler = async () => {
    await authService.logout();
    setAuth({});
  };

  const contextValue = {
    auth,
    loginSubmitHandler,
    registerConsumerSubmitHandler,
    logoutHandler,
  };

  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

export { AuthProvider };
export default AuthContext;
