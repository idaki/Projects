import { getJwtToken, getCsrfToken } from '../utils/utils';
import { BASE_URL } from '../config/config';
import{fetchCsrfToken } from '../utils/csrfUtils';

export const getAll = async () => {
    try {   let csrfToken = getCsrfToken();
        console.log(csrfToken);
    if (!csrfToken) {
      csrfToken = await fetchCsrfToken();}
        
        const response = await fetch(`${BASE_URL}/teams/my-teams`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`,
                'X-XSRF-TOKEN': getCsrfToken()
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

        console.log('Fetched teams data:', data);

        // Check if the data is an array and contains objects with expected keys
        if (!Array.isArray(data) || data.some(item => typeof item !== 'object' || !item.id)) {
            console.error('Expected data to be an array of objects but received:', data);
            throw new Error('Invalid data format from server');
        }

        sessionStorage.setItem('teamsList', JSON.stringify(data));
        return data;
    } catch (error) {
        console.error('Failed to fetch teams:', error);
        throw error;
    }
};


export const getTeamsByTournamentId = async (tournamentId) => {
    try {
        let csrfToken = getCsrfToken();
        if (!csrfToken) {
            csrfToken = await fetchCsrfToken();
        }

        const response = await fetch(`${BASE_URL}/teams/by-tournament`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`,
                'X-XSRF-TOKEN': csrfToken
            },
            credentials: 'include',
            body: JSON.stringify({ tournamentId })
        });

        if (!response) {
            throw new Error('No response from server');
        }

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`HTTP error! status: ${response.status}, message: ${errorText}`);
            throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Failed to fetch teams by tournament ID:', error);
        throw error;
    }
};