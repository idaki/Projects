import React, { useState } from 'react';
import styles from './CarContainer.module.css';
import CarCard from '../car/CarCard';  // Ensure correct path

const mockCars = [
  { id: 1, make: 'Toyota', model: 'Camry', year: 2020, status: 'Available', variants: ['Base', 'Sport', 'Premium'], image: 'toyota_camry.jpg' },
  { id: 2, make: 'Honda', model: 'Civic', year: 2021, status: 'Assigned', variants: ['LX', 'EX', 'Sport'], image: 'honda_civic.jpg' },
  { id: 3, make: 'Ford', model: 'Focus', year: 2019, status: 'In Maintenance', variants: ['SE', 'Titanium', 'Active'], image: 'ford_focus.jpg' }
];

export default function CarContainer() {
  const [cars, setCars] = useState(mockCars);

  return (
    <div className={styles.container}>
      <h2>Car Management</h2>

      {/* Car Cards List */}
      <div className={styles.carCardsContainer}>
        {cars.map(car => (
          <CarCard key={car.id} car={car} />
        ))}
      </div>

      {/* Button to Add New Car */}
      <button className={styles.addButton}>Add New Car</button>
    </div>
  );
}
