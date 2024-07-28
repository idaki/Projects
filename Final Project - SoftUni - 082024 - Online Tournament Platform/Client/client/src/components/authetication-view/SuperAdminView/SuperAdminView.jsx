import React, { useContext, useEffect, useState } from 'react';

import ViewContext from './../../../context/viewContext';
import AdminProfileModal from  '../../profiles/AdminProfile/AdminProfileModal';
export default function SuperAdminView() {
  const { mainContent, setMainContent } = useContext(ViewContext);
  const [reload, setReload] = useState(false);

  useEffect(() => {
    if (mainContent === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [mainContent]);

  return (
    <div >
   <AdminProfileModal/>
    </div>
  );
}
