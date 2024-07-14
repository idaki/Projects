import React from 'react';
import TournamentsContainer from '../tournament-container/TournamentsContainer';
import HeaderContainer from '../header/HeaderContainer';

export default function PublicContainer() {
  return (
    <div>
      <HeaderContainer/>
      <TournamentsContainer />

    </div>
  );
}
