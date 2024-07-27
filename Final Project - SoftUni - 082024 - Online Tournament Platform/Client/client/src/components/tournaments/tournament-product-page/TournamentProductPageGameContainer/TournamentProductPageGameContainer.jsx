import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function TournamentProductPageGameContainer() {
    return (
        <div className="card">
            <img src="game_image.jpg" className="card-img-top" alt="Game" />
            <div className="card-body">
                <h5 className="card-title">Granblue Fantasy Versus: Rising</h5>
                <p className="card-text">July 19th 2024</p>
            </div>
        </div>
    );
}