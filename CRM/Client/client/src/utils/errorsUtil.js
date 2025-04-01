export const handleHttpError = async (response) => {
    const isJsonResponse = response.headers.get('Content-Type')?.includes('application/json');
    const responseBody = isJsonResponse ? await response.json() : await response.text();
  
    // Determine the error message
    let errorMessage = 'Network response was not ok';
    if (isJsonResponse) {
      errorMessage = responseBody.message || errorMessage;
    } else if (responseBody) {
      errorMessage = responseBody;
    }
  
    // Throw an error with the message
    throw new Error(errorMessage);
  };

  export function isJsonResponse(response) {
    const contentType = response.headers.get('Content-Type');
    return contentType && contentType.includes('application/json');
  }