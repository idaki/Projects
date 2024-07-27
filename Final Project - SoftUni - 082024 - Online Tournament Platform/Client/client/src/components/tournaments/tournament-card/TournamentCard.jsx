import React from 'react';

export default function TournamentCard({ id, title, description, img, onLearnMore }) {
    return (
        <div className="card h-100">
            <img className="card-img-top" src={img} alt={title} />
            
            <div className="card-body p-4">
                <div className="text-center">
                    <h5 className="fw-bolder">{title}</h5>
                    {description}
                </div>
            </div>
            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div className="text-center">
                    <a className="btn btn-outline-dark mt-auto" href="#" onClick={onLearnMore}>Learn More</a>
                </div>
            </div>
        </div>
    );
}
