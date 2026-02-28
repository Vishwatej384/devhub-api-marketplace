import React, { useState, useEffect } from 'react';
import { getAllApis, subscribe, rateApi } from '../api';

function Marketplace() {
  const [apis, setApis] = useState([]);
  const [filteredApis, setFilteredApis] = useState([]);
  const [message, setMessage] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const [ratingModal, setRatingModal] = useState({ show: false, apiId: null, apiName: '' });
  const [selectedRating, setSelectedRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);

  useEffect(() => {
    loadApis();
  }, []);

  useEffect(() => {
    if (searchTerm) {
      const filtered = apis.filter(api =>
        api.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        api.description.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredApis(filtered);
    } else {
      setFilteredApis(apis);
    }
  }, [searchTerm, apis]);

  const loadApis = async () => {
    try {
      const response = await getAllApis();
      setApis(response.data);
      setFilteredApis(response.data);
    } catch (err) {
      console.error('Failed to load APIs', err);
    }
  };

  const handleSubscribe = async (apiId) => {
    try {
      await subscribe(apiId);
      setMessage('Successfully subscribed! Check My Subscriptions for your API key.');
      setTimeout(() => setMessage(''), 3000);
      loadApis(); // Reload to update subscription status
    } catch (err) {
      setMessage(err.response?.data?.message || 'Subscription failed');
      setTimeout(() => setMessage(''), 3000);
    }
  };

  const openRatingModal = (apiId, apiName) => {
    const api = apis.find(a => a.id === apiId);
    setSelectedRating(api?.userRating || 0);
    setRatingModal({ show: true, apiId, apiName });
  };

  const closeRatingModal = () => {
    setRatingModal({ show: false, apiId: null, apiName: '' });
    setSelectedRating(0);
    setHoverRating(0);
  };

  const handleRateApi = async () => {
    if (selectedRating === 0) {
      alert('Please select a rating');
      return;
    }

    try {
      await rateApi({
        apiId: ratingModal.apiId,
        rating: selectedRating,
        comment: ''
      });
      setMessage('Rating submitted successfully!');
      setTimeout(() => setMessage(''), 3000);
      closeRatingModal();
      loadApis(); // Reload to update ratings
    } catch (err) {
      setMessage(err.response?.data?.message || 'Failed to submit rating');
      setTimeout(() => setMessage(''), 3000);
    }
  };

  const renderStars = (rating, count, interactive = false, apiId = null, apiName = '') => {
    const stars = [];
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 >= 0.5;

    for (let i = 1; i <= 5; i++) {
      if (i <= fullStars) {
        stars.push(
          <span 
            key={i} 
            style={{ color: '#fbbf24', fontSize: '16px', cursor: interactive ? 'pointer' : 'default' }}
            onClick={interactive ? () => openRatingModal(apiId, apiName) : undefined}
          >
            ★
          </span>
        );
      } else if (i === fullStars + 1 && hasHalfStar) {
        stars.push(
          <span 
            key={i} 
            style={{ color: '#fbbf24', fontSize: '16px', cursor: interactive ? 'pointer' : 'default' }}
            onClick={interactive ? () => openRatingModal(apiId, apiName) : undefined}
          >
            ★
          </span>
        );
      } else {
        stars.push(
          <span 
            key={i} 
            style={{ color: '#d1d5db', fontSize: '16px', cursor: interactive ? 'pointer' : 'default' }}
            onClick={interactive ? () => openRatingModal(apiId, apiName) : undefined}
          >
            ★
          </span>
        );
      }
    }

    return (
      <div style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
        {stars}
        {count !== undefined && (
          <span style={{ fontSize: '12px', color: '#9ca3af', marginLeft: '4px' }}>
            ({count})
          </span>
        )}
      </div>
    );
  };

  const renderInteractiveStars = () => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <span
          key={i}
          style={{
            color: i <= (hoverRating || selectedRating) ? '#fbbf24' : '#d1d5db',
            fontSize: '32px',
            cursor: 'pointer',
            transition: 'color 0.2s'
          }}
          onMouseEnter={() => setHoverRating(i)}
          onMouseLeave={() => setHoverRating(0)}
          onClick={() => setSelectedRating(i)}
        >
          ★
        </span>
      );
    }
    return stars;
  };

  return (
    <div className="container">
      <div className="page-title-section">
        <h2>API Marketplace</h2>
        <p>Discover, integrate, and manage top-tier APIs across diverse categories.</p>
      </div>

      <div className="search-bar">
        <input
          type="text"
          placeholder="Search APIs..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button>Search</button>
      </div>

      {message && <p className={message.includes('Success') || message.includes('successfully') ? 'success' : 'error'}>{message}</p>}

      <div style={{ marginBottom: '16px', color: '#4a5568', fontSize: '14px', fontWeight: '600' }}>
        All APIs ({filteredApis.length})
      </div>

      <div className="api-grid">
        {filteredApis.map((api) => (
          <div key={api.id} className="api-card">
            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '16px' }}>
              <div style={{
                width: '48px',
                height: '48px',
                borderRadius: '12px',
                background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                marginRight: '12px',
                color: 'white',
                fontSize: '20px',
                fontWeight: '700'
              }}>
                {api.name.charAt(0).toUpperCase()}
              </div>
              <div style={{ flex: 1 }}>
                <h3 style={{ marginBottom: '4px' }}>{api.name}</h3>
                <div style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
                  <span className="badge badge-primary">{api.method}</span>
                  {renderStars(api.averageRating || 0, api.ratingCount || 0, true, api.id, api.name)}
                </div>
              </div>
            </div>
            <p>{api.description}</p>
            <div className="endpoint">
              <strong>{api.method}</strong> {api.endpoint}
            </div>
            <div style={{ 
              fontSize: '12px', 
              color: '#a0aec0', 
              marginTop: '12px',
              paddingTop: '12px',
              borderTop: '1px solid #e2e8f0'
            }}>
              By: {api.createdBy?.name}
            </div>
            
            {api.isSubscribed ? (
              <button
                className="btn"
                style={{ 
                  marginTop: '16px',
                  background: '#e2e8f0',
                  color: '#4a5568',
                  cursor: 'default'
                }}
                disabled
              >
                ✓ Subscribed
              </button>
            ) : (
              <button
                onClick={() => handleSubscribe(api.id)}
                className="btn btn-success"
                style={{ marginTop: '16px' }}
              >
                Subscribe to API
              </button>
            )}
          </div>
        ))}
      </div>

      {filteredApis.length === 0 && (
        <div className="empty-state">
          <h3>No APIs Found</h3>
          <p>
            {searchTerm 
              ? 'Try adjusting your search terms'
              : 'No APIs available yet. Be the first to publish one!'}
          </p>
        </div>
      )}

      {/* Rating Modal */}
      {ratingModal.show && (
        <div style={{
          position: 'fixed',
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          background: 'rgba(0, 0, 0, 0.5)',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          zIndex: 1000
        }}>
          <div style={{
            background: 'white',
            borderRadius: '12px',
            padding: '32px',
            maxWidth: '400px',
            width: '90%',
            boxShadow: '0 20px 60px rgba(0, 0, 0, 0.3)'
          }}>
            <h3 style={{ marginBottom: '8px', color: '#1a202c' }}>Rate {ratingModal.apiName}</h3>
            <p style={{ color: '#718096', fontSize: '14px', marginBottom: '24px' }}>
              How would you rate this API?
            </p>
            
            <div style={{ display: 'flex', justifyContent: 'center', gap: '8px', marginBottom: '24px' }}>
              {renderInteractiveStars()}
            </div>

            <div style={{ display: 'flex', gap: '12px' }}>
              <button
                onClick={closeRatingModal}
                style={{
                  flex: 1,
                  padding: '12px',
                  background: '#e2e8f0',
                  color: '#4a5568',
                  border: 'none',
                  borderRadius: '8px',
                  cursor: 'pointer',
                  fontWeight: '600'
                }}
              >
                Cancel
              </button>
              <button
                onClick={handleRateApi}
                className="btn"
                style={{ flex: 1, margin: 0 }}
              >
                Submit Rating
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Marketplace;
