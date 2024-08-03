import React, { useState, useEffect } from 'react';
import TournamentCard from '../tournament-card/TournamentCard';
import * as tournamentService from '../../../services/api/tournamentService';
import TournamentProductPageContainer from '../tournament-product-page/Product-Page-Container/TournamentProducPageContainer';

export default function MyTournamentsContainer() {
  const [tournaments, setTournaments] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedTournament, setSelectedTournament] = useState(null);

  useEffect(() => {
    const fetchTournaments = async () => {
      try {
        const storedTournaments = sessionStorage.getItem('myTournamentsList');
        if (storedTournaments) {
          setTournaments(JSON.parse(storedTournaments));
          setIsLoading(false);
        } else {
          const result = await tournamentService.getMyTournaments();
          setTournaments(result);
          sessionStorage.setItem('myTournamentsList', JSON.stringify(result));
          setIsLoading(false);
        }
      } catch (error) {
        console.error('Failed to fetch tournaments:', error);
        setIsLoading(false);
      }
    };

    fetchTournaments();
  }, []);

  const handleLearnMore = async (tournamentId) => {
    try {
      const tournamentData = await tournamentService.getTournamentById(tournamentId);
      setSelectedTournament(tournamentData);
    } catch (error) {
      console.error('Failed to fetch tournament:', error);
    }
  };

  if (selectedTournament) {
    return <TournamentProductPageContainer tournament={selectedTournament} />;
  }

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
                  onLearnMore={() => handleLearnMore(tournament.id)}
                />
              </div>
            ))}
          </div>
        )}
      </div>
    </section>
  );
}