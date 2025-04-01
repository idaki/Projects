import React, { useState, useContext } from 'react';
import AuthContext from '../../context/authContext';
import AuthenticatedContainer from '../authenticated-container/authenticatedcontainer';
import PublicContainer from '../public-container/publiccontainer';

export default function BodyModal() {
  const { auth } = useContext(AuthContext);

  return (
    <div>
      {auth && auth.accessToken ? (
        <AuthenticatedContainer />
      ) : (
        <PublicContainer />
      )}
    </div>
  );
}
