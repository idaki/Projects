export function getJwtToken() {
    const authData = JSON.parse(localStorage.getItem('authData'));
    return authData?.accessToken || '';
}

export function getCsrfToken() {
    const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
    return match ? match[2] : '';
}

export function fetchCsrfToken() {
    const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    return { token, header };
}

export function getCsrfTokenFromMeta() {
    const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    return { token, header };
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

// Function to set CSRF token in meta tags
export const setCsrfTokenInMetaTags = (token) => {
    let csrfMetaTag = document.querySelector('meta[name="_csrf"]');
    if (!csrfMetaTag) {
        csrfMetaTag = document.createElement('meta');
        csrfMetaTag.name = '_csrf';
        document.head.appendChild(csrfMetaTag);
    }
    csrfMetaTag.content = token;

    let csrfHeaderMetaTag = document.querySelector('meta[name="_csrf_header"]');
    if (!csrfHeaderMetaTag) {
        csrfHeaderMetaTag = document.createElement('meta');
        csrfHeaderMetaTag.name = '_csrf_header';
        document.head.appendChild(csrfHeaderMetaTag);
    }
    csrfHeaderMetaTag.content = 'X-CSRF-TOKEN';
};

