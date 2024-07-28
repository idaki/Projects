import React, { useContext } from 'react';
import { ViewProvider } from '../../context/viewContext';
import AuthContext from '../../context/authContext';
import ProfileModal from '../profile/ProfileModal';

export default function AuthenticatedContainer() {
  const { auth } = useContext(AuthContext);

  if (!auth || !auth.roles) {
    return <p>Unauthorized</p>;
  }


  return (
    <ViewProvider>

     <ProfileModal/>
    </ViewProvider>
  );
}