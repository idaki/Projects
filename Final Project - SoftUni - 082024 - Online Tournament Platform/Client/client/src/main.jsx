// src/main.jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';  // Ensure this import path is correct
import { AuthProvider } from './context/authContext';  // Ensure this path is correct
import './config/i18next'
import { ViewProvider } from './context/viewContext';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
 
  <AuthProvider>
  <ViewProvider>
    <App />
  </ViewProvider>
</AuthProvider>,
 
);