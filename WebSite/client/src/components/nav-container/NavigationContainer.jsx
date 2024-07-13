import React, { useState, useEffect } from 'react';

import UserAuthContainerModal from '../user-auth/user-auth-container/UserAuthContainerModal';
import LoginModal from '../user-auth/login/LoginModal';
import { Link } from 'react-router-dom';



export default function NavigationModal() {
    const [loginClick, setLoginClick] = useState(false);
    const [isMobile, setIsMobile] = useState(false);
    const [isNavOpen, setIsNavOpen] = useState(false); // State to manage navigation visibility on mobile

    const handleLoginClick = () => {
        setLoginClick(true);
    };

    const handleClose = () => {
        console.log("Closing modal...");
        setLoginClick(false);
    };

    const handleCloseModal = () => {
        setLoginClick(false);
    };

    const handleToggleNav = () => {
        setIsNavOpen(!isNavOpen);
    };

    useEffect(() => {
        const handleResize = () => {
            setIsMobile(window.innerWidth <= 992); // Adjust the breakpoint as needed
            // Close the navigation when the window size changes on mobile
            if (window.innerWidth > 992) {
                setIsNavOpen(false);
            }
        };

        // Initial check on mount
        handleResize();

        // Event listener for window resize
        window.addEventListener('resize', handleResize);

        // Cleanup on unmount
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    useEffect(() => {
        // Close the login modal when loginClick state changes
        if (loginClick) {
            setIsNavOpen(false); // Close the navigation when the login button is clicked
        }
    }, [loginClick]);

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
                    {(isMobile || !isNavOpen) && ( // Conditionally render login button based on mobile view or closed navigation
                        <div className="d-flex">
                            <button className="btn btn-outline-dark" type="submit" onClick={handleLoginClick}>Login</button>
                        </div>
                    )}
                </div>
            </div>
        </nav>
    );
}