import React, { useState, useEffect } from 'react';
import { getMyApis, deleteApi } from '../api';

function MyApis() {
  const [apis, setApis] = useState([]);
  const [message, setMessage] = useState('');

  useEffect(() => {
    loadApis();
  }, []);

  const loadApis = async () => {
    try {
      const response = await getMyApis();
      setApis(response.data);
    } catch (err) {
      console.error('Failed to load APIs', err);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this API?')) {
      try {
        await deleteApi(id);
        setMessage('API deleted successfully');
        loadApis();
        setTimeout(() => setMessage(''), 3000);
      } catch (err) {
        setMessage('Failed to delete API');
        setTimeout(() => setMessage(''), 3000);
      }
    }
  };

  return (
    <div className="container">
      <div className="page-title-section">
        <h2>My Published APIs</h2>
        <p>Manage and monitor your published APIs</p>
      </div>
      {message && <p className={message.includes('success') ? 'success' : 'error'}>{message}</p>}
      
      <div style={{ marginBottom: '16px', color: '#4a5568', fontSize: '14px', fontWeight: '600' }}>
        Total APIs ({apis.length})
      </div>

      <div className="api-grid">
        {apis.map((api) => (
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
                <span className="badge badge-success">{api.method}</span>
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
              Created: {new Date(api.createdAt).toLocaleDateString()}
            </div>
            <button
              onClick={() => handleDelete(api.id)}
              className="btn btn-danger"
              style={{ marginTop: '16px' }}
            >
              Delete API
            </button>
          </div>
        ))}
      </div>
      {apis.length === 0 && (
        <div className="empty-state">
          <h3>No APIs Published Yet</h3>
          <p>Start by publishing your first API to the marketplace</p>
        </div>
      )}
    </div>
  );
}

export default MyApis;
