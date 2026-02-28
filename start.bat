@echo off
echo ========================================
echo   API Marketplace Startup
echo ========================================
echo.

echo Starting Backend...
cd backend
start "Backend Server" cmd /k "mvnw.cmd spring-boot:run"
echo Backend starting in new window...
echo.

timeout /t 15 /nobreak

echo Starting Frontend...
cd ..\frontend
start "Frontend Server" cmd /k "npm start"
echo Frontend starting in new window...
echo.

echo ========================================
echo   Services Starting!
echo ========================================
echo.
echo Frontend: http://localhost:3000
echo Backend:  http://localhost:8080
echo.
echo Please wait 30 seconds for services to start
echo Then open http://localhost:3000 in your browser
echo.
echo Press any key to exit this window...
pause > nul
