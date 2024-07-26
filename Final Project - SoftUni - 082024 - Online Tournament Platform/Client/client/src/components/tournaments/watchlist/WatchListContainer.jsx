import React, { useState, useEffect } from 'react';
import TournamentCard from '../tournament-card/TournamentCard';
import * as tournamentService from '../../../services/api/tournamentService';

export default function WatchlistContainer() {
    const [tournaments, setWatchlist] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        const fetchTournaments = async () => {
            try {
                const storedWatchlist = sessionStorage.getItem('myWatchList');
                if (storedWatchlist && !reload) {
                    setWatchlist(JSON.parse(storedWatchlist));
                    setIsLoading(false);
                } else {
                    const result = await tournamentService.getMyWatchList();
                    setWatchlist(result);
                    sessionStorage.setItem('myWatchList', JSON.stringify(result));
                    setIsLoading(false);
                }
            } catch (error) {
                console.error('Failed to fetch tournaments:', error);
                setIsLoading(false);
            }
        };

        fetchTournaments();
    }, [reload]);

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
                                />
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </section>
    );
}
