import React from 'react';

export default function TournamentCard({ id, title, description, img }) {
    return (
        <div className="card h-100">
            {/* Product image */}
            {/* <img className="card-img-top" src={img} alt="Tournament" /> */}
            {/* Product details */}
            <div className="card-body p-4">
                <div className="text-center">
                    {/* Tournament name */}
                    <h5 className="fw-bolder">{title}</h5>
                    {/* Tournament description */}
                    {description}
                </div>
            </div>
            {/* Product actions */}
            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div className="text-center">
                    <a className="btn btn-outline-dark mt-auto" href="#">Learn More</a>
                </div>
            </div>
        </div>
    );
}
