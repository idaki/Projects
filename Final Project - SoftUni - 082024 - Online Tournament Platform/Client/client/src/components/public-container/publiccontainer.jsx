import React from 'react';
import PublicTournamentsContainer from '../tournaments/public-tournaments/PublicTournamentsContainer';
import HeaderContainer from '../header/HeaderContainer';

export default function PublicContainer() {
  return (
    <div>
      <HeaderContainer/>
      <PublicTournamentsContainer />

    </div>
  );
}
