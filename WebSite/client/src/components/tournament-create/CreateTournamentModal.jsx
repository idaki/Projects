import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { getAllGames } from '../../services/api/gameService';
import { getAllRegions, getAllCountriesByRegion } from '../../services/api/locationService';

import { validateCreateTournamentForm } from '../../services/formValidator/createTournamentFormValidator';

export default function CreateTournamentModal({ onClose }) {
  const [formData, setFormData] = useState({
    title: '',
    category: '',
    summary: '',
    startDate: '',
    endDate: '',
    numberOfTeams: '',
    teamSize: '',
    region: '',
    country: []
  });

  const [errors, setErrors] = useState({
    title: '',
    category: '',
    summary: '',
    startDate: '',
    endDate: '',
    numberOfTeams: '',
    teamSize: '',
    region: '',
    country: ''
  });

  const [games, setGames] = useState([]);
  const [regions, setRegions] = useState([]);
  const [countries, setCountries] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [gamesData, regionsData] = await Promise.all([getAllGames(), getAllRegions()]);
        setGames(gamesData);
        setRegions(regionsData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {

    const fetchCountriesPerRegion = async () => {
      try {
        const countriesData = await getAllCountriesByRegion(formData.region);
        setCountries(countriesData);
        console.log('Fetched countries:', countriesData); // Log fetched countries
      } catch (error) {
        console.error('Error fetching countries:', error);
      }
    };

    fetchCountriesPerRegion();
  }, [formData.region]);


  const handleChange = (e) => {
    const { name, options } = e.target;
    const selectedCountryIds = Array.from(options)
      .filter(option => option.selected)
      .map(option => option.value);
    setFormData(prevState => ({
      ...prevState,
      [name]: selectedCountryIds,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const { valid, errors } = validateCreateTournamentForm(formData);
    if (valid) {
      console.log(formData);
      onClose();
    } else {
      setErrors(errors);
    }
  };





  return (
    <div className="container mt-5">
      <form onSubmit={handleSubmit}>
        <h1>Create Tournament</h1>

        <div className="form-group mb-3">
          <label htmlFor="title">Legendary title:</label>
          <select
            className={`form-control ${errors.title && 'is-invalid'}`}
            id="title"
            name="title"
            value={formData.title}
            onChange={handleChange}
          >
            <option key="default" value="">Select a game...</option>
            {games.map(game => (
              <option key={game.title} value={game.title}>{game.title}</option>
            ))}
          </select>

          {errors.title && <div className="invalid-feedback">{errors.title}</div>}
        </div>


        <div className="form-group mb-3">
          <label htmlFor="category">Category:</label>
          <input
            type="text"
            className={`form-control ${errors.category && 'is-invalid'}`}
            id="category"
            name="category"
            value={formData.category}
            onChange={handleChange}
            placeholder="Enter game category..."
          />
          {errors.category && <div className="invalid-feedback">{errors.category}</div>}
        </div>



        <div className="form-group mb-3">
          <label htmlFor="summary">Summary:</label>
          <textarea
            className={`form-control ${errors.summary && 'is-invalid'}`}
            id="summary"
            name="summary"
            value={formData.summary}
            onChange={handleChange}
          ></textarea>
          {errors.summary && <div className="invalid-feedback">{errors.summary}</div>}
        </div>

        <div className="form-group mb-3">
          <label htmlFor="startDate">Start Date:</label>
          <input
            type="date"
            className={`form-control ${errors.startDate && 'is-invalid'}`}
            id="startDate"
            name="startDate"
            value={formData.startDate}
            onChange={handleChange}
          />
          {errors.startDate && <div className="invalid-feedback">{errors.startDate}</div>}
        </div>

        <div className="form-group mb-3">
          <label htmlFor="endDate">End Date:</label>
          <input
            type="date"
            className={`form-control ${errors.endDate && 'is-invalid'}`}
            id="endDate"
            name="endDate"
            value={formData.endDate}
            onChange={handleChange}
          />
          {errors.endDate && <div className="invalid-feedback">{errors.endDate}</div>}
        </div>

        <div className="form-group mb-3">
          <label htmlFor="numberOfTeams">Number of Teams:</label>
          <input
            type="number"
            className={`form-control ${errors.numberOfTeams && 'is-invalid'}`}
            id="numberOfTeams"
            name="numberOfTeams"
            value={formData.numberOfTeams}
            onChange={handleChange}
            min="1"
          />
          {errors.numberOfTeams && <div className="invalid-feedback">{errors.numberOfTeams}</div>}
        </div>

        <div className="form-group mb-3">
          <label htmlFor="teamSize">Team Size:</label>
          <input
            type="number"
            className={`form-control ${errors.teamSize && 'is-invalid'}`}
            id="teamSize"
            name="teamSize"
            value={formData.teamSize}
            onChange={handleChange}
            min="1"
          />
          {errors.teamSize && <div className="invalid-feedback">{errors.teamSize}</div>}
        </div>



        <div className="form-group mb-3">
          <label htmlFor="region">Region:</label>

          <select
            className={`form-control ${errors.region && 'is-invalid'}`}
            id="region"
            name="region"
            value={formData.region}
            onChange={handleChange}
          >
            <option key="default" value="">Select a region...</option>
            {regions.map(region => (
              <option key={region.id} value={region.id}>{region.name}</option>
            ))}
          </select>

          {errors.region && <div className="invalid-feedback">{errors.region}</div>}
        </div>


        <div className="form-group mb-3">
          <label htmlFor="country">Country:</label>
          <select
            className={`form-control ${errors.country && 'is-invalid'}`}
            id="country"
            name="country"
            value={formData.country}
            onChange={handleChange}
            multiple // Enable multiple selection
          >
            <option key="default" value="">Select country...</option>
            {Array.isArray(countries) && countries.map(country => (
              <option key={country.id} value={country.id}>{country.name}</option>
            ))}
          </select>

          {errors.region && <div className="invalid-feedback">{errors.region}</div>}
        </div>


        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary">Create Tournament</button>
        </div>
      </form>
    </div>
  );
}