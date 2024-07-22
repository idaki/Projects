// userDetailsService.js
import { fetchWithSettings } from '../utils/utils';
import { BASE_URL } from '../config/config';

// API interaction functions for user details
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

export const updateUserDetails = async (userDetails) => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/update`, {
            method: 'PUT',
            body: JSON.stringify(userDetails)
        });

        console.log('User details updated:', result);
        return result;
    } catch (error) {
        console.error("Failed to update user details:", error);
        throw error;
    }
};

export const changeUserPassword = async (oldPassword, newPassword) => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/change-password`, {
            method: 'POST',
            body: JSON.stringify({ oldPassword, newPassword })
        });

        console.log('User password changed:', result);
        return result;
    } catch (error) {
        console.error("Failed to change user password:", error);
        throw error;
    }
};

export const updateUserEmail = async (newEmail) => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/update-email`, {
            method: 'POST',
            body: JSON.stringify({ newEmail })
        });

        console.log('User email updated:', result);
        return result;
    } catch (error) {
        console.error("Failed to update user email:", error);
        throw error;
    }
};

export const updateUsername = async (newUsername) => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/update-username`, {
            method: 'POST',
            body: JSON.stringify({ newUsername })
        });

        console.log('Username updated:', result);
        return result;
    } catch (error) {
        console.error("Failed to update username:", error);
        throw error;
    }
};

export const manageFriends = async (friendId, action) => {
    try {
        const result = await fetchWithSettings(`${BASE_URL}/user/manage-friends`, {
            method: 'POST',
            body: JSON.stringify({ friendId, action }) // action can be 'add' or 'remove'
        });

        console.log('Friend management action:', result);
        return result;
    } catch (error) {
        console.error("Failed to manage friends:", error);
        throw error;
    }
};
