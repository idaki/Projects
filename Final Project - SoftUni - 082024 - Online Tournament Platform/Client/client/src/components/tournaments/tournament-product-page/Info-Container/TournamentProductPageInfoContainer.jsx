import React from 'react';

export default function TournamentProductPageInfoContainer({ info }) {
  return (
    <div>
      <h3>Tournament Information</h3>
      <p><strong>Name:</strong> {info.name}</p>
      <p><strong>Category:</strong> {info.category}</p>
      <p><strong>Summary:</strong> {info.summary}</p>
      <p><strong>Start Date:</strong> {new Date(info.startDate).toLocaleDateString()}</p>
      <p><strong>End Date:</strong> {new Date(info.endDate).toLocaleDateString()}</p>
      <p><strong>Number of Teams:</strong> {info.numberOfTeams}</p>
      <p><strong>Team Size:</strong> {info.teamSize}</p>
    </div>
  );
}