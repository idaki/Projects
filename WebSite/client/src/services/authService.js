const baseUrl = 'http://localhost:8080/api';

export const login = async (username, password) => {
  const response = await fetch(`${baseUrl}/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ usernameOrEmail: username, password })
  });

  if (!response.ok) {
    throw new Error('Network response was not ok');
  }

  const result = await response.json();
  localStorage.setItem('accessToken', result.accessToken);
  return result;
};

export const registerConsumer = async (username, password, email) => {
  const response = await fetch(`${baseUrl}/register-consumer`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ username, password, email })
  });

  if (!response.ok) {
    throw new Error('Network response was not ok');
  }

  const result = await response.json();
  return result;
};

export const logout = async () => {
  const response = await fetch(`${baseUrl}/logout`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'X-Authorization': localStorage.getItem('accessToken')
    }
  });

  if (!response.ok) {
    throw new Error('Network response was not ok');
  }

  localStorage.removeItem('accessToken');
  return {};
};

export const getAllGames = async () => {
  const response = await fetch(`${baseUrl}/games`);
  if (!response.ok) {
    throw new Error('Network response was not ok');
  }
  const result = await response.json();
  return result;
};
