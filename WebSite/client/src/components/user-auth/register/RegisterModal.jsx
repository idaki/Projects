import React, { useState, useContext } from 'react';
import AuthContext from '../../../context/authContext'; // Adjust the path as needed

export default function RegisterModal({ onClose }) {
  const { registerConsumerSubmitHandler } = useContext(AuthContext);

  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    newsletter: true,
    age: true
  });

  const [errors, setErrors] = useState({
    username: '',
    email: '',
    password: '',
    general: ''
  });

  const handleChange = (e) => {
    const { name, value, checked } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: name === 'newsletter' || name === 'age' ? checked : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Simple validation
    const newErrors = {};
    if (!formData.username) newErrors.username = 'Username is required';
    if (!formData.email) newErrors.email = 'Email is required';
    if (!formData.password) newErrors.password = 'Password is required';
    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      try {
        await registerConsumerSubmitHandler(formData); // Call the context handler to submit the form
        onClose(); // Close the modal on successful registration
      } catch (error) {
        setErrors(prevState => ({
          ...prevState,
          general: 'Registration failed',
        }));
      }
    }
  };

  return (
    <div className="position-relative p-4">
      <form onSubmit={handleSubmit}>
        {/* Username input */}
        <div className="form-outline mb-4">
          <input
            type="text"
            id="username"
            className={`form-control ${errors.username && 'is-invalid'}`}
            name="username"
            value={formData.username}
            onChange={handleChange}
            placeholder="Username"
          />
          {errors.username && <div className="invalid-feedback">{errors.username}</div>}
        </div>

        {/* Email input */}
        <div className="form-outline mb-4">
          <input
            type="email"
            id="email"
            className={`form-control ${errors.email && 'is-invalid'}`}
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Email"
          />
          {errors.email && <div className="invalid-feedback">{errors.email}</div>}
        </div>

        {/* Password input */}
        <div className="form-outline mb-4">
          <input
            type="password"
            id="password"
            className={`form-control ${errors.password && 'is-invalid'}`}
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Password"
          />
          {errors.password && <div className="invalid-feedback">{errors.password}</div>}
        </div>

        {/* Checkboxes */}
        <div className="row mb-4">
          <div className="col d-flex justify-content-start">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="newsletter"
                name="newsletter"
                checked={formData.newsletter}
                onChange={handleChange}
              />
              <label className="form-check-label" htmlFor="newsletter">
                Subscribe to our newsletter
              </label>
            </div>
          </div>
        </div>

        <div className="row mb-4">
          <div className="col d-flex justify-content-start">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="age"
                name="age"
                checked={formData.age}
                onChange={handleChange}
              />
              <label className="form-check-label" htmlFor="age">
                I am 16+ years old!
              </label>
            </div>
          </div>
        </div>

        {/* Submit button */}
        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-primary row mb-4">
            Sign up
          </button>
        </div>

        {errors.general && <div className="alert alert-danger">{errors.general}</div>}
      </form>
    </div>
  );
}
