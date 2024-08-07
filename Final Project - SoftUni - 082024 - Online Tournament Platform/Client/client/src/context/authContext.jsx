// src/context/authContext.js
import React, { createContext, useCallback, useEffect } from 'react';
import { login, logout, registerConsumer, resetPassword, updatePasswordAndLogin } from '../services/authService';
import usePersistedState from '../hooks/usePersistedState';
import {jwtDecode} from 'jwt-decode';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = usePersistedState('authData', null);

  useEffect(() => {
    const checkTokenExpiration = () => {
      if (auth && auth.expiresAt && Date.now() >= auth.expiresAt) {
        setAuth(null);
        localStorage.removeItem('authData');
        console.warn('Session expired, please log in again.');
      }
    };

    checkTokenExpiration();

    const interval = setInterval(checkTokenExpiration, 60000); // Check every minute

    return () => clearInterval(interval);
  }, [auth]);

  const loginHandler = async (username, password) => {
    try {
      const authData = await login(username, password);
      setAuth(authData);
    } catch (error) {
      console.error('Login failed', error);
      throw error;
    }
  };

  const logoutHandler = useCallback(async () => {
    try {
      await logout();
      setAuth(null);
      localStorage.removeItem('authData');
    } catch (error) {
      console.error('Logout failed', error);
    }
  }, []);

  const registerHandler = async (username, password, email) => {
    try {
      const authData = await registerConsumer(username, password, email);
      setAuth(authData);
    } catch (error) {
      console.error('Registration failed', error);
      throw error;
    }
  };

  const resetPasswordHandler = async (email) => {
    try {
      const message = await resetPassword(email);
      return message; 
    } catch (error) {
      console.error('Password reset failed', error);
      throw error;
    }
  };

  const updatePasswordHandler = async (token, newPassword) => {
    try {
      const authData = await updatePasswordAndLogin(token, newPassword);
      setAuth(authData);
    } catch (error) {
      console.error('Password update failed', error);
      throw error;
    }
  };

  return (
    <AuthContext.Provider value={{ auth, loginHandler, logoutHandler, registerHandler, resetPasswordHandler, updatePasswordHandler, setAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
