import { fetchWithSettings, getCsrfToken, getJwtToken } from '../utils/utils';
import { BASE_URL } from '../config/config';

// Login function
export const login = async (username, password) => {
  try {
    const result = await fetchWithSettings(`${BASE_URL}/login`, {
      method: 'POST',
      body: JSON.stringify({ usernameOrEmail: username, password }),
    });

    console.log('Login successful, received token:', result);
    const authData = { accessToken: result };
    localStorage.setItem('authData', JSON.stringify(authData));
    return authData;
  } catch (error) {
    console.error('Login failed:', error);
    throw error;
  }
};

// Register function
export const registerConsumer = async (username, password, email) => {
  try {
    const registrationResult = await fetchWithSettings(`${BASE_URL}/register-consumer`, {
      method: 'POST',
      body: JSON.stringify({ username, password, email }),
    });

    console.log('Registration successful, attempting to log in...');
    const loginResult = await login(username, password);
    console.log('Login successful after registration:', loginResult);

    return loginResult;
  } catch (error) {
    console.error('Error during registration or login:', error);
    throw error;
  }
};

// Reset password function
export const resetPassword = async (email) => {
  return fetchWithSettings(`${BASE_URL}/reset-password`, {
    method: 'POST',
    body: JSON.stringify({ email }),
  });
};

// Update password and login function
export const updatePasswordAndLogin = async (token, newPassword) => {
  try {
    const response = await fetchWithSettings(`${BASE_URL}/update-password`, {
      method: 'POST',
      body: JSON.stringify({ token, newPassword }),
    });

    console.log('Password updated successfully, received new JWT token:', response);
    const jwtToken = response.accessToken || response;
    const authData = { accessToken: jwtToken };
    localStorage.setItem('authData', JSON.stringify(authData));

    return authData;
  } catch (error) {
    console.error('Error updating password:', error);
    throw error;
  }
};

// Logout function
export const logout = async () => {
  try {
    await fetchWithSettings(`${BASE_URL}/logout`, {
      method: 'POST',
    });
  } catch (error) {
    console.error('Logout failed:', error);
  }
  localStorage.removeItem('authData');
  sessionStorage.clear();
  window.location.href = '/login'; // Redirect to login after clearing auth data
};

// Get all games function
export const getAllGames = async () => {
  return fetchWithSettings(`${BASE_URL}/games`, {
    method: 'GET',
  });
};
