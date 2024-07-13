// validation.js

export const validateField = (name, value) => {
    let errorMessage = '';
    switch (name) {
      case 'username':
        errorMessage = value.trim() === '' ? 'Username is required' : '';
        break;
      case 'password':
        errorMessage = value.trim() === '' ? 'Password is required' : '';
        break;
      default:
        break;
    }
    return errorMessage;
  };
  
  export const validateForm = (formData) => {
    let isValid = true;
    let errors = {};
  
    for (const key in formData) {
      if (formData[key].trim() === '') {
        errors[key] = `${key.charAt(0).toUpperCase() + key.slice(1)} is required`;
        isValid = false;
      }
    }
  
    return { isValid, errors };
  };
  