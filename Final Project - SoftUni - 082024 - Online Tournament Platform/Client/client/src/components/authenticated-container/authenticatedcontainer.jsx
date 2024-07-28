import React, { useContext } from 'react';
import { ViewProvider } from '../../context/viewContext';
import AdminUserView from '../authetication-view/AdminUserView/AdminUserView';
import SuperAdminView from '../authetication-view/SuperAdminView/SuperAdminView';
import AuthContext from '../../context/authContext';

export default function AuthenticatedContainer() {
  const { auth } = useContext(AuthContext);

  if (!auth || !auth.roles) {
    return <p>Unauthorized</p>;
  }

  let AdminViewComponent = null;

  if (auth.roles.includes('ROLE_ADMIN_SUPER')) {
    AdminViewComponent = SuperAdminView;
  } else if (auth.roles.includes('ROLE_ADMIN_USER')) {
    AdminViewComponent = AdminUserView;
  }

  return (
    <ViewProvider>
      {AdminViewComponent ? <AdminViewComponent /> : <p>Unauthorized</p>}
    </ViewProvider>
  );
}