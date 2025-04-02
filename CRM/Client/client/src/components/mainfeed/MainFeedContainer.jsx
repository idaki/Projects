import React, { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import styles from './MainFeedContainer.module.css';

import StudentContainer from '../sudent/StudentContainer';
import EditUserContainer from '../admin-panel/edit-users/EditUserModal';
import SettingsContainer from '../settings/settings-container/SettingsModal';
import CarContainer from '../Car/CarContainer';
import LessonContainer from '../lesson/LessonContainer';
import InstructorContainer from '../instructor/InstructorContainer';

export default function MainFeedContainer({ userDetails }) {
  const { mainContent } = useContext(ViewContext);

  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{mainContent?.toUpperCase()}</h5>
        </div>
      </div>

      {mainContent === 'student' && <StudentContainer />}
      {mainContent === 'edit-users' && <EditUserContainer />}
      {mainContent === 'settings' && <SettingsContainer userDetails={userDetails} isOpen={true} toggle={() => {}} />}
      {mainContent === 'car' && <CarContainer />} {/* Added for Car */}
      {mainContent === 'lesson' && <LessonContainer />} {/* Added for Lesson */}
      {mainContent === 'instructor' && <InstructorContainer />} {/* Added for Instructor */}
    </div>
  );
}
