import React, { createContext, useState, useEffect } from 'react';
import { login, logout, registerConsumer, resetPassword } from '../services/authService'; // Ensure registerConsumer is imported

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(() => {
    const persistedAuth = localStorage.getItem('authData');
    return persistedAuth ? JSON.parse(persistedAuth) : null;
  });

  const loginHandler = async (username, password) => {
    try {
      const authData = await login(username, password);
      setAuth(authData);
    } catch (error) {
      console.error('Login failed', error);
      throw error;
    }
  };

  const logoutHandler = async () => {
    try {
      await logout();
      setAuth(null);
    } catch (error) {
      console.error('Logout failed', error);
    }
  };

  const registerHandler = async (username, password, email) => {
    try {
      // Register the user and automatically log them in
      const authData = await registerConsumer(username, password, email);
  
      console.log('Registration and auto-login successful:', authData);
      
      // Update the auth state with the new token received
      setAuth(authData);
    } catch (error) {
      console.error('Registration failed', error);
      throw error;
    }
  };

  const resetPasswordHandler = async (email) => {
    try {
      await resetPassword(email);
    } catch (error) {
      console.error('Password reset failed', error);
      throw error;
    }
  };

  useEffect(() => {
    localStorage.setItem('authData', JSON.stringify(auth));
  }, [auth]);

  return (
    <AuthContext.Provider value={{ auth, loginHandler, logoutHandler, registerHandler, resetPasswordHandler }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;