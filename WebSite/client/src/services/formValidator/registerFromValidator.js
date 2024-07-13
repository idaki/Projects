// validation.js
export const validateField = (name, value) => {
  let errorMessage = '';

  switch (name) {
    case 'username':
      if (value.trim() === '') {
        errorMessage = 'Username is required';
      } else if (value.length < 3) {
        errorMessage = 'Username must be at least 3 characters long';
      }
      break;
    case 'email':
      if (value.trim() === '') {
        errorMessage = 'Email is required';
      } else if (!/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(value)) {
        errorMessage = 'Invalid email address';
      }
      break;
    case 'password':
      if (value.trim() === '') {
        errorMessage = 'Password is required';
      } else if (value.length < 6) {
        errorMessage = 'Password must be at least 6 characters long';
      }
      break;
    default:
      break;
  }

  return errorMessage;
};

export const validateForm = (formData) => {
  const errors = {};
  let isValid = true;

  for (const field in formData) {
    const error = validateField(field, formData[field]);
    if (error) {
      isValid = false;
      errors[field] = error;
    }
  }

  return { isValid, errors };
};
