import React from 'react';
import styles from './TournamentCard.module.css';
import {getCsrfToken, fetchCsrfToken} from  '../../../utils/csrfUtils';
export default function TournamentCard({ id, title, description, img, onLearnMore }) {
  const handleLearnMoreClick = async () => {
    if (onLearnMore) {
      let csrfToken = getCsrfToken();

      if (!csrfToken) {
        csrfToken = await fetchCsrfToken();
      }

      onLearnMore(id); // Pass the tournament ID to the onLearnMore function
    }
  };

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
          <button className="btn btn-outline-dark mt-auto" onClick={handleLearnMoreClick}>Learn More</button>
        </div>
      </div>
    </div>
  );
}
