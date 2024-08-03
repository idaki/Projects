import React from 'react';

export default function TournamentProductPageInfoContainer({ info }) {
  // Placeholder static data
  const tournamentInfo = info || {
    details: "This is a grand tournament with participants from around the world. It features several exciting matches, thrilling gameplay, and an engaging audience experience.",
  };

  return (
    <div>
      <h3>Tournament Information</h3>
      <p>{tournamentInfo.details}</p>
    </div>
  );
}