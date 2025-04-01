import i18next from 'i18next'; // Import i18next

export const validateField = (name, value) => {
  let errorMessage = '';

  // Get the translation function
  const t = i18next.t;

  switch (name) {
    case 'username':
      errorMessage = value.trim() === '' ? t('errors.usernameRequired') : '';
      break;
    case 'password':
      errorMessage = value.trim() === '' ? t('errors.passwordRequired') : '';
      break;
    default:
      break;
  }

  return errorMessage;
};

export const validateForm = (formData) => {
  let isValid = true;
  let errors = {};

  // Get the translation function
  const t = i18next.t;

  for (const key in formData) {
    if (formData[key].trim() === '') {
      errors[key] = t(`errors.${key}Required`);
      isValid = false;
    }
  }

  return { isValid, errors };
};
