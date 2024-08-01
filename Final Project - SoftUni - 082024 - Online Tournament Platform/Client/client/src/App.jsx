// src/App.jsx
import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavigationModal from './components/nav-container/NavigationContainer';
import BodyModal from './components/body/body-container';
import FooterModal from './components/footer/FooterContainer';
import UpdatePasswordModal from './components/user-auth/update-password/UpdatePasswordModal';
import LoginForm from './components/user-auth/login/LoginModal';
import AuthContext from './context/authContext';

function App() {
  const { auth } = useContext(AuthContext);

  return (
    <Router>
      <NavigationModal />
      <BodyModal />
      <FooterModal />
      <Routes>
        <Route path="/newpassword" element={<UpdatePasswordModal />} />
        {/* Define other routes here */}
      </Routes>
    </Router>
  );
}

export default App;