import React, { useState } from 'react';
import styles from '../instructor/Instructor.module.css';

const mockInstructors = [
  { id: 1, name: 'John Doe', subject: 'React' },
  { id: 2, name: 'Jane Smith', subject: 'JavaScript' },
  { id: 3, name: 'Mary Johnson', subject: 'Node.js' },
];

export default function InstructorContainer() {
  const [instructors] = useState(mockInstructors);

  return (
    <div className={styles.container}>
      <h2>Instructor Management</h2>
      <ul>
        {instructors.map(instructor => (
          <li key={instructor.id}>
            {instructor.name} - {instructor.subject}
          </li>
        ))}
      </ul>
    </div>
  );
}
