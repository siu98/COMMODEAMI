import { useState, React } from 'react'
import { BrowserRouter as Router } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Navigation from './components/Navigation';
import Counter from './features/Counter';
import { useAuth } from './hooks/useAuth';

import './App.css'

function App() {
  const { isLoggedIn, logout } = useAuth();

  return (
      <Router>
          <Navigation isLoggedIn={isLoggedIn} handleLogout={logout} />
      </Router>
  );
}

export default App
