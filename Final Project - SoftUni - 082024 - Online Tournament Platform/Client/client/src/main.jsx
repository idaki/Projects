import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';  // Ensure this import path is correct, you usually don't need to include the file extension
import { AuthProvider } from './context/authContext';  // Ensure this path is correct

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <AuthProvider>
      <App />
    </AuthProvider>
  </React.StrictMode>
);