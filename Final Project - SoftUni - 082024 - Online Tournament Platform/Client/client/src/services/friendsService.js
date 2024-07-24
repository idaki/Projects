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
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${jwt}`,  // Sending the JWT in the Authorization header as well
        'Content-Type': 'text/plain'  // Change Content-Type to text/plain if the body will only contain the JWT string
      },
      body: jwt  // Send the JWT as a plain text string
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