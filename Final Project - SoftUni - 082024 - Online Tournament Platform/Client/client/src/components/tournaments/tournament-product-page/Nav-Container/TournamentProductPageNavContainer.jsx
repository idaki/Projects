import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function TournamentProductPageNavContainer({ setMainContent }) {
  return (
    <nav className="nav nav-tabs">
      <a className="nav-link" href="#" onClick={() => setMainContent('game')}>Game</a>
      <a className="nav-link" href="#" onClick={() => setMainContent('info')}>Info</a>
      <a className="nav-link" href="#" onClick={() => setMainContent('teams')}>Teams</a>
    </nav>
  );
}