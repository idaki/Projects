import i18next from 'i18next'; // Import i18next

export const validateField = (name, value) => {
  let errorMessage = '';

  // Get the translation function
  const t = i18next.t;

  switch (name) {
    case 'username':
      if (value.trim() === '') {
        errorMessage = t('errors.usernameRequired');
      } else if (value.length < 3) {
        errorMessage = t('errors.usernameMinLength');
      }
      break;
    case 'email':
      if (value.trim() === '') {
        errorMessage = t('errors.emailRequired');
      } else if (!/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(value)) {
        errorMessage = t('errors.emailInvalid');
      }
      break;
    case 'password':
      if (value.trim() === '') {
        errorMessage = t('errors.passwordRequired');
      } else if (value.length < 6) {
        errorMessage = t('errors.passwordMinLength');
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
