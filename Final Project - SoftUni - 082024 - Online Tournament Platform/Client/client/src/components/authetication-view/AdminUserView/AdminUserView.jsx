import React, { useContext, useEffect, useState } from 'react';
import MainFeedModal from '../../mainfeed/MainFeedModal';
import ViewContext from './../../../context/viewContext';

export default function AdminUserView() {
  const { mainContent, setMainContent } = useContext(ViewContext);
  const [reload, setReload] = useState(false);

  useEffect(() => {
    if (mainContent === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [mainContent]);

  return (
    <div className={`col-md-9`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{mainContent.toUpperCase()}</h5>
        </div>
      </div>
      <MainFeedModal />
      <h1>Hello Admin User</h1>
    </div>
  );
}
