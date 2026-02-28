# Quick Start Guide

## 🚀 Get Running in 5 Minutes

### Step 1: Database Setup (1 minute)
```bash
# Login to MySQL
mysql -u root -p

# Create database
CREATE DATABASE devhub;
exit;
```

### Step 2: Update Database Password (30 seconds)
Edit `backend/src/main/resources/application.properties` and change:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Start Backend (2 minutes)
```bash
cd backend
./mvnw spring-boot:run
```
Wait for: "Started BackendApplication in X seconds"

### Step 4: Start Frontend (2 minutes)
Open a new terminal:
```bash
cd frontend
npm install
npm start
```
Browser will open at http://localhost:3000

## 🎯 Test the Application

### Create Provider Account
1. Click "Register"
2. Fill in:
   - Name: Test Provider
   - Email: provider@test.com
   - Password: test123
   - Role: API Provider
3. Click Register → Login

### Publish an API
1. Click "Publish API"
2. Fill in:
   - Name: Test Weather API
   - Description: Returns weather data
   - Endpoint: https://api.weather.com/v1/current
   - Method: GET
3. Click "Publish API"

### Create Consumer Account
1. Logout
2. Register new account:
   - Name: Test Consumer
   - Email: consumer@test.com
   - Password: test123
   - Role: API Consumer
3. Login

### Subscribe to API
1. Go to "Marketplace"
2. Click "Subscribe" on the Weather API
3. Go to "My Subscriptions"
4. Copy your API key

## ✅ You're Done!

Your full-stack API marketplace is now running with:
- JWT authentication
- Role-based access
- API publishing
- Subscription management
- Auto-generated API keys

## 🔧 Troubleshooting

**Backend won't start?**
- Check MySQL is running: `mysql -u root -p`
- Verify database exists: `SHOW DATABASES;`
- Check port 8080 is free: `lsof -i :8080`

**Frontend won't start?**
- Delete node_modules: `rm -rf node_modules`
- Reinstall: `npm install`
- Check port 3000 is free: `lsof -i :3000`

**Can't login?**
- Check backend console for errors
- Verify user was created in database
- Clear browser localStorage

## 📝 Default Ports
- Backend: http://localhost:8080
- Frontend: http://localhost:3000
- MySQL: localhost:3306
