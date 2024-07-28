import React, { useContext, useEffect, useState } from 'react';
import MainFeedModal from '../../mainfeed/MainFeedModal';
import ViewContext from './../../../context/viewContext';

import UserProfileModal from '../../profiles/UserProfile/UserProfileModal';

export default function AdminUserView() {
  const { mainContent, setMainContent } = useContext(ViewContext);
  const [reload, setReload] = useState(false);

  useEffect(() => {
    if (mainContent === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [mainContent]);

  return (
    <div >
   <UserProfileModal/>
    </div>
  );
}
