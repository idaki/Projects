import { fetchWithSettings, getJwtToken } from '../utils/utils';
import { BASE_URL } from '../config/config';

export const getAllFriends = async () => {
  try {
    const jwt = getJwtToken();  // Get the JWT from localStorage
    if (!jwt) {
      console.error("JWT token is empty");
      throw new Error("JWT token is empty");  // Validate the JWT is not empty
    }

    const response = await fetchWithSettings(`${BASE_URL}/friends`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${jwt}`,  // Send the JWT in the Authorization header
        'Content-Type': 'application/json'  // Set Content-Type to application/json
      }
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json();
    return data;  // Assume data is an array of FriendDTOs
  } catch (error) {
    console.error('Failed to fetch friends:', error);
    throw error;
  }
};
