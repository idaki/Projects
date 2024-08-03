import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';



export default function TournamentProductPageGameContainer({ game }) {
    return (
        <div className="card">
            <img src={game.imageUrl} className="card-img-top" alt={game.title} />
            <div className="card-body">
                <h5 className="card-title">{game.title}</h5>
                <p className="card-text">{game.description}</p>
            </div>
        </div>
    );
}