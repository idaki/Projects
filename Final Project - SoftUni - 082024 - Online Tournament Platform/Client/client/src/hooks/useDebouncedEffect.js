import { useEffect } from 'react';

// Custom hook for debouncing
function useDebouncedEffect(effect, deps, delay) {
  useEffect(() => {
    const handler = setTimeout(() => effect(), delay);

    return () => clearTimeout(handler);
  }, [...deps, delay]);
}

export default useDebouncedEffect;
