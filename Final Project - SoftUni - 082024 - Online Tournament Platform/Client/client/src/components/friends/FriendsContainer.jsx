import React, { useState, useEffect } from 'react';
import FriendCard from './FriendCard';
import * as friendsService from '../../services/friendsService';
import Pagination from '../pagination/PaginationModal';

export default function FriendsContainer({ reload }) {
  const [friends, setFriends] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [friendsPerPage, setFriendsPerPage] = useState(6); // Initial number of friends per page

  useEffect(() => {
    const storedFriends = sessionStorage.getItem('friendsList');
    if (storedFriends && !reload) {
      setFriends(JSON.parse(storedFriends));
      setIsLoading(false);
    } else {
      friendsService.getAll()
        .then(result => {
          setFriends(result);
          sessionStorage.setItem('friendsList', JSON.stringify(result));
          setIsLoading(false);
        })
        .catch(err => {
          console.log(err);
          setIsLoading(false);
        });
    }
  }, [reload]);

  // Get current friends
  const indexOfLastFriend = currentPage * friendsPerPage;
  const indexOfFirstFriend = indexOfLastFriend - friendsPerPage;
  const currentFriends = friends.slice(indexOfFirstFriend, indexOfLastFriend);

  // Change page
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

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
          <>
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
              {currentFriends.map(friend => (
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
            <Pagination
              friendsPerPage={friendsPerPage}
              totalFriends={friends.length}
              paginate={paginate}
              currentPage={currentPage}
            />
            <div className="d-flex justify-content-center align-items-center mt-3">
              <label htmlFor="friendsPerPage" className="me-2">Friends per page:</label>
              <select
                id="friendsPerPage"
                value={friendsPerPage}
                onChange={(e) => setFriendsPerPage(Number(e.target.value))}
                className="form-select w-auto"
              >
                <option value={6}>6</option>
                <option value={10}>10</option>
                <option value={25}>25</option>
                <option value={50}>50</option>
                <option value={100}>100</option>
              </select>
            </div>
          </>
        )}
      </div>
    </section>
  );
}
