import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Added 'Route' to the imports
import NavigationModal from './components/nav-container/NavigationContainer';
import FooterModal from './components/footer/FooterContainer';
import BodyModal from './components/body/body-container';
import { AuthProvider } from './context/authContext';
import UpdatePasswordModal from './components/user-auth/update-password/UpdatePasswordModal';

function App() {
  return (
    <Router>
      <AuthProvider>
        <Routes>
          <Route path="/newpassword" element={<UpdatePasswordModal />} />
        </Routes>
        <NavigationModal />
        <BodyModal />
        <FooterModal />
      </AuthProvider>
    </Router>
  );
}

export default App;
