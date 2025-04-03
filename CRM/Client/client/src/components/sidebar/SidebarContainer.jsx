import { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import styles from '../sidebar/SidebarContainer.module.css'; // Use styles from the CSS Module

export default function Sidebar() {
  const { setMainContent } = useContext(ViewContext);

  return (
    <div className={`col-12 col-md-3 col-lg-2 ${styles.sidebar}`}>
      <div className="list-group">
        <button className="list-group-item list-group-item-action" onClick={() => setMainContent('student')}>
          Student
        </button>
        <button className="list-group-item list-group-item-action" onClick={() => setMainContent('settings')}>
          Settings
        </button>
        <button className="list-group-item list-group-item-action" onClick={() => setMainContent('car')}>
          Car
        </button>
        <button className="list-group-item list-group-item-action" onClick={() => setMainContent('lesson')}>
          Lesson
        </button>
        <button className="list-group-item list-group-item-action" onClick={() => setMainContent('instructor')}>
          Instructor
        </button>
      </div>
    </div>
  );
}
