import React, { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import styles from '../mainfeed/MainFeedContainer';

import StudentContainer from '../student/studentContainer/StudentContainer';
import EditUserContainer from '../admin-panel/edit-users/EditUserModal';
import SettingsContainer from '../settings/settings-container/SettingsModal';
import CarContainer from '../car/carContainer/CarContainer';
import LessonContainer from '../lesson/LessonContainer';
import InstructorContainer from '../instructor/InstructorContainer';

export default function MainFeedContainer({ userDetails }) {
  const { mainContent } = useContext(ViewContext);

  // Map readable names for different content types
  const contentTitles = {
    student: "Student Management",
    "edit-users": "Edit Users",
    settings: "User Settings",
    car: "Car Information",
    lesson: "Lesson Schedule",
    instructor: "Instructor Profiles",
  };

  return (
    <div className="col-md-9">
      <div className="card mb-4">
        <div className="card-body">
          {/* Expanded title based on context */}
          <h5 className="card-title">
            {contentTitles[mainContent] || mainContent?.toUpperCase()}
          </h5>
        </div>
      </div>

      {mainContent === 'student' && <StudentContainer />}
      {mainContent === 'edit-users' && <EditUserContainer />}
      {mainContent === 'settings' && <SettingsContainer userDetails={userDetails} isOpen={true} toggle={() => {}} />}
      {mainContent === 'car' && <CarContainer />}
      {mainContent === 'lesson' && <LessonContainer />}
      {mainContent === 'instructor' && <InstructorContainer />}
    </div>
  );
}
