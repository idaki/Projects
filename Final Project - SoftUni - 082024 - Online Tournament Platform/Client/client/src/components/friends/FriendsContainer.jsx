import React, { useState, useEffect } from 'react';
import FriendCard from './FriendCard';
import * as friendsService from '../../services/friendsService';

export default function FriendsContainer() {
    const [friends, setFriends] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        friendsService.getAll()
            .then(result => {
                setFriends(result);
                setIsLoading(false);
            })
            .catch(err => {
                console.log(err);
                setIsLoading(false);
            });
    }, []);

    return (
        <section className="py-5 min-vh-100 d-flex align-items-center justify-content-center">
            <div className="container">
                {isLoading ? (
                    <div className="d-flex justify-content-center align-items-center h-70">
                        Loading...
                    </div>
                ) : friends.length === 0 ? (
                    <div className="d-flex justify-content-center align-items-center h-70">
                        No friends available
                    </div>
                ) : (
                    <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                        {friends.map(friend => (
                            <div key={friend.id} className="col">
                                <FriendCard
                                    id={friend.id}
                                    first_name={friend.first_name}
                                    last_name={friend.last_name}
                                    img={friend.img} // Assuming img is a part of the friend's data
                                />
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </section>
    );
}
