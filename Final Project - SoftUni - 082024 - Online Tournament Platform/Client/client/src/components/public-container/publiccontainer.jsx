import React from 'react';
import PublicTournamentsContainer from '../tournaments/all-tournaments/AllTournamentsContainer';
import HeaderContainer from '../header/HeaderContainer';

export default function PublicContainer() {
  return (
    <div>
      <HeaderContainer/>
      <PublicTournamentsContainer />

    </div>
  );
}
