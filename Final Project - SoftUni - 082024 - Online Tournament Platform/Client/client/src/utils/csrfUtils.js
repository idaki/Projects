import { BASE_URL } from '../config/config';
import { getJwtToken } from './utils';

// Utility function to fetch CSRF token
// Utility function to fetch CSRF token
// Utility function to fetch CSRF token
export const fetchCsrfToken = async () => {
    try {
        const response = await fetch(`${BASE_URL}/csrf`, {
            method: 'POST', // Use POST method
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`,
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

        console.log('Fetched CSRF token:', data);
        return data.token; // Assuming the response contains a token property
    } catch (error) {
        console.error('Failed to fetch CSRF token:', error);
        throw error;
    }
};


export const captureCsrfTokenFromResponse = (response) => {
    const csrfToken = response.headers.get('x-xsrf-token');
    if (csrfToken) {
      document.cookie = `XSRF-TOKEN=${csrfToken}; path=/;`;
      console.log('CSRF token captured and stored in cookies:', csrfToken);
    } else {
      console.warn('CSRF token not found in response headers');
    }
  };
  
 export  const ValidityStateCsrfToken = async () => {
    let csrfToken = getCsrfToken();
    console.log(csrfToken);

    if (!csrfToken) {
        csrfToken = await fetchCsrfToken();
        console.log(csrfToken);
    }
};
export const getCsrfToken = () => {
    const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
    if (match) {
      return match[2];
    }
    return null;
  };
