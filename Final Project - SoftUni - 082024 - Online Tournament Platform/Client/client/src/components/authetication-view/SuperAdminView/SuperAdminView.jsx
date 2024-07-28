import React, { useState, useEffect } from 'react';


import MainFeedModal from '../../mainfeed/MainFeedModal';

export default function SuperAdminView({ currentView, setMainContent }) {
  const [reload, setReload] = useState(false);

  useEffect(() => {
    if (currentView === 'friends') {
      setReload(prevReload => !prevReload); // Toggle reload state when 'friends' view is selected
    }
  }, [currentView]);

  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{currentView.toUpperCase()}</h5>
        </div>
      </div>
      {currentView === 'tournaments' && <SomeAdminComponent />}
      {/* Add other views for Super Admin here */}
      {/* <MainFeedModal currentView={currentView} setMainContent={setMainContent} /> */}
      <h1>Hello Master Admin</h1>
    </div>
  );
}
