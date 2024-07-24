import { fetchWithSettings } from '../utils/utils';
import { BASE_URL } from '../config/config';

export const getAllFriends = async (userId) => {
  try {
    const response = await fetchWithSettings(`${BASE_URL}/friends?userId=${userId}`, {
      method: 'GET',
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Failed to fetch friends:', error);
    throw error;
  }
};