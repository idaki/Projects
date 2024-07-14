import React from 'react';

export default function AuthenticatedContainer() {
  return (
    <div>
      <h1>Welcome, authenticated user!</h1>
      <p>This is content only visible to authenticated users.</p>
    </div>
  );
}
