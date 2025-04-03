import React from 'react';
import LessonHistory from '../studentLessonTable/LessonHistory';
import StudentContactDetails from '../studentContactDetails/StudentContactDetails';

const StudentProfileContainer = () => {
    // Test user details data
    const userDetails = {
      firstName: 'John',
      lastName: 'Doe',
      email: 'johndoe@example.com',
      username: 'johndoe123',
    };
  
    // Sample lesson history data
    const lessons = [
      {
        classInfo: 'Driving Lesson 1',
        date: '2023-03-15',
        instructor: 'John Doe',
        car: 'Car Model A',
        type: 'Practical',
      },
      {
        classInfo: 'Theory Lesson 1',
        date: '2023-03-16',
        instructor: 'Jane Smith',
        car: 'N/A',
        type: 'Theoretical',
      },
      {
        classInfo: 'Driving Lesson 2',
        date: '2023-03-20',
        instructor: 'John Doe',
        car: 'Car Model B',
        type: 'Practical',
      },
      // You can add more random lessons here if needed
    ];
  
    // Handle button actions
    const handleSignUpClass = () => {
      alert("Signed up for the class!");
    };
  
    const handleUpdateInfo = () => {
      alert("Student info updated!");
    };
  
    const handleDeleteUser = () => {
      if (window.confirm("Are you sure you want to delete this user?")) {
        alert("User deleted!");
      }
    };
  
    const handleCloseProfile = () => {
      alert("Profile closed!");
    };
  
    return (
      <div className="container">
          <div className="mt-4">
            <button className="btn btn-info me-2" onClick={handleSignUpClass}>
              Sign Up for a Class
            </button>
            <button className="btn btn-warning me-2" onClick={handleUpdateInfo}>
              Update Info
            </button>
            <button className="btn btn-danger me-2" onClick={handleDeleteUser}>
              Delete User
            </button>
            <button className="btn btn-secondary" onClick={handleCloseProfile}>
              Close Profile
            </button>
          </div>
        <div className="row">
          {/* Contact Details Section */}
          <StudentContactDetails userDetails={userDetails} />
          
          {/* Lesson History Section */}
          <LessonHistory lessons={lessons} />
          
          {/* Buttons for Sign Up, Update, Delete, Close */}
        
        </div>
      </div>
    );
  };
  
  export default StudentProfileContainer;