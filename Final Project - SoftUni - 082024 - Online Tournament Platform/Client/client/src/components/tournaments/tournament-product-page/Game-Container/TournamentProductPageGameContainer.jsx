import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function TournamentProductPageGameContainer({ tournament }) {
  if (!tournament) {
    return <div>Loading...</div>;
  }

  return (
    <div className="card mb-4">
      <img src={tournament.url} className="card-img-top" alt={tournament.name} />
      <div className="card-body">
        <h5 className="card-title">{tournament.name}</h5>
        <p className="card-text">{tournament.description}</p>
      </div>
    </div>
  );
}