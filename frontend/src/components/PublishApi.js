import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createApi } from '../api';

function PublishApi() {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    endpoint: '',
    method: 'GET'
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createApi(formData);
      setSuccess('API published successfully!');
      setTimeout(() => navigate('/my-apis'), 2000);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to publish API');
    }
  };

  return (
    <div className="container">
      <div className="auth-form" style={{ maxWidth: '700px', margin: '60px auto' }}>
        <div style={{ textAlign: 'center', marginBottom: '32px' }}>
          <h2>Publish New API</h2>
          <p style={{ color: '#718096', fontSize: '14px', marginTop: '8px' }}>
            Share your API with the marketplace community
          </p>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>API Name</label>
            <input
              type="text"
              placeholder="e.g., Weather Data API"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              required
            />
          </div>
          <div className="form-group">
            <label>Description</label>
            <textarea
              rows="4"
              placeholder="Describe what your API does and its key features..."
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              required
            />
          </div>
          <div className="form-group">
            <label>Endpoint URL</label>
            <input
              type="text"
              placeholder="https://api.example.com/v1/resource"
              value={formData.endpoint}
              onChange={(e) => setFormData({ ...formData, endpoint: e.target.value })}
              required
            />
          </div>
          <div className="form-group">
            <label>HTTP Method</label>
            <select
              value={formData.method}
              onChange={(e) => setFormData({ ...formData, method: e.target.value })}
            >
              <option value="GET">GET - Retrieve data</option>
              <option value="POST">POST - Create data</option>
              <option value="PUT">PUT - Update data</option>
              <option value="DELETE">DELETE - Remove data</option>
            </select>
          </div>
          <button type="submit" className="btn">Publish API</button>
          {error && <p className="error">{error}</p>}
          {success && <p className="success">{success}</p>}
        </form>
      </div>
    </div>
  );
}

export default PublishApi;
