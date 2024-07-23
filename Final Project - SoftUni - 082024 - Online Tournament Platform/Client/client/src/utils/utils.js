// utils.js
export function getJwtToken() {
    const authData = JSON.parse(localStorage.getItem('authData'));
    return authData?.accessToken || '';
}

export async function fetchWithSettings(url, options) {
    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${getJwtToken()}`
    };

    try {
        const response = await fetch(url, {
            ...options,
            headers: {
                ...defaultHeaders,
                ...options.headers
            },
            credentials: 'include'
        });

        const isJsonResponse = response.headers.get('Content-Type')?.includes('application/json');
        const responseBody = isJsonResponse ? await response.json() : await response.text();

        if (!response.ok) {
            throw new Error(responseBody.message || 'Network response was not ok');
        }

        return responseBody;
    } catch (error) {
        console.error("Fetch failed:", error);
        throw error;
    }
}
