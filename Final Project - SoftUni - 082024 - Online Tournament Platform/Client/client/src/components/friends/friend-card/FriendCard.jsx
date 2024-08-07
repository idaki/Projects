import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './FriendCard.module.css';

export default function FriendCard({ id, first_name, last_name, avatar }) {
    return (
        <div className={`card mb-3 ${styles.cardContainer}`}>
            <div className="row g-0">
                <div className="col-md-4 d-flex align-items-center justify-content-center">
                    <img src={avatar} className={`img-fluid rounded-start ${styles.avatar}`} alt={`${first_name} ${last_name}`} />
                </div>
                <div className="col-md-8">
                    <div className={`card-body d-flex flex-column justify-content-between ${styles.cardBody}`}>
                        <div>
                            <h5 className="card-title">{first_name} {last_name}</h5>
                        </div>
                        <div>
                            <div className="dropdown mt-3">
                                <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                    Actions
                                </button>
                                <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a className="dropdown-item" href="#">Send Message</a></li>
                                    <li><a className="dropdown-item" href="#">Remove Friend</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
