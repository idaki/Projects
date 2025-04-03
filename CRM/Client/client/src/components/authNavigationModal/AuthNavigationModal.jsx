import React, { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import AuthContext from '../../context/authContext';
import Sidebar from '../sidebar/SidebarContainer'; // Import Sidebar here

export default function AuthNavigationModal() {
  const { auth } = useContext(AuthContext);

  if (!auth) {
    return <p>Unauthorized</p>;
  }

  return <Sidebar />;
}
