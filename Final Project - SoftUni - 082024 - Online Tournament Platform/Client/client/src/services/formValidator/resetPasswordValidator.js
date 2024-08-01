import i18next from 'i18next'; // Import i18next

export const validateEmail = (email) => {

  const t = i18next.t;

  if (!email || !/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(email)) {
    return t('errors.emailInvalid'); // Use the localized error message
  }
  return '';
};
