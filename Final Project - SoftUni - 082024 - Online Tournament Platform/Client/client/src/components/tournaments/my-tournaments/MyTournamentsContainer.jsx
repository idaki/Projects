import React, { useState, useEffect } from 'react';
import TournamentCard from '../tournament-card/TournamentCard';
import * as tournamentService from '../../../services/api/tournamentService';

export default function MyTournamentsContainer({ onLearnMore }) {
    const [tournaments, setTournaments] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [reload, setReload] = useState(false); // Define reload state
  
    useEffect(() => {
      const fetchTournaments = async () => {
        try {
          const storedTournaments = sessionStorage.getItem('myTournamentsList');
          if (storedTournaments && !reload) {
            setTournaments(JSON.parse(storedTournaments));
            setIsLoading(false);
          } else {
            const result = await tournamentService.getMyTournaments(); // Call the function
            setTournaments(result);
            sessionStorage.setItem('myTournamentsList', JSON.stringify(result)); // Store in session storage
            setIsLoading(false);
          }
        } catch (error) {
          console.error('Failed to fetch tournaments:', error);
          setIsLoading(false);
        }
      };
  
      fetchTournaments();
    }, [reload]); // Ensure reload is defined and used
  
    return (
      <section className="py-5 min-vh-100 d-flex align-items-center justify-content-center">
        <div className="container">
          {isLoading ? (
            <div className="d-flex justify-content-center align-items-center h-70">
              Loading...
            </div>
          ) : tournaments.length === 0 ? (
            <div className="d-flex justify-content-center align-items-center h-70">
              No tournaments available
            </div>
          ) : (
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
              {tournaments.map(tournament => (
                <div key={tournament.id} className="col">
                  <TournamentCard
                    id={tournament.id}
                    title={tournament.name}
                    description={tournament.description}
                    img={tournament.url}
                    onLearnMore={() => onLearnMore(tournament)}
                  />
                </div>
              ))}
            </div>
          )}
        </div>
      </section>
    );
  }