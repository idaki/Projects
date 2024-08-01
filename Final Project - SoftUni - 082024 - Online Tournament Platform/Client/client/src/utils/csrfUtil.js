import { BASE_URL } from '../config/config';
import { getJwtToken } from '../utils/utils';

// Utility function to fetch CSRF token
// Utility function to fetch CSRF token
// Utility function to fetch CSRF token
export const fetchCsrfToken = async () => {
    try {
        const response = await fetch(`${BASE_URL}/admin/csrf`, {
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