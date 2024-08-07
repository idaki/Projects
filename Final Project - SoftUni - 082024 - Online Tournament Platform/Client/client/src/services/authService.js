import { fetchWithSettings, storeAuthData   } from '../utils/utils';
import {fetchCsrfToken, captureCsrfTokenFromResponse} from '../utils/csrfUtils';
import { BASE_URL } from '../config/config';
import { jwtDecode } from "jwt-decode";
import {handleHttpError, isJsonResponse} from '../utils/errorsUtil';

export const login = async (username, password) => {
  try {
    const response = await fetch(`${BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ usernameOrEmail: username, password }),
      credentials: 'include' // Include credentials for CSRF protection
    });

    if (!response.ok) {
      await handleHttpError(response);
    }

    // Capture CSRF token from the response
    captureCsrfTokenFromResponse(response);

    // Get the token as plain text
    const token = await response.text();
    console.log('Login successful, received token:', token);

    // Store authentication data
    const authData = storeAuthData(token);

    return authData;
  } catch (error) {
    console.error('Login failed:', error);
    throw error;
  }
};

export const registerConsumer = async (username, password, email) => {
  try {
    const registrationResult = await fetchWithSettings(`${BASE_URL}/register-consumer`, {
      method: 'POST',
      body: JSON.stringify({ username, password, email }),
    });

    const loginResult = await login(username, password);

    return loginResult;
  } catch (error) {
    throw error;
  }
};

export const resetPassword = async (email) => {
  try {
    const response = await fetch(`${BASE_URL}/reset-password`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email }),
    });

    if (!response.ok) {
      const isJsonResponse = response.headers.get('Content-Type')?.includes('application/json');
      const responseBody = isJsonResponse ? await response.json() : await response.text();
      const firstErrorMessage = isJsonResponse ? responseBody.message : responseBody;
      throw new Error(firstErrorMessage || 'Network response was not ok');
    }

    return await response.text(); // Return the response text directly
  } catch (error) {
    console.error('Reset password failed:', error);
    throw error;
  }
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
