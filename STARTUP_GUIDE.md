# API Marketplace - Startup Guide

## Quick Start

### 1. Start Backend
```bash
cd backend
./mvnw clean spring-boot:run
```

Wait for the message: "Started BackendApplication"

### 2. Start Frontend (in a new terminal)
```bash
cd frontend
npm start
```

### 3. Access the Application
Open your browser: **http://localhost:3000**

---

## Test Accounts

The application comes with pre-loaded test accounts:

### Provider Accounts (can publish APIs)
- **Email:** john@provider.com  
  **Password:** password123

- **Email:** sarah@provider.com  
  **Password:** password123

### Consumer Account (can subscribe to APIs)
- **Email:** mike@consumer.com  
  **Password:** password123

---

## Database Configuration

The app now uses **H2 in-memory database** (no MySQL setup needed!)

- Database resets on each restart
- Pre-loaded with 15 sample APIs
- H2 Console available at: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:devhub`
  - Username: `sa`
  - Password: (leave empty)

---

## Troubleshooting

### Backend won't start
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

### Frontend won't start
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
npm start
```

### Port already in use
```bash
# Kill process on port 8080 (backend)
lsof -ti:8080 | xargs kill -9

# Kill process on port 3000 (frontend)
lsof -ti:3000 | xargs kill -9
```

### Login fails
1. Make sure backend is running (check http://localhost:8080)
2. Use one of the test accounts above
3. Check browser console for errors (F12)

---

## Features

✅ User Registration & Login  
✅ JWT Authentication  
✅ API Marketplace with Search  
✅ Publish APIs (Provider role)  
✅ Subscribe to APIs  
✅ API Key Management  
✅ Modern UI with animations  

---

## API Endpoints

### Authentication
- POST `/auth/register` - Register new user
- POST `/auth/login` - Login user

### APIs
- GET `/api/all` - Get all APIs
- GET `/api/my-apis` - Get my published APIs
- POST `/api/create` - Publish new API
- DELETE `/api/{id}` - Delete API

### Subscriptions
- POST `/subscription/subscribe/{apiId}` - Subscribe to API
- GET `/subscription/my-subscriptions` - Get my subscriptions
