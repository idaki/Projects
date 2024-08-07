const baseUrl =  'http://localhost:8080/api/tournaments';

export const getAll = async () => {
    const response = await fetch(baseUrl);
    const result = await response.json();

    const data = Object.values(result);
 
    return data;
};




export const getAllRegions = async () => {
    const baseUrl = 'http://localhost:8080/api/regions'; // Adjust the URL as per your API endpoint
    const response = await fetch(baseUrl);
    const regions = await response.json();
    return regions;
  };

  export const getAllCountriesByRegion = async (regionId) => {
    const baseUrl = `http://localhost:8080/api/countries/by-region/${regionId}`;
    try {
      const response = await fetch(baseUrl);
      if (!response.ok) {
        throw new Error('Failed to fetch countries: ' + response.statusText);
      }
      const countries = await response.json();
      return countries;
    } catch (error) {
      console.error('Error fetching countries:', error);
      throw error; // Rethrow the error to handle it in the caller function if needed
    }
  };