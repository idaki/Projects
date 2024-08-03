export const validateCreateTournamentForm = (formData) => {
  const errors = {};

  if (!formData.game || formData.game.length === 0) {
    errors.game = 'Game is required';
  }

  if (!formData.category || formData.category.length === 0) {
    errors.category = 'Category is required';
  }

  if (!formData.summary || formData.summary.length === 0) {
    errors.summary = 'Summary is required';
  }

  if (!formData.startDate) {
    errors.startDate = 'Start date is required';
  }

  if (!formData.endDate) {
    errors.endDate = 'End date is required';
  }

  if (!formData.numberOfTeams || formData.numberOfTeams <= 0) {
    errors.numberOfTeams = 'Number of teams must be greater than 0';
  }

  if (!formData.teamSize || formData.teamSize <= 0) {
    errors.teamSize = 'Team size must be greater than 0';
  }

  return {
    valid: Object.keys(errors).length === 0,
    errors,
  };
};
