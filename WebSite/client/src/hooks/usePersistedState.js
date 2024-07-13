import { useState, useEffect } from 'react';

// Custom hook for debouncing
function useDebouncedEffect(effect, deps, delay) {
  useEffect(() => {
    const handler = setTimeout(() => effect(), delay);

    return () => clearTimeout(handler);
  }, [...deps, delay]);
}

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
