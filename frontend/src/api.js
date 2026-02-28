import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const register = (userData) => api.post('/auth/register', userData);
export const login = (credentials) => api.post('/auth/login', credentials);
export const getAllApis = () => api.get('/api/all');
export const getMyApis = () => api.get('/api/my-apis');
export const createApi = (apiData) => api.post('/api/create', apiData);
export const deleteApi = (id) => api.delete(`/api/${id}`);
export const subscribe = (apiId) => api.post(`/subscription/subscribe/${apiId}`);
export const getMySubscriptions = () => api.get('/subscription/my-subscriptions');
export const rateApi = (ratingData) => api.post('/api/rate', ratingData);

export default api;
