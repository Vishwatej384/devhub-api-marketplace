#!/bin/bash

echo "🚀 Starting API Marketplace..."
echo ""

# Check if MySQL is running
if ! command -v mysql &> /dev/null; then
    echo "❌ MySQL is not installed. Please install MySQL first."
    exit 1
fi

# Check if database exists
echo "📊 Checking database..."
DB_EXISTS=$(mysql -u root -p -e "SHOW DATABASES LIKE 'devhub';" 2>/dev/null | grep devhub)

if [ -z "$DB_EXISTS" ]; then
    echo "⚠️  Database 'devhub' not found."
    echo "Creating database..."
    mysql -u root -p -e "CREATE DATABASE devhub;"
    echo "✅ Database created!"
else
    echo "✅ Database exists!"
fi

echo ""
echo "🔧 Starting Backend..."
cd backend

# Check if Maven wrapper exists
if [ ! -f "./mvnw" ]; then
    echo "❌ Maven wrapper not found!"
    exit 1
fi

# Start backend in background
./mvnw spring-boot:run > ../backend.log 2>&1 &
BACKEND_PID=$!
echo "✅ Backend starting (PID: $BACKEND_PID)..."
echo "   Logs: backend.log"

# Wait for backend to start
echo "⏳ Waiting for backend to start..."
sleep 15

# Check if backend is running
if ps -p $BACKEND_PID > /dev/null; then
    echo "✅ Backend is running!"
else
    echo "❌ Backend failed to start. Check backend.log for errors."
    exit 1
fi

echo ""
echo "🎨 Starting Frontend..."
cd ../frontend

# Check if node_modules exists
if [ ! -d "node_modules" ]; then
    echo "📦 Installing dependencies..."
    npm install
fi

# Start frontend
echo "✅ Starting frontend..."
npm start > ../frontend.log 2>&1 &
FRONTEND_PID=$!
echo "✅ Frontend starting (PID: $FRONTEND_PID)..."
echo "   Logs: frontend.log"

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "✨ API Marketplace is starting!"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "📍 URLs:"
echo "   Frontend: http://localhost:3000"
echo "   Backend:  http://localhost:8080"
echo ""
echo "📝 Process IDs:"
echo "   Backend:  $BACKEND_PID"
echo "   Frontend: $FRONTEND_PID"
echo ""
echo "🛑 To stop:"
echo "   kill $BACKEND_PID $FRONTEND_PID"
echo ""
echo "📋 Logs:"
echo "   Backend:  tail -f backend.log"
echo "   Frontend: tail -f frontend.log"
echo ""
echo "⏳ Please wait 30 seconds for services to fully start..."
echo "   Then open http://localhost:3000 in your browser"
echo ""
