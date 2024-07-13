// validation.js

export const validateCreateTournamentForm = (formData) => {
    let valid = true;
    const errors = {
      title: '',
      category: '',
      summary: '',
      startDate: '',
      endDate: '',
      numberOfTeams: '',
      teamSize: '',
      countries: '',
      region: ''
    };
  
    if (formData.title.trim() === '') {
      errors.title = 'Title is required';
      valid = false;
    }
  
    if (formData.category.trim() === '') {
      errors.category = 'Category is required';
      valid = false;
    }
  
    if (formData.summary.trim() === '') {
      errors.summary = 'Summary is required';
      valid = false;
    }
  
    if (formData.startDate.trim() === '') {
      errors.startDate = 'Start date is required';
      valid = false;
    }
  
    if (formData.endDate.trim() === '') {
      errors.endDate = 'End date is required';
      valid = false;
    }
  
    if (formData.numberOfTeams < 1) {
      errors.numberOfTeams = 'Number of teams must be at least 1';
      valid = false;
    }
  
    if (formData.teamSize < 1) {
      errors.teamSize = 'Team size must be at least 1';
      valid = false;
    }
  
    if (formData.countries.length === 0) {
      errors.countries = 'At least one country is required';
      valid = false;
    }
  
    if (formData.region.trim() === '') {
      errors.region = 'Region is required';
      valid = false;
    }
  
    return { valid, errors };
  };
  