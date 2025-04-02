import { useContext } from 'react';
import ViewContext from '../../context/viewContext';

export default function Sidebar() {
  const { setMainContent } = useContext(ViewContext);

  return (
    <div>
      <button onClick={() => setMainContent('student')}>Student</button>
      <button onClick={() => setMainContent('edit-users')}>Edit Users</button>
      <button onClick={() => setMainContent('settings')}>Settings</button>
      <button onClick={() => setMainContent('car')}>Car</button> {/* Added Car */}
      <button onClick={() => setMainContent('lesson')}>Lesson</button> {/* Added Lesson */}
      <button onClick={() => setMainContent('instructor')}>Instructor</button> {/* Added Instructor */}
    </div>
  );
}
