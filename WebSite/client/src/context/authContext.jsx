import React, { createContext, useState, useEffect } from 'react';
import * as authService from '../services/authService'; // Adjust the path as needed

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(() => {
    const savedAuth = localStorage.getItem('authData');
    return savedAuth ? JSON.parse(savedAuth) : {};
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    localStorage.setItem('authData', JSON.stringify(auth));
    console.log("Auth state updated:", auth); // Debug log
  }, [auth]);

  const loginSubmitHandler = async (username, password) => {
    setLoading(true);
    try {
      const result = await authService.login(username, password);
      setAuth(result);
      setError(null);
      setLoading(false);
      return result; // Return the result on success
    } catch (error) {
      setLoading(false);
      setError(error.message); // Set error from server directly
      throw error; // Throw the original error
    }
  };

  const registerConsumerSubmitHandler = async (formData) => {
    setLoading(true);
    try {
      const result = await authService.registerConsumer(formData.username, formData.password, formData.email);
      setAuth(result);
      setError(null);
    } catch (error) {
      setError(error.message);
      console.error("Registration failed:", error);
    } finally {
      setLoading(false);
    }
  };

  const logoutHandler = async () => {
    setLoading(true);
    try {
      await authService.logout();
      setAuth({});
      setError(null);
    } catch (error) {
      setError(error.message);
      console.error("Logout failed:", error);
    } finally {
      setLoading(false);
    }
  };

  const contextValue = {
    auth,
    loading,
    error,
    loginSubmitHandler,
    registerConsumerSubmitHandler,
    logoutHandler,
  };

  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

export { AuthProvider };
export default AuthContext;
