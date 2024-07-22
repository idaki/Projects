// userDetailsService.js
import { fetchWithSettings } from '../utils/utils';
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