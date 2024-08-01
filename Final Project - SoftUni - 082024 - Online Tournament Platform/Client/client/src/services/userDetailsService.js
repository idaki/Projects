// userDetailsService.js
import { fetchWithSettings, getJwtToken, getCsrfTokenFromMeta    } from '../utils/utils';
import { BASE_URL } from '../config/config';

//API interaction function to get user details
// export const getUserDetails = async () => {
//     try {
//         const result = await fetchWithSettingsForMetadata(`${BASE_URL}/user/details`, {
//             method: 'POST'
//         });

//         console.log('User details retrieved:', result);
//         return result;
//     } catch (error) {
//         console.error("Failed to get user details:", error);
//         throw error;
//     }
// };

export const getUserDetails = async () => {
    try {
        const { token: csrfToken, header: csrfHeader } = getCsrfTokenFromMeta();
        console.log('Fetching user details with CSRF token:', csrfToken);

        const response = await fetch(`${BASE_URL}/user/details`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getJwtToken()}`,
                'X-XSRF-TOKEN': csrfToken // CSRF token header
            },
            credentials: 'include'
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`HTTP error! status: ${response.status}, message: ${errorText}`);
            throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
        }

        const data = await response.json();
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
