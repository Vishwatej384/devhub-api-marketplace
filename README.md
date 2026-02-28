# API Marketplace - Full Stack Application

A complete API marketplace platform where providers can publish APIs and consumers can discover and subscribe to them.

> 📚 **New to this project?** Start with **[INDEX.md](INDEX.md)** for a complete documentation guide!

## Features Implemented ✅

### Backend (Spring Boot)
- ✅ User Registration & Authentication with JWT
- ✅ Role-based access (PROVIDER/CONSUMER)
- ✅ API CRUD operations
- ✅ Subscription system with auto-generated API keys
- ✅ MySQL database integration
- ✅ BCrypt password hashing
- ✅ RESTful API endpoints

### Frontend (React)
- ✅ User registration and login
- ✅ API marketplace browsing
- ✅ Publish API (for providers)
- ✅ My APIs management (for providers)
- ✅ Subscribe to APIs
- ✅ View subscriptions with API keys
- ✅ Role-based navigation

## Tech Stack

**Backend:**
- Spring Boot 4.0.2
- Spring Security with JWT
- Spring Data JPA
- MySQL Database
- Lombok
- Maven

**Frontend:**
- React 18
- React Router v6
- Axios
- CSS3

## Setup Instructions

### Prerequisites
- Java 25
- Maven
- MySQL 8.0+
- Node.js 16+
- npm

### Backend Setup

1. **Configure MySQL Database**
   ```sql
   CREATE DATABASE devhub;
   ```

2. **Update Database Credentials**
   Edit `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Install Dependencies & Run**
   ```bash
   cd backend
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   Backend will start on `http://localhost:8080`

### Frontend Setup

1. **Install Dependencies**
   ```bash
   cd frontend
   npm install
   ```

2. **Start Development Server**
   ```bash
   npm start
   ```
   Frontend will start on `http://localhost:3000`

## API Endpoints

### Authentication
- `POST /auth/register` - Register new user
- `POST /auth/login` - Login and get JWT token

### API Management
- `GET /api/all` - Get all published APIs (public)
- `GET /api/my-apis` - Get my published APIs (requires auth)
- `POST /api/create` - Publish new API (requires auth)
- `DELETE /api/{id}` - Delete API (requires auth)

### Subscriptions
- `POST /subscription/subscribe/{apiId}` - Subscribe to an API (requires auth)
- `GET /subscription/my-subscriptions` - Get my subscriptions with API keys (requires auth)

## Usage Flow

### For API Providers:
1. Register with role "PROVIDER"
2. Login to get JWT token
3. Navigate to "Publish API"
4. Fill in API details (name, description, endpoint, method)
5. View and manage published APIs in "My APIs"

### For API Consumers:
1. Register with role "CONSUMER"
2. Login to get JWT token
3. Browse APIs in "Marketplace"
4. Click "Subscribe" on any API
5. View API keys in "My Subscriptions"
6. Copy API key to use in requests

## Project Structure

```
api-marketplace/
├── backend/
│   ├── src/main/java/com/devhub/backend/
│   │   ├── auth/           # Authentication controllers
│   │   ├── config/         # Security configuration
│   │   ├── controller/     # REST controllers
│   │   ├── dto/            # Data transfer objects
│   │   ├── entity/         # JPA entities
│   │   ├── repository/     # Data repositories
│   │   ├── service/        # Business logic
│   │   └── util/           # JWT utilities
│   └── pom.xml
└── frontend/
    ├── public/
    ├── src/
    │   ├── components/     # React components
    │   ├── api.js          # API client
    │   ├── App.js          # Main app component
    │   └── index.js        # Entry point
    └── package.json
```

## Testing with Postman

### 1. Register User
```
POST http://localhost:8080/auth/register
Body: {
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "PROVIDER"
}
```

### 2. Login
```
POST http://localhost:8080/auth/login
Body: {
  "email": "john@example.com",
  "password": "password123"
}
Response: { "token": "jwt_token_here", ... }
```

### 3. Create API (use token from login)
```
POST http://localhost:8080/api/create
Headers: Authorization: Bearer {token}
Body: {
  "name": "Weather API",
  "description": "Get weather data",
  "endpoint": "https://api.weather.com/v1/current",
  "method": "GET"
}
```

### 4. Subscribe to API
```
POST http://localhost:8080/subscription/subscribe/1
Headers: Authorization: Bearer {token}
```

## Security Features
- Passwords hashed with BCrypt
- JWT token-based authentication
- Protected endpoints requiring authentication
- Role-based access control
- CORS enabled for frontend communication

## Future Enhancements
- API usage analytics
- Rate limiting per subscription
- API documentation viewer
- Payment integration
- API versioning
- Search and filter functionality
- User profile management
- Email notifications

## License
MIT
