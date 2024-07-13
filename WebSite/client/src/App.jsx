import React from "react";
import NavigationModal from "./components/nav-container/NavigationContainer";
import FooterModal from './components/footer/FooterContainer';
import HeaderModal from "./components/header/HeaderContainer";
import TournamentsContainer from "./components/tournament-container/TournamentsContainer";
import { AuthProvider } from "./context/authContext";
import { BrowserRouter as Router } from 'react-router-dom';

function App() {
  return (
    <Router>
      <AuthProvider>
        <NavigationModal />
        <HeaderModal />
        <TournamentsContainer />
        <FooterModal />
      </AuthProvider>
    </Router>
  );
}

export default App;
