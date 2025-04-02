import React, { useState } from 'react';
import styles from '../carCard/CarCard.module.css';  // Ensure correct path

const CarCard = ({ car }) => {
  const [selectedVariant, setSelectedVariant] = useState('');

  // Function to handle dropdown selection
  const handleVariantChange = (event) => {
    setSelectedVariant(event.target.value);
  };

  // Handle action button clicks
  const handleActionClick = (action) => {
    alert(`${action} action for ${car.make} ${car.model} - Variant: ${selectedVariant}`);
  };

  return (
    <div className={styles.carCard}>
      <img src={car.image} alt={`${car.make} ${car.model}`} className={styles.image} />
      <h3>{car.make} {car.model}</h3>
      <p>{car.year}</p>

      {/* Dropdown to select vehicle variant */}
      <select onChange={handleVariantChange} value={selectedVariant} className={styles.dropdown}>
        <option value="">Select Variant</option>
        {car.variants.map((variant, index) => (
          <option key={index} value={variant}>
            {variant}
          </option>
        ))}
      </select>

      {/* Action Buttons */}
      <div className={styles.buttons}>
        <button onClick={() => handleActionClick('View Details')}>View Details</button>
        <button onClick={() => handleActionClick('Compare')}>Compare</button>
        <button onClick={() => handleActionClick('Add to Cart')}>Add to Cart</button>
      </div>
    </div>
  );
};

export default CarCard;
