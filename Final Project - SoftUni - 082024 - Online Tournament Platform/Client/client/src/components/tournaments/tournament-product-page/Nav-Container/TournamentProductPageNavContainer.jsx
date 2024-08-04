import React, { useContext } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import AuthContext from '../../../../context/authContext'; // Adjust the path as needed

export default function TournamentProductPageNavContainer({ setMainContent }) {
  const { auth } = useContext(AuthContext);

  return (
    <nav className="nav nav-tabs">
      <a className="nav-link" href="#" onClick={() => setMainContent('game')}>Game</a>
      {auth && (
        <a className="nav-link" href="#" onClick={() => setMainContent('teams')}>Teams</a>
      )}
      <a className="nav-link" href="#" onClick={() => setMainContent('info')}>Info</a>
    </nav>
  );
}
