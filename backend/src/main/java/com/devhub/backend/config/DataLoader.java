package com.devhub.backend.config;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Subscription;
import com.devhub.backend.entity.User;
import com.devhub.backend.repository.ApiRepository;
import com.devhub.backend.repository.SubscriptionRepository;
import com.devhub.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                    ApiRepository apiRepository,
                                    SubscriptionRepository subscriptionRepository,
                                    BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Only load data if database is empty
            if (userRepository.count() > 0) {
                return;
            }

            // Create sample users
            User provider1 = new User();
            provider1.setName("John Provider");
            provider1.setEmail("john@provider.com");
            provider1.setPassword(passwordEncoder.encode("password123"));
            provider1.setRole(User.Role.PROVIDER);
            provider1 = userRepository.save(provider1);

            User provider2 = new User();
            provider2.setName("Sarah Developer");
            provider2.setEmail("sarah@provider.com");
            provider2.setPassword(passwordEncoder.encode("password123"));
            provider2.setRole(User.Role.PROVIDER);
            provider2 = userRepository.save(provider2);

            User consumer1 = new User();
            consumer1.setName("Mike Consumer");
            consumer1.setEmail("mike@consumer.com");
            consumer1.setPassword(passwordEncoder.encode("password123"));
            consumer1.setRole(User.Role.CONSUMER);
            consumer1 = userRepository.save(consumer1);

            User provider3 = new User();
            provider3.setName("Emma Tech");
            provider3.setEmail("emma@provider.com");
            provider3.setPassword(passwordEncoder.encode("password123"));
            provider3.setRole(User.Role.PROVIDER);
            provider3 = userRepository.save(provider3);

            User provider4 = new User();
            provider4.setName("Alex Builder");
            provider4.setEmail("alex@provider.com");
            provider4.setPassword(passwordEncoder.encode("password123"));
            provider4.setRole(User.Role.PROVIDER);
            provider4 = userRepository.save(provider4);

            // Create 30 sample APIs
            Api api1 = new Api();
            api1.setName("Weather API");
            api1.setDescription("Get real-time weather data for any location worldwide. Includes temperature, humidity, wind speed, and 7-day forecasts with hourly breakdowns.");
            api1.setEndpoint("https://api.weather.com/v1/current");
            api1.setMethod("GET");
            api1.setCreatedBy(provider1);
            api1 = apiRepository.save(api1);

            Api api2 = new Api();
            api2.setName("Payment Gateway API");
            api2.setDescription("Secure payment processing for credit cards, debit cards, and digital wallets. PCI-DSS compliant with fraud detection and 3D Secure support.");
            api2.setEndpoint("https://api.payments.com/v2/charge");
            api2.setMethod("POST");
            api2.setCreatedBy(provider1);
            api2 = apiRepository.save(api2);

            Api api3 = new Api();
            api3.setName("Geolocation API");
            api3.setDescription("Convert addresses to coordinates and vice versa. Includes distance calculation, route optimization, place search, and timezone lookup.");
            api3.setEndpoint("https://api.maps.com/v1/geocode");
            api3.setMethod("GET");
            api3.setCreatedBy(provider2);
            api3 = apiRepository.save(api3);

            Api api4 = new Api();
            api4.setName("Email Service API");
            api4.setDescription("Send transactional and marketing emails with customizable templates, tracking, analytics, bounce handling, and A/B testing.");
            api4.setEndpoint("https://api.emailservice.com/v1/send");
            api4.setMethod("POST");
            api4.setCreatedBy(provider2);
            api4 = apiRepository.save(api4);

            Api api5 = new Api();
            api5.setName("SMS Notification API");
            api5.setDescription("Send SMS messages globally to 200+ countries with delivery reports, two-way messaging, scheduling, and Unicode support.");
            api5.setEndpoint("https://api.sms.com/v1/messages");
            api5.setMethod("POST");
            api5.setCreatedBy(provider3);
            api5 = apiRepository.save(api5);

            Api api6 = new Api();
            api6.setName("Image Processing API");
            api6.setDescription("Resize, crop, filter, and optimize images. Supports all major formats including WebP, AVIF with AI-powered enhancements and background removal.");
            api6.setEndpoint("https://api.images.com/v1/process");
            api6.setMethod("POST");
            api6.setCreatedBy(provider3);
            api6 = apiRepository.save(api6);

            Api api7 = new Api();
            api7.setName("Currency Exchange API");
            api7.setDescription("Real-time currency conversion rates for 150+ currencies. Historical data, charts, trend analysis, and crypto support available.");
            api7.setEndpoint("https://api.forex.com/v1/rates");
            api7.setMethod("GET");
            api7.setCreatedBy(provider4);
            api7 = apiRepository.save(api7);

            Api api8 = new Api();
            api8.setName("Stock Market API");
            api8.setDescription("Live stock prices, historical data, and market analysis for global exchanges. Real-time quotes, portfolio tracking, and technical indicators.");
            api8.setEndpoint("https://api.stocks.com/v2/quotes");
            api8.setMethod("GET");
            api8.setCreatedBy(provider4);
            api8 = apiRepository.save(api8);

            Api api9 = new Api();
            api9.setName("Translation API");
            api9.setDescription("Translate text between 100+ languages using neural machine translation. Supports document translation, language detection, and glossary management.");
            api9.setEndpoint("https://api.translate.com/v1/translate");
            api9.setMethod("POST");
            api9.setCreatedBy(provider1);
            api9 = apiRepository.save(api9);

            Api api10 = new Api();
            api10.setName("QR Code Generator API");
            api10.setDescription("Generate custom QR codes with logos, colors, and various formats (PNG, SVG, PDF). Includes analytics, tracking, and dynamic QR codes.");
            api10.setEndpoint("https://api.qrcode.com/v1/generate");
            api10.setMethod("POST");
            api10.setCreatedBy(provider2);
            api10 = apiRepository.save(api10);

            Api api11 = new Api();
            api11.setName("PDF Generation API");
            api11.setDescription("Convert HTML to PDF with custom headers, footers, watermarks, and digital signatures. Batch processing and template support included.");
            api11.setEndpoint("https://api.pdf.com/v1/convert");
            api11.setMethod("POST");
            api11.setCreatedBy(provider3);
            api11 = apiRepository.save(api11);

            Api api12 = new Api();
            api12.setName("Voice Recognition API");
            api12.setDescription("Convert speech to text in real-time. Supports 50+ languages, accents, custom vocabulary, speaker identification, and punctuation.");
            api12.setEndpoint("https://api.voice.com/v1/recognize");
            api12.setMethod("POST");
            api12.setCreatedBy(provider4);
            api12 = apiRepository.save(api12);

            Api api13 = new Api();
            api13.setName("Sentiment Analysis API");
            api13.setDescription("Analyze text sentiment (positive, negative, neutral) with confidence scores. Emotion detection, topic extraction, and entity recognition included.");
            api13.setEndpoint("https://api.sentiment.com/v1/analyze");
            api13.setMethod("POST");
            api13.setCreatedBy(provider1);
            api13 = apiRepository.save(api13);

            Api api14 = new Api();
            api14.setName("Face Detection API");
            api14.setDescription("Detect and analyze faces in images. Includes age, gender, emotion detection, facial landmark identification, and face comparison.");
            api14.setEndpoint("https://api.faces.com/v1/detect");
            api14.setMethod("POST");
            api14.setCreatedBy(provider2);
            api14 = apiRepository.save(api14);

            Api api15 = new Api();
            api15.setName("Barcode Scanner API");
            api15.setDescription("Read and decode barcodes and QR codes from images. Supports all major formats including UPC, EAN, Code128, Data Matrix, and Aztec.");
            api15.setEndpoint("https://api.barcode.com/v1/scan");
            api15.setMethod("POST");
            api15.setCreatedBy(provider3);
            api15 = apiRepository.save(api15);

            Api api16 = new Api();
            api16.setName("Video Streaming API");
            api16.setDescription("Stream and transcode videos in multiple formats. Adaptive bitrate streaming, DRM protection, and CDN integration for global delivery.");
            api16.setEndpoint("https://api.video.com/v1/stream");
            api16.setMethod("POST");
            api16.setCreatedBy(provider4);
            api16 = apiRepository.save(api16);

            Api api17 = new Api();
            api17.setName("News Aggregator API");
            api17.setDescription("Access news articles from 50,000+ sources worldwide. Filter by category, country, language, and sentiment with real-time updates.");
            api17.setEndpoint("https://api.news.com/v2/articles");
            api17.setMethod("GET");
            api17.setCreatedBy(provider1);
            api17 = apiRepository.save(api17);

            Api api18 = new Api();
            api18.setName("Social Media Analytics API");
            api18.setDescription("Track social media metrics across platforms. Engagement rates, follower growth, sentiment analysis, and competitor benchmarking.");
            api18.setEndpoint("https://api.social.com/v1/analytics");
            api18.setMethod("GET");
            api18.setCreatedBy(provider2);
            api18 = apiRepository.save(api18);

            Api api19 = new Api();
            api19.setName("URL Shortener API");
            api19.setDescription("Create short URLs with custom aliases, QR codes, and detailed analytics. Track clicks, locations, devices, and referrers in real-time.");
            api19.setEndpoint("https://api.shorturl.com/v1/shorten");
            api19.setMethod("POST");
            api19.setCreatedBy(provider3);
            api19 = apiRepository.save(api19);

            Api api20 = new Api();
            api20.setName("IP Geolocation API");
            api20.setDescription("Get location data from IP addresses. Includes country, city, timezone, ISP, connection type, and security threat detection.");
            api20.setEndpoint("https://api.ipgeo.com/v1/lookup");
            api20.setMethod("GET");
            api20.setCreatedBy(provider4);
            api20 = apiRepository.save(api20);

            Api api21 = new Api();
            api21.setName("Cryptocurrency API");
            api21.setDescription("Real-time cryptocurrency prices for 5000+ coins. Market cap, volume, historical data, and portfolio tracking with exchange integration.");
            api21.setEndpoint("https://api.crypto.com/v1/prices");
            api21.setMethod("GET");
            api21.setCreatedBy(provider1);
            api21 = apiRepository.save(api21);

            Api api22 = new Api();
            api22.setName("Flight Data API");
            api22.setDescription("Access real-time flight information, schedules, and prices. Track flights, get airport data, and search for best deals across airlines.");
            api22.setEndpoint("https://api.flights.com/v1/search");
            api22.setMethod("GET");
            api22.setCreatedBy(provider2);
            api22 = apiRepository.save(api22);

            Api api23 = new Api();
            api23.setName("Recipe API");
            api23.setDescription("Search 2 million+ recipes with nutritional information. Filter by diet, cuisine, ingredients, and cooking time with step-by-step instructions.");
            api23.setEndpoint("https://api.recipes.com/v1/search");
            api23.setMethod("GET");
            api23.setCreatedBy(provider3);
            api23 = apiRepository.save(api23);

            Api api24 = new Api();
            api24.setName("Music Recognition API");
            api24.setDescription("Identify songs from audio samples. Get track info, lyrics, artist details, and similar song recommendations with streaming links.");
            api24.setEndpoint("https://api.music.com/v1/identify");
            api24.setMethod("POST");
            api24.setCreatedBy(provider4);
            api24 = apiRepository.save(api24);

            Api api25 = new Api();
            api25.setName("Spam Detection API");
            api25.setDescription("Detect spam in emails, comments, and messages. AI-powered filtering with customizable rules, blacklists, and real-time threat intelligence.");
            api25.setEndpoint("https://api.spam.com/v1/check");
            api25.setMethod("POST");
            api25.setCreatedBy(provider1);
            api25 = apiRepository.save(api25);

            Api api26 = new Api();
            api26.setName("Document Parser API");
            api26.setDescription("Extract text and data from PDFs, images, and documents. OCR support, table extraction, and structured data output in JSON/XML.");
            api26.setEndpoint("https://api.parser.com/v1/extract");
            api26.setMethod("POST");
            api26.setCreatedBy(provider2);
            api26 = apiRepository.save(api26);

            Api api27 = new Api();
            api27.setName("Calendar API");
            api27.setDescription("Manage events and schedules across multiple calendars. Sync with Google, Outlook, iCal with reminders, recurring events, and timezone support.");
            api27.setEndpoint("https://api.calendar.com/v1/events");
            api27.setMethod("POST");
            api27.setCreatedBy(provider3);
            api27 = apiRepository.save(api27);

            Api api28 = new Api();
            api28.setName("Webhook Manager API");
            api28.setDescription("Create and manage webhooks with retry logic, payload transformation, and delivery tracking. Monitor webhook health and debug failures.");
            api28.setEndpoint("https://api.webhooks.com/v1/create");
            api28.setMethod("POST");
            api28.setCreatedBy(provider4);
            api28 = apiRepository.save(api28);

            Api api29 = new Api();
            api29.setName("Machine Learning API");
            api29.setDescription("Pre-trained ML models for classification, prediction, and clustering. Image recognition, text analysis, and anomaly detection without training.");
            api29.setEndpoint("https://api.ml.com/v1/predict");
            api29.setMethod("POST");
            api29.setCreatedBy(provider1);
            api29 = apiRepository.save(api29);

            Api api30 = new Api();
            api30.setName("Database Backup API");
            api30.setDescription("Automated database backups for MySQL, PostgreSQL, MongoDB. Scheduled backups, encryption, compression, and cloud storage integration.");
            api30.setEndpoint("https://api.backup.com/v1/create");
            api30.setMethod("POST");
            api30.setCreatedBy(provider2);
            apiRepository.save(api30);

            // Create sample subscriptions for consumer
            Subscription sub1 = new Subscription();
            sub1.setUser(consumer1);
            sub1.setApi(api1);
            subscriptionRepository.save(sub1);

            Subscription sub2 = new Subscription();
            sub2.setUser(consumer1);
            sub2.setApi(api3);
            subscriptionRepository.save(sub2);

            Subscription sub3 = new Subscription();
            sub3.setUser(consumer1);
            sub3.setApi(api7);
            subscriptionRepository.save(sub3);

            Subscription sub4 = new Subscription();
            sub4.setUser(consumer1);
            sub4.setApi(api9);
            subscriptionRepository.save(sub4);

            System.out.println("✅ Sample data loaded successfully!");
            System.out.println("📊 Created 5 users, 30 APIs, and 4 subscriptions");
            System.out.println("🔑 Test credentials: john@provider.com / password123");
        };
    }
}
