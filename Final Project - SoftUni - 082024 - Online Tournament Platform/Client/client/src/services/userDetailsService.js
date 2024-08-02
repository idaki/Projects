// import { fetchCsrfToken } from '../utils/csrfUtil'; // Adjust the path as needed
import { BASE_URL } from '../config/config';
import { getJwtToken, getCsrfToken , fetchWithSettings} from '../utils/utils';
import {getCsrfTokenFromMeta} from '../utils/metaUtils';
import {fetchCsrfToken} from '../utils/csrfUtil';

// API interaction function to get user details

export const getUserDetails = async () => {
    try {
      
        let csrfToken = getCsrfToken();
        console.log(csrfToken);
    if (!csrfToken) {
      csrfToken = await fetchCsrfToken();
      
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

export const deleteUserById = async (userId) => {
    try {
        let csrfToken = getCsrfToken();
        const url = new URL(`${BASE_URL}/user/delete/${userId}`);

        const result = await fetchWithSettings(url.toString(), {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${getJwtToken()}`,
                'X-XSRF-TOKEN': csrfToken // Include CSRF token in the headers
            }
        });

        if (!result.ok) {
            const errorText = await result.text();
            console.error(`HTTP error! status: ${result.status}, message: ${errorText}`);
            throw new Error(`HTTP error! status: ${result.status}, message: ${errorText}`);
        }

        console.log('User deleted successfully:', result);
        localStorage.removeItem('authData');
        return result;
    } catch (error) {
        console.error("Failed to delete user:", error);
        throw error;
    }
};

