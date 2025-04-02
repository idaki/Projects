import React, { useState } from 'react';
import styles from './LessonContainer.module.css';

const mockLessons = [
  { id: 1, topic: 'React Basics', date: '2025-04-02' },
  { id: 2, topic: 'JavaScript Advanced', date: '2025-04-04' },
  { id: 3, topic: 'Node.js', date: '2025-04-06' },
];

export default function LessonContainer() {
  const [lessons] = useState(mockLessons);

  return (
    <div className={styles.container}>
      <h2>Lesson Management</h2>
      <ul>
        {lessons.map(lesson => (
          <li key={lesson.id}>
            {lesson.topic} - {lesson.date}
          </li>
        ))}
      </ul>
    </div>
  );
}
