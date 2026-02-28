import React, { useState, useEffect } from 'react';
import { getMySubscriptions } from '../api';

function MySubscriptions() {
  const [subscriptions, setSubscriptions] = useState([]);
  const [copiedKey, setCopiedKey] = useState(null);

  useEffect(() => {
    loadSubscriptions();
  }, []);

  const loadSubscriptions = async () => {
    try {
      const response = await getMySubscriptions();
      setSubscriptions(response.data);
    } catch (err) {
      console.error('Failed to load subscriptions', err);
    }
  };

  const copyToClipboard = (text, id) => {
    navigator.clipboard.writeText(text);
    setCopiedKey(id);
    setTimeout(() => setCopiedKey(null), 2000);
  };

  return (
    <div className="container">
      <div className="page-title-section">
        <h2>My Subscriptions</h2>
        <p>Manage your API subscriptions and access keys</p>
      </div>

      <div style={{ marginBottom: '16px', color: '#4a5568', fontSize: '14px', fontWeight: '600' }}>
        Active Subscriptions ({subscriptions.length})
      </div>

      {subscriptions.map((sub) => (
        <div key={sub.id} className="subscription-card">
          <div style={{ display: 'flex', alignItems: 'flex-start', marginBottom: '16px' }}>
            <div style={{
              width: '56px',
              height: '56px',
              borderRadius: '12px',
              background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              marginRight: '16px',
              color: 'white',
              fontSize: '24px',
              fontWeight: '700',
              flexShrink: 0
            }}>
              {sub.api.name.charAt(0).toUpperCase()}
            </div>
            <div style={{ flex: 1 }}>
              <h3 style={{ marginBottom: '8px', fontSize: '22px' }}>{sub.api.name}</h3>
              <p style={{ color: '#718096', marginBottom: '12px' }}>{sub.api.description}</p>
              <div style={{ display: 'flex', gap: '12px', alignItems: 'center', flexWrap: 'wrap' }}>
                <span className="badge badge-primary">{sub.api.method}</span>
                <span style={{ fontSize: '12px', color: '#a0aec0' }}>
                  Provider: {sub.api.createdBy?.name}
                </span>
                <span style={{ fontSize: '12px', color: '#a0aec0' }}>
                  Subscribed: {new Date(sub.subscribedAt).toLocaleDateString()}
                </span>
              </div>
            </div>
          </div>

          <div className="endpoint">
            <strong>{sub.api.method}</strong> {sub.api.endpoint}
          </div>

          <div style={{ marginTop: '20px', padding: '16px', background: '#f7fafc', borderRadius: '8px', border: '1px solid #e2e8f0' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
              <strong style={{ color: '#2d3748', fontSize: '14px' }}>Your API Key</strong>
              <button
                onClick={() => copyToClipboard(sub.apiKey, sub.id)}
                style={{
                  padding: '6px 16px',
                  cursor: 'pointer',
                  background: copiedKey === sub.id ? '#48bb78' : 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                  color: 'white',
                  border: 'none',
                  borderRadius: '6px',
                  fontSize: '13px',
                  fontWeight: '600',
                  transition: 'all 0.2s ease'
                }}
              >
                {copiedKey === sub.id ? '✓ Copied!' : 'Copy Key'}
              </button>
            </div>
            <div style={{
              background: 'white',
              padding: '12px',
              borderRadius: '6px',
              fontFamily: "'Courier New', monospace",
              fontSize: '13px',
              color: '#2d3748',
              wordBreak: 'break-all',
              border: '1px solid #e2e8f0'
            }}>
              {sub.apiKey}
            </div>
          </div>
        </div>
      ))}

      {subscriptions.length === 0 && (
        <div className="empty-state">
          <h3>No Subscriptions Yet</h3>
          <p>Visit the marketplace to subscribe to APIs and start building</p>
        </div>
      )}
    </div>
  );
}

export default MySubscriptions;
