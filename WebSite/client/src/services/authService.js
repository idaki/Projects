const baseUrl = 'http://localhost:8080/api';

// Function to retrieve the CSRF token from cookies
function getCsrfToken() {
    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    console.log('CSRF Token:', csrfToken); // Log the CSRF token
    return csrfToken;
}

// Enhanced fetch function with CSRF token handling for secure API interactions
async function fetchWithSettings(url, options) {
    const csrfToken = getCsrfToken();
    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${JSON.parse(localStorage.getItem('authData'))?.accessToken || ''}`
    };

    // Include CSRF token for state-changing methods (POST, PUT, DELETE, PATCH)
    const methodsRequiringCsrf = ['POST', 'PUT', 'DELETE', 'PATCH'];
    if (methodsRequiringCsrf.includes(options.method)) {
        defaultHeaders['X-XSRF-TOKEN'] = csrfToken;
    }

    console.log('Request URL:', url);
    console.log('Request Headers:', defaultHeaders);
    console.log('Request Options:', options);

    try {
        const response = await fetch(url, {
            ...options,
            headers: {
                ...defaultHeaders,
                ...options.headers
            },
            credentials: 'include'  // Ensure credentials are included with every request
        });

        const isJsonResponse = response.headers.get('Content-Type')?.includes('application/json');
        const responseBody = isJsonResponse ? await response.json() : await response.text();

        if (!response.ok) {
            console.error('Response status:', response.status);
            console.error('Response body:', responseBody);
            // Construct an error object and throw it
            const errorBody = typeof responseBody === 'object' ? responseBody : { message: responseBody };
            throw new Error(errorBody.message || 'Network response was not ok');
        }

        console.log('Response body:', responseBody);
        return responseBody;
    } catch (error) {
        console.error("Fetch failed:", error);
        throw error;
    }
}

// API interaction functions
export const login = async (username, password) => {
    try {
        const result = await fetchWithSettings(`${baseUrl}/login`, {
            method: 'POST',
            body: JSON.stringify({ usernameOrEmail: username, password })
        });

        console.log('Login successful, received token:', result);
        const authData = { accessToken: result };
        localStorage.setItem('authData', JSON.stringify(authData));
        return authData;
    } catch (error) {
        console.error("Login failed:", error);
        throw error;
    }
};

export const registerConsumer = async (username, password, email) => {
    try {
        // Attempt to register the user
        const registrationResult = await fetchWithSettings(`${baseUrl}/register-consumer`, {
            method: 'POST',
            body: JSON.stringify({ username, password, email })
        });

        console.log('Registration successful, attempting to log in...');

        // Automatically log in the user after successful registration
        const loginResult = await login(username, password);
        console.log('Login successful after registration:', loginResult);
        
        // Return the login result which includes the auth token
        return loginResult;
    } catch (error) {
        console.error("Error during registration or login:", error);
        throw error;
    }
};


export const resetPassword = async (email) => {
    return fetchWithSettings(`${baseUrl}/reset-password`, {
        method: 'POST',
        body: JSON.stringify({ email })
    });
};

export const updatePassword = async (token, newPassword) => {
    return fetchWithSettings(`${baseUrl}/update-password`, {
        method: 'POST',
        body: JSON.stringify({ token, newPassword })
    });
};



export const logout = async () => {
    await fetchWithSettings(`${baseUrl}/logout`, {
        method: 'POST'
    });
    localStorage.removeItem('authData');
};

export const getAllGames = async () => {
    return fetchWithSettings(`${baseUrl}/games`, {
        method: 'GET'
    });
};