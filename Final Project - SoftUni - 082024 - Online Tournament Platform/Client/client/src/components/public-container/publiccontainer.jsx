import React from 'react';
import TournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import HeaderContainer from '../header/HeaderContainer';

export default function PublicContainer() {
  return (
    <div>
      <HeaderContainer/>
      <TournamentsContainer />

    </div>
  );
}
