// src/services/authService.js

const baseUrl = 'http://localhost:8080/api';

async function fetchWithSettings(url, options) {
  const defaultHeaders = {
    'Content-Type': 'application/json',
    'X-Authorization': JSON.parse(localStorage.getItem('authData'))?.accessToken || ''
  };

  const response = await fetch(url, {
    ...options,
    headers: {
      ...defaultHeaders,
      ...options.headers
    }
  });

  const isJsonResponse = response.headers.get('Content-Type')?.includes('application/json');
  const responseBody = isJsonResponse ? await response.json() : await response.text();

  if (!response.ok) {
    const errorBody = typeof responseBody === 'object' ? responseBody : { message: responseBody };
    throw new Error(`HTTP error ${response.status}: ${errorBody.message || responseBody}`);
  }

  return responseBody;
}

export const login = async (username, password) => {
  const result = await fetchWithSettings(`${baseUrl}/login`, {
    method: 'POST',
    body: JSON.stringify({ usernameOrEmail: username, password })
  });

  const authData = { accessToken: result };
  localStorage.setItem('authData', JSON.stringify(authData));
  return authData;
};

export const registerConsumer = async (username, password, email) => {
  return fetchWithSettings(`${baseUrl}/register-consumer`, {
    method: 'POST',
    body: JSON.stringify({ username, password, email })
  });
};

export const logout = async () => {
  await fetchWithSettings(`${baseUrl}/logout`, {
    method: 'GET'
  });
  localStorage.removeItem('authData');
};

export const getAllGames = async () => {
  return fetchWithSettings(`${baseUrl}/games`, {
    method: 'GET'
  });
};
