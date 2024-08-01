// import { fetchCsrfToken } from '../utils/csrfUtil'; // Adjust the path as needed
import { BASE_URL } from '../config/config';
import { getJwtToken, getCsrfToken } from '../utils/utils';
import {getCsrfTokenFromMeta} from '../utils/metaUtils';
import {fetchCsrfToken} from '../utils/csrfUtil';
// API interaction function to get user details

export const getUserDetails = async () => {
    try {
      
        let csrfToken = getCsrfToken();
    if (!csrfToken) {
      csrfToken = await fetchCsrfToken();
      if (!csrfToken) {
        throw new Error('Failed to fetch CSRF token');
      }
    }

     

        const response = await fetch(`${BASE_URL}/user/details`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`, // JWT token for authentication
                'X-XSRF-TOKEN': csrfToken
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

        console.log('Fetched user details:', data);
        return data;
    } catch (error) {
        console.error('Failed to fetch user details:', error);
        throw error;
    }
};

export const deleteUser = async () => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/delete`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${getJwtToken()}`,
                'X-XSRF-TOKEN': getCsrfToken() // Include CSRF token in the headers
            }
        });

        console.log('User deleted successfully:', result);
        localStorage.removeItem('authData');
        return result;
    } catch (error) {
        console.error("Failed to delete user:", error);
        throw error;
    }
};