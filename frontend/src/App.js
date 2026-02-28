import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Marketplace from './components/Marketplace';
import PublishApi from './components/PublishApi';
import MyApis from './components/MyApis';
import MySubscriptions from './components/MySubscriptions';

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');
    if (token && userData) {
      setUser(JSON.parse(userData));
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  return (
    <Router>
      <div className="navbar">
        <h1>API Marketplace</h1>
        <nav>
          {user ? (
            <>
              <Link to="/marketplace">Marketplace</Link>
              {user.role === 'PROVIDER' && (
                <>
                  <Link to="/publish">Publish API</Link>
                  <Link to="/my-apis">My APIs</Link>
                </>
              )}
              <Link to="/subscriptions">My Subscriptions</Link>
              <span>
                {user.name} ({user.role})
              </span>
              <button onClick={handleLogout}>
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          )}
        </nav>
      </div>

      <Routes>
        <Route path="/login" element={user ? <Navigate to="/marketplace" /> : <Login setUser={setUser} />} />
        <Route path="/register" element={user ? <Navigate to="/marketplace" /> : <Register />} />
        <Route path="/marketplace" element={user ? <Marketplace /> : <Navigate to="/login" />} />
        <Route path="/publish" element={user && user.role === 'PROVIDER' ? <PublishApi /> : <Navigate to="/marketplace" />} />
        <Route path="/my-apis" element={user && user.role === 'PROVIDER' ? <MyApis /> : <Navigate to="/marketplace" />} />
        <Route path="/subscriptions" element={user ? <MySubscriptions /> : <Navigate to="/login" />} />
        <Route path="/" element={<Navigate to={user ? "/marketplace" : "/login"} />} />
      </Routes>
    </Router>
  );
}

export default App;
