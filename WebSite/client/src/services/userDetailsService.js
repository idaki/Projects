// userDetailsService.js
import { fetchWithSettings } from '../utils/utils';
import { BASE_URL } from '../config/config';

const getJwtToken = () => {
    const authData = JSON.parse(localStorage.getItem('authData'));
    return authData?.accessToken || '';
};



export const getUserDetails = async () => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/details`, {
            method: 'GET' // Use GET method for fetching details
        });

        console.log('User details retrieved:', result);
        return result;
    } catch (error) {
        console.error("Failed to get user details:", error);
        throw error;
    }
};