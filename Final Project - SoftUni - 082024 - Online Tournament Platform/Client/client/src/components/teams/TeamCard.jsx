import React from 'react';

export default function TeamCard({ id, name, img }) {
    return (
        <div className="card h-100">
            {img && <img className="card-img-top" src={img} alt="Team" />}
            <div className="card-body p-4">
                <div className="text-center">
                    <h5 className="fw-bolder">{name}</h5>
                </div>
            </div>
            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                <div className="text-center">
                    <a className="btn btn-outline-dark mt-auto" href="#">Learn More</a>
                </div>
            </div>
        </div>
    );
}
