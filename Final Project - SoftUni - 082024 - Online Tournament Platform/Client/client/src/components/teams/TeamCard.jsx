import React from 'react';

export default function TeamCard({ id, first_name, last_name, img }) {
    return (
        <div className="card h-100">
            {/* Product image */}
            {img && <img className="card-img-top" src={img} alt="Friend" />}
            {/* Product details */}
            <div className="card-body p-4">
                <div className="text-center">
                    {/* Friend first name */}
                    <h5 className="fw-bolder">{first_name}</h5>
                    {/* Friend last name */}
                    {last_name}
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
