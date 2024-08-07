import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function TeamCard({ id, name, img, members = [], userIds = [] }) {
    return (
      <div className="card h-100">
        {img && <img className="card-img-top" src={img} alt="Team" />}
        <div className="card-body p-4">
          <div className="text-center">
            <h5 className="fw-bolder">{name}</h5>
          </div>
          <ul className="list-group list-group-flush mt-3">
            {members.map((member, index) => (
              <li key={userIds[index]} className="list-group-item"> 
                <div className="d-flex align-items-center">
                  {member.imageUrl && (
                    <img
                      src={member.imageUrl}
                      alt={member.username}
                      className="rounded-circle"
                      style={{ width: '40px', height: '40px', marginRight: '10px' }}
                    />
                  )}
                  <span>{member}</span>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }