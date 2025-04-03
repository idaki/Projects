import React, { useState } from 'react';
import SearchBar from "../search/SearchBar";
import StudentTable from "../table/StudentTable";
import StudentProfileContainer from "../studentProfile/studentProfileContainer/StudentProfileContainer";  

// Sample student data
const students = [
  {
    firstName: 'John',
    middleName: 'M.',
    lastName: 'Doe',
    startDate: '2023-01-15',
    examDate: '2023-06-15',
    hoursTaken: 20,
    remainingHours: 30,
  },
  {
    firstName: 'Jane',
    middleName: 'A.',
    lastName: 'Smith',
    startDate: '2023-03-10',
    examDate: '2023-09-10',
    hoursTaken: 15,
    remainingHours: 35,
  },
  // Add more students here
  {
    firstName: 'Sam',
    middleName: 'B.',
    lastName: 'Taylor',
    startDate: '2023-02-20',
    examDate: '2023-07-20',
    hoursTaken: 10,
    remainingHours: 50,
  },
  {
    firstName: 'Lisa',
    middleName: 'C.',
    lastName: 'Williams',
    startDate: '2023-04-01',
    examDate: '2023-08-10',
    hoursTaken: 30,
    remainingHours: 25,
  }
];

export default function StudentContainer() {
  const [searchQuery, setSearchQuery] = useState("");
  const [studentsList, setStudentsList] = useState(students);
  const [selectedStudent, setSelectedStudent] = useState(null); // State to store selected student

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleSearch = async () => {
    if (searchQuery) {
      const filteredStudents = students.filter(
        (student) =>
          student.firstName.toLowerCase().includes(searchQuery.toLowerCase()) ||
          student.lastName.toLowerCase().includes(searchQuery.toLowerCase())
      );
      setStudentsList(filteredStudents);
      
      // Automatically select the first student from filtered results
      if (filteredStudents.length > 0) {
        setSelectedStudent(filteredStudents[0]);
      } else {
        setSelectedStudent(null); // Reset if no student is found
      }
    } else {
      setStudentsList(students); // Reset to full list if search is empty
      setSelectedStudent(null); // Reset selection when search is cleared
    }
  };

  const handleAddNewStudent = () => {
    // Simulate adding a new student (you could show a modal here for details)
    const newStudent = {
      firstName: "New",
      middleName: "Student",
      lastName: "Test",
      startDate: "2023-04-01",
      examDate: "2023-10-01",
      hoursTaken: 0,
      remainingHours: 40,
    };
    setStudentsList([...studentsList, newStudent]);
  };

  const handleStudentSelect = (student) => {
    setSelectedStudent(student); // Set the selected student
  };

  return (
    <div>
      {/* Search Bar and Search Button in One Row */}
      <div className="d-flex align-items-center mb-3">
        <SearchBar keyword={searchQuery} onChange={handleSearchChange} />
        <button
          className="btn btn-primary ms-2"
          onClick={handleSearch}
        >
          Search
        </button>
      </div>

      {/* "Add New Student" Button Below */}
      <div className="mb-3">
        <button
          className="btn btn-success"
          onClick={handleAddNewStudent}
        >
          Add New Student
        </button>
      </div>

      {/* Student Table */}
      <StudentTable students={studentsList} onStudentSelect={handleStudentSelect} />

      {/* Show Profile when a student is selected */}
      {selectedStudent && (
        <div className="mt-5">
          <h2>Student Profile</h2>
          <StudentProfileContainer userDetails={selectedStudent} />
        </div>
      )}
    </div>
  );
}
