// validation.js
export const validateEmail = (email) => {
    if (!email || !/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(email)) {
      return 'Please enter a valid email address';
    }
    return '';
  };
  