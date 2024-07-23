const baseUrl = 'http://localhost:8080/api/games';

export const getAllGames = async () => {
  const response = await fetch(baseUrl);
  if (!response.ok) {
    throw new Error('Network response was not ok');
  }
  const result = await response.json();
  return result;
};
