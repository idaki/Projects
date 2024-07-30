export function getJwtToken() {
  const authData = JSON.parse(localStorage.getItem('authData'));
  return authData?.accessToken || '';
}

export function getCsrfToken() {
  const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
  return match ? match[2] : '';
}

export async function fetchWithSettings(url, options) {
  const defaultHeaders = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${getJwtToken()}`,
      'X-XSRF-TOKEN': getCsrfToken()
  };

  const mergedOptions = {
      ...options,
      headers: {
          ...defaultHeaders,
          ...options.headers
      },
      credentials: 'include'
  };

  console.log('Making request to:', url);
  console.log('Request method:', mergedOptions.method);
  console.log('Request headers:', mergedOptions.headers);
  if (mergedOptions.body) {
      console.log('Request body:', mergedOptions.body);
  }

  try {
      const response = await fetch(url, mergedOptions);

      if (response.status === 401) {
          localStorage.removeItem('authData');
          window.location.href = '/';
          return;
      }

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
