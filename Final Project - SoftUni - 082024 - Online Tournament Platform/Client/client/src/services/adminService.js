import { fetchWithSettings, storeAuthData, getCsrfToken } from '../utils/utils';
import { BASE_URL } from '../config/config';
import {fetchCsrfToken} from '../utils/csrfUtil';

export async function getUserDetailsByProfileInfo(query) {
    try {
        let csrfToken = getCsrfToken();
         if (!csrfToken) {
          csrfToken = await fetchCsrfToken();
          getUserDetailsByProfileInfo(query) 
          }
         

        const url = new URL(`${BASE_URL}/admin/search`);
        
        // Determine the type based on the input
        let type;
        if (query.includes(' ')) {
            type = query.split(' ').length === 2 ? 'firstName' : 'lastName';
        } else {
            type = 'username';
        }

        url.searchParams.append(type, query);

        const result = await fetchWithSettings(url.toString(), {
            method: 'POST'
        });

        console.log('User details retrieved:', result);
        return result;
    } catch (error) {
        console.error('Failed to get user details:', error);
        throw error;
    }
}
