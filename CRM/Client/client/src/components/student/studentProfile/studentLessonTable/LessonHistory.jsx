import React from 'react';

const LessonHistory = ({ lessons }) => {
  if (!lessons || lessons.length === 0) {
    return (
      <div className="bg-secondary-soft px-4 py-5 rounded mt-5">
        <h4>Classes Taken</h4>
        <p>No lessons available.</p>
      </div>
    );
  }

  return (
    <div className="bg-secondary-soft px-4 py-5 rounded mt-5">
      <h4>Classes Taken</h4>
      <table className="table table-striped mt-3">
        <thead>
          <tr>
            <th scope="col">Class Info</th>
            <th scope="col">Date</th>
            <th scope="col">Instructor</th>
            <th scope="col">Car</th>
            <th scope="col">Type</th>
          </tr>
        </thead>
        <tbody>
          {lessons.map((lesson, index) => (
            <tr key={index}>
              <td>{lesson.classInfo}</td>
              <td>{new Date(lesson.date).toLocaleDateString()}</td> {/* Format the date */}
              <td>{lesson.instructor}</td>
              <td>{lesson.car}</td>
              <td>{lesson.type}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LessonHistory;
