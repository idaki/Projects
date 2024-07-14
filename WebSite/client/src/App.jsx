import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import NavigationModal from './components/nav-container/NavigationContainer';
import FooterModal from './components/footer/FooterContainer';
import BodyModal from './components/body/body-container';
import { AuthProvider } from './context/authContext';

function App() {
  return (
    <Router>
      <AuthProvider>
        <NavigationModal />
       
        <BodyModal />
        <FooterModal />
      </AuthProvider>
    </Router>
  );
}

export default App;
