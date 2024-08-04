import React from 'react';
import TeamCard from '../../../teams/TeamCard';
import 'bootstrap/dist/css/bootstrap.min.css';


export default function TournamentProductPageTeamContainer({ teams }) {
  return (
      <div className="container mt-4">
          <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
              {teams.map(team => (
                  <div key={team.id} className="col"> {/* Ensure unique key */}
                      <TeamCard
                          id={team.id}
                          name={team.teamName}
                          img={team.imageUrl} 
                          members={team.members}
                      />
                  </div>
              ))}
          </div>
      </div>
  );
}