import React, { useState, useEffect } from 'react';
import FriendsSearchModal from '../friends-search/FriendsSearchModal';
import FriendsListModal from '../friends-list/FriendsListModal';
import { getAllFriends } from '../../../services/friendsService';
import Pagination from '../../pagination/PaginationModal';

export default function FriendsModal  () {
  const [friends, setFriends] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [friendsPerPage] = useState(20);

  useEffect(() => {
    const fetchFriends = async () => {
      try {
        const allFriends = await getAllFriends();
        setFriends(allFriends);
      } catch (error) {
        console.error('Failed to fetch friends:', error);
      }
    };

    fetchFriends();
  }, []);

  const indexOfLastFriend = currentPage * friendsPerPage;
  const indexOfFirstFriend = indexOfLastFriend - friendsPerPage;
  const currentFriends = friends.slice(indexOfFirstFriend, indexOfLastFriend);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div>
      <FriendsSearchModal />
      <FriendsListModal friends={currentFriends} />
      <Pagination 
        friendsPerPage={friendsPerPage} 
        totalFriends={friends.length} 
        paginate={paginate} 
        currentPage={currentPage}
      />
    </div>
  );
};


