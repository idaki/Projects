import React from 'react';

export default function StudentTable({ students }) {
  return (
    <div className="student-table">
      <h3>Active Students</h3>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Start Date</th>
            <th>Exam Date</th>
            <th>Hours Taken</th>
            <th>Remaining Hours</th>
          </tr>
        </thead>
        <tbody>
          {students.map((student, index) => (
            <tr key={index}>
              <td>{student.firstName}</td>
              <td>{student.middleName}</td>
              <td>{student.lastName}</td>
              <td>{student.startDate}</td>
              <td>{student.examDate}</td>
              <td>{student.hoursTaken}</td>
              <td>{student.remainingHours}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
