// src/App.jsx
import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavigationModal from './components/nav-container/NavigationContainer';
import BodyModal from './components/body/body-container';
import FooterModal from './components/footer/FooterContainer';
import UpdatePasswordModal from './components/user-auth/update-password/UpdatePasswordModal';
import LoginForm from './components/user-auth/login/LoginModal';
import AuthContext from './context/authContext';
import RegisterModal from './components/user-auth/register/RegisterModal';
import LoginModal from './components/user-auth/login/LoginModal';
import ResetPasswordModal from './components/user-auth/reset-password/ResetPasswordModal';


function App() {
  const { auth } = useContext(AuthContext);

  return (
    <Router>
      <NavigationModal />
      <BodyModal />
      <FooterModal />
      <Routes>
        <Route path="/newpassword" element={<UpdatePasswordModal />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/register" element={<RegisterModal />} />
        <Route path="/reset-password" element={<ResetPasswordModal />} />
        {/* Add other routes as needed */}
      </Routes>
    </Router>
  );
}

export default App;