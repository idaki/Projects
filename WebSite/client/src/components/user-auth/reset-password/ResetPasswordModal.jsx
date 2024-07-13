import React, { useState } from 'react';
import { validateEmail } from '../../../services/formValidator/resetPasswordValidator'

export default function ResetPasswordModal({ onClose }) {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setEmail(e.target.value);
    setError('');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validate email format using the external validator
    const emailError = validateEmail(email);
    if (emailError) {
      setError(emailError);
      return;
    }
    // Handle reset password logic
    console.log("Reset password for email:", email);
    // Close modal
    onClose();
  };

  return (
    <div className="p-4">
      <form onSubmit={handleSubmit}>
        <div className="form-outline mb-4">
          <input
            type="email"
            value={email}
            onChange={handleChange}
            className={`form-control ${error && 'is-invalid'}`}
            placeholder="Email"
          />
          {error && <div className="invalid-feedback">{error}</div>}
        </div>
        <button type="submit" className="btn btn-primary mb-4">Reset Password</button>
      </form>
    </div>
  );
}
