import React from 'react';

export default function TournamentProductPageNavContainer({ setMainContent }) {
  return (
    <div className="nav flex-column">
      <a className="nav-link" href="#" onClick={() => setMainContent('game')}>Game</a>
      <a className="nav-link" href="#" onClick={() => setMainContent('info')}>Info</a>
      <a className="nav-link" href="#" onClick={() => setMainContent('teams')}>Teams</a>
    </div>
  );
}
