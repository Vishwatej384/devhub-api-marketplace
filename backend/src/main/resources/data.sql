-- Sample Users (passwords are 'password123' hashed with BCrypt)
INSERT INTO "user" (id, name, email, password, role) VALUES
(1, 'John Provider', 'john@provider.com', '$2a$10$xqKXKZ5qYJZQZ5qYJZQZ5uK5qYJZQZ5qYJZQZ5qYJZQZ5qYJZQZ5q', 'PROVIDER');
INSERT INTO "user" (id, name, email, password, role) VALUES
(2, 'Sarah Developer', 'sarah@provider.com', '$2a$10$xqKXKZ5qYJZQZ5qYJZQZ5uK5qYJZQZ5qYJZQZ5qYJZQZ5qYJZQZ5q', 'PROVIDER');
INSERT INTO "user" (id, name, email, password, role) VALUES
(3, 'Mike Consumer', 'mike@consumer.com', '$2a$10$xqKXKZ5qYJZQZ5qYJZQZ5uK5qYJZQZ5qYJZQZ5qYJZQZ5qYJZQZ5q', 'CONSUMER');
INSERT INTO "user" (id, name, email, password, role) VALUES
(4, 'Emma Tech', 'emma@provider.com', '$2a$10$xqKXKZ5qYJZQZ5qYJZQZ5uK5qYJZQZ5qYJZQZ5qYJZQZ5qYJZQZ5q', 'PROVIDER');
INSERT INTO "user" (id, name, email, password, role) VALUES
(5, 'Alex Builder', 'alex@provider.com', '$2a$10$xqKXKZ5qYJZQZ5qYJZQZ5uK5qYJZQZ5qYJZQZ5qYJZQZ5qYJZQZ5q', 'PROVIDER');

-- Sample APIs
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (1, 'Weather API', 'Get real-time weather data for any location worldwide. Includes temperature, humidity, wind speed, and forecasts.', 'https://api.weather.com/v1/current', 'GET', 1, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (2, 'Payment Gateway API', 'Secure payment processing for credit cards, debit cards, and digital wallets. PCI-DSS compliant.', 'https://api.payments.com/v2/charge', 'POST', 1, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (3, 'Geolocation API', 'Convert addresses to coordinates and vice versa. Includes distance calculation and route optimization.', 'https://api.maps.com/v1/geocode', 'GET', 2, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (4, 'Email Service API', 'Send transactional and marketing emails with templates, tracking, and analytics.', 'https://api.emailservice.com/v1/send', 'POST', 2, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (5, 'SMS Notification API', 'Send SMS messages globally with delivery reports and two-way messaging support.', 'https://api.sms.com/v1/messages', 'POST', 4, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (6, 'Image Processing API', 'Resize, crop, filter, and optimize images. Supports all major formats including WebP.', 'https://api.images.com/v1/process', 'POST', 4, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (7, 'Currency Exchange API', 'Real-time currency conversion rates for 150+ currencies. Historical data available.', 'https://api.forex.com/v1/rates', 'GET', 5, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (8, 'Stock Market API', 'Live stock prices, historical data, and market analysis for global exchanges.', 'https://api.stocks.com/v2/quotes', 'GET', 5, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (9, 'Translation API', 'Translate text between 100+ languages using neural machine translation.', 'https://api.translate.com/v1/translate', 'POST', 1, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (10, 'QR Code Generator API', 'Generate custom QR codes with logos, colors, and various formats (PNG, SVG, PDF).', 'https://api.qrcode.com/v1/generate', 'POST', 2, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (11, 'PDF Generation API', 'Convert HTML to PDF with custom headers, footers, and watermarks.', 'https://api.pdf.com/v1/convert', 'POST', 4, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (12, 'Voice Recognition API', 'Convert speech to text in real-time. Supports 50+ languages and accents.', 'https://api.voice.com/v1/recognize', 'POST', 5, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (13, 'Sentiment Analysis API', 'Analyze text sentiment (positive, negative, neutral) with confidence scores.', 'https://api.sentiment.com/v1/analyze', 'POST', 1, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (14, 'Face Detection API', 'Detect and analyze faces in images. Includes age, gender, and emotion detection.', 'https://api.faces.com/v1/detect', 'POST', 2, CURRENT_TIMESTAMP());
INSERT INTO api (id, name, description, endpoint, method, created_by, created_at) VALUES (15, 'Barcode Scanner API', 'Read and decode barcodes and QR codes from images. Supports all major formats.', 'https://api.barcode.com/v1/scan', 'POST', 4, CURRENT_TIMESTAMP());

-- Sample Subscriptions
INSERT INTO subscription (id, user_id, api_id, api_key, subscribed_at) VALUES (1, 3, 1, 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', CURRENT_TIMESTAMP());
INSERT INTO subscription (id, user_id, api_id, api_key, subscribed_at) VALUES (2, 3, 3, 'b2c3d4e5-f6a7-8901-bcde-fa2345678901', CURRENT_TIMESTAMP());
INSERT INTO subscription (id, user_id, api_id, api_key, subscribed_at) VALUES (3, 3, 7, 'c3d4e5f6-a7b8-9012-cdef-ab3456789012', CURRENT_TIMESTAMP());
INSERT INTO subscription (id, user_id, api_id, api_key, subscribed_at) VALUES (4, 3, 9, 'd4e5f6a7-b8c9-0123-defa-bc4567890123', CURRENT_TIMESTAMP());
