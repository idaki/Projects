// src/components/nav-container/NavigationContainer.jsx

import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';
import UserAuthContainerModal from '../user-auth/user-auth-container/UserAuthContainerModal';
import AuthContext from '../../context/authContext'; // Ensure this path is correct

export default function NavigationModal() {
  const [loginClick, setLoginClick] = useState(false);
  const [isMobile, setIsMobile] = useState(false);
  const [isNavOpen, setIsNavOpen] = useState(false);
  const { auth, logoutHandler } = useContext(AuthContext); // Use the AuthContext to get auth state and logout function

  const handleLoginClick = () => {
    setLoginClick(true);
  };

  const handleLogoutClick = () => {
    logoutHandler();
  };

  const handleCloseModal = () => {
    setLoginClick(false);
  };

  const handleToggleNav = () => {
    setIsNavOpen(!isNavOpen);
  };

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 992);
      if (window.innerWidth > 992) {
        setIsNavOpen(false);
      }
    };

    handleResize();
    window.addEventListener('resize', handleResize);

    return () => window.removeEventListener('resize', handleResize);
  }, []);

  useEffect(() => {
    if (loginClick) {
      setIsNavOpen(false);
    }
  }, [loginClick]);

  useEffect(() => {
    console.log("NavigationModal re-rendered. Auth state:", auth); // Debug log
  }, [auth]);

  return (
    <nav className={`navbar navbar-expand-lg navbar-light bg-light ${isMobile ? (isNavOpen ? 'show' : '') : ''}`}>
      {loginClick && <UserAuthContainerModal onClose={handleCloseModal} />}
      <div className="container-fluid px-4 px-lg-5">
        <a className="navbar-brand" href="#!">SerdicaGrid</a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" onClick={handleToggleNav}>
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className={`collapse navbar-collapse ${isNavOpen ? 'show' : ''}`} id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
            <li className="nav-item"><a className="nav-link" href="#!">Tournaments</a></li>
            <li className="nav-item"><a className="nav-link" href="#!">Ranking</a></li>
            <li className="nav-item"><a className="nav-link" href="#!">News</a></li>
          </ul>
          <div className="d-flex">
            {auth && auth.accessToken ? ( // Check if the user is authenticated
              <button className="btn btn-outline-dark" type="button" onClick={handleLogoutClick}>Logout</button>
            ) : (
              <button className="btn btn-outline-dark" type="button" onClick={handleLoginClick}>Login</button>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}
