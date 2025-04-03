import React, { useContext } from 'react';
import styles from './AuthNavigationModal.module.css';
import ViewContext from '../../context/viewContext';
import AuthContext from '../../context/authContext';
import Sidebar from '../sidebar/SidebarContainer'; // Import Sidebar here

export default function AuthNavigationModal() {
  const { setMainContent } = useContext(ViewContext);
  const { auth } = useContext(AuthContext);

  if (!auth) {
    return <p>Unauthorized</p>;
  }

  const handleLinkClick = (content) => {
    setMainContent(content);
  };

  return (
    <Sidebar/>
    // <div className="auth-navigation-modal">
 
    //   <h1>Navigation</h1>
    //   <button onClick={() => handleLinkClick('students')}>Students</button>
    //   <button onClick={() => handleLinkClick('instructors')}>Instructors</button>
    //   <button onClick={() => handleLinkClick('lessons')}>Lessons</button>
    //   <button onClick={() => handleLinkClick('cars')}>Cars</button>
    //   <button onClick={() => handleLinkClick('settings')}>Settings</button>
    // </div>
  );
}
