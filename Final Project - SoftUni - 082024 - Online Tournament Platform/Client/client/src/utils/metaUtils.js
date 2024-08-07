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