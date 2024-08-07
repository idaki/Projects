import { useState } from 'react';
import useDebouncedEffect from './useDebouncedEffect'; // Ensure correct path

export default function usePersistedState(key, defaultValue) {
  const [state, setState] = useState(() => {
    const persistedState = localStorage.getItem(key);
    try {
      return persistedState ? JSON.parse(persistedState) : defaultValue;
    } catch (error) {
      console.error('Error reading from localStorage:', error);
      return defaultValue;
    }
  });

  useDebouncedEffect(() => {
    try {
      const serializedValue = JSON.stringify(state);
      localStorage.setItem(key, serializedValue);
    } catch (error) {
      console.error('Error writing to localStorage:', error);
    }
  }, [state], 500); // 500 ms delay

  return [state, setState];
}
