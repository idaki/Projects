// src/components/authenticated-container/authenticatedcontainer.jsx

import React from 'react';
import Profile from '../profile/ProfileModal';
import { ViewProvider } from '../../context/viewContext';

export default function AuthenticatedContainer() {
  return (
    <ViewProvider>
      <Profile />
    </ViewProvider>
  );
}
