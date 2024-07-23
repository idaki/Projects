// userDetailsService.js
import { fetchWithSettings, getJwtToken } from '../utils/utils';
import { BASE_URL } from '../config/config';

// API interaction function to get user details
export const getUserDetails = async () => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/details`, {
            method: 'GET'
        });

        console.log('User details retrieved:', result);
        return result;
    } catch (error) {
        console.error("Failed to get user details:", error);
        throw error;
    }
};

export const deleteUser = async () => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/delete`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${getJwtToken()}`
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