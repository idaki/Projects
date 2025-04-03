import { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import styles from '../sidebar/SidebarContainer.module.css'; // Use styles from the CSS Module

export default function Sidebar() {
  const { setMainContent } = useContext(ViewContext);

  return (
    <div className={styles.sidebar}>
      <a onClick={() => setMainContent('student')}>Student</a>
      <a onClick={() => setMainContent('settings')}>Settings</a>
      <a onClick={() => setMainContent('car')}>Car</a> {/* Added Car */}
      <a onClick={() => setMainContent('lesson')}>Lesson</a> {/* Added Lesson */}
      <a onClick={() => setMainContent('instructor')}>Instructor</a> {/* Added Instructor */}
    </div>
  );
}
