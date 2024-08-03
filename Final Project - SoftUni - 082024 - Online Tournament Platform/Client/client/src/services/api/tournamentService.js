import { getJwtToken } from '../../utils/utils';
import { BASE_URL } from '../../config/config'; 
import {getCsrfToken, fetchCsrfToken } from '../../utils/csrfUtils';

export const getAll = async () => {
    const response = await fetch(`${BASE_URL}/tournaments/all`);
    const result = await response.json();

    const data = Object.values(result);

    return data;
    
};



export const getMyTournaments = async () => {
  try {
    let csrfToken = getCsrfToken();
    console.log(csrfToken);

    if (!csrfToken) {
        csrfToken = await fetchCsrfToken();
        console.log(csrfToken);
    }

    const authData = JSON.parse(localStorage.getItem('authData'));
    if (!authData || !authData.accessToken) {
      throw new Error('Auth data or access token not found in localStorage');
    }

    // // Prepend 'csrf_' to the csrfToken
    // const csrfTokenWithPrefix = `csrf_${csrfToken}`;

    const response = await fetch(`${BASE_URL}/tournaments/managed`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authData.accessToken}`,
        'x-xsrf-token': csrfToken // Include CSRF token in the headers
      },
      credentials: 'include'
    });

    if (!response) {
      throw new Error('No response from server');
    }

    console.log('Response received:', response);

    if (!response.ok) {
      const errorText = await response.text();
      console.error(`HTTP error! status: ${response.status}, message: ${errorText}`);
      throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
    }

    const contentType = response.headers.get('Content-Type');
    let data;
    if (contentType && contentType.includes('application/json')) {
      data = await response.json();
    } else {
      const textData = await response.text();
      console.error('Unexpected response format:', textData);
      throw new Error('Unexpected response format');
    }

    console.log('Fetched tournaments data:', data);

    if (!Array.isArray(data) || data.some(item => typeof item !== 'object' || !item.id)) {
      console.error('Expected data to be an array of objects but received:', data);
      throw new Error('Invalid data format from server');
    }

    localStorage.setItem('tournamentsList', JSON.stringify(data));
    return data;
  } catch (error) {
    console.error('Failed to fetch tournaments:', error);
    throw error;
  }
};


export const getMyWatchList = async () => {
  try {
    let csrfToken = getCsrfToken();
    console.log(csrfToken);

    if (!csrfToken) {
        csrfToken = await fetchCsrfToken();
        console.log(csrfToken);
    }
    const response = await fetch(`${BASE_URL}/tournaments/watchlist`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getJwtToken()}`,
        'X-XSRF-TOKEN': csrfToken
      },
      credentials: 'include'
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Failed to fetch watchlist:', error);
    throw error;
  }
};


export const createTournament = async (tournamentData) => {
  try {
    let csrfToken = getCsrfToken();
    console.log(csrfToken);

    if (!csrfToken) {
        csrfToken = await fetchCsrfToken();
        console.log(csrfToken);
    }


    const response = await fetch(`${BASE_URL}/tournaments/create`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getJwtToken()}`,
        'X-XSRF-TOKEN': csrfToken
      },
      credentials: 'include',
      body: JSON.stringify(tournamentData)
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Failed to create tournament:', error);
    throw error;
  }
};

export const getTournamentById = async (id) => {
  try {
    const response = await fetch(`${BASE_URL}/tournaments/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${getJwtToken()}`,
        'X-XSRF-TOKEN': getCsrfToken()
      },
      credentials: 'include'
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Failed to fetch tournament:', error);
    throw error;
  }
};

