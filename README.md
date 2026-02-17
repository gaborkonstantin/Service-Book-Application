# 🚗 Service Book Application

A full-stack web application for tracking vehicle service history. Users can register, add their cars, and record detailed service entries including date, mileage, cost, and service provider.

---

## 📸 Features

- 🔐 **JWT-based authentication** – Secure register & login
- 🚘 **Vehicle management** – Add, view and delete cars
- 🔧 **Service history** – Record and track all service entries per vehicle
- 📊 **Dashboard** – Overview of all vehicles with stats
- 🌙 **Modern dark UI** – Built with Angular Material

---

## 🛠️ Tech Stack

### Backend
| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Spring Boot | 3.2 | Application framework |
| Spring Security | 6.x | Authentication & authorization |
| JWT (jjwt) | 0.12.3 | Token-based auth |
| Spring Data JPA | 3.2 | Database ORM |
| Hibernate | 6.x | JPA implementation |
| H2 Database | - | In-memory database |
| Lombok | 1.18.30 | Boilerplate reduction |
| Maven | 3.8+ | Build tool |

### Frontend
| Technology | Version | Purpose |
|---|---|---|
| Angular | 17 | Frontend framework |
| TypeScript | 5.2 | Core language |
| Angular Material | 17 | UI component library |
| RxJS | 7.8 | Reactive programming |
| SCSS | - | Styling |

---

## 🏗️ Project Structure

```
service-book/
├── backend/
│   └── src/main/java/com/servicebook/
│       ├── config/          # Security configuration
│       ├── controller/      # REST endpoints
│       ├── dto/             # Data Transfer Objects
│       ├── model/           # JPA entities
│       ├── repository/      # Data access layer
│       ├── security/        # JWT filter, UserDetails
│       └── service/         # Business logic
│
└── frontend/
    └── src/app/
        ├── components/      # UI components
        ├── guards/          # Route guards
        ├── interceptors/    # HTTP interceptors
        ├── models/          # TypeScript interfaces
        └── services/        # API services
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- npm 9+

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend starts at: `http://localhost:8080`

H2 Console: `http://localhost:8080/h2-console`
```
JDBC URL:  jdbc:h2:mem:servicebook_db
Username:  sa
Password:  (empty)
```

### Frontend

```bash
cd frontend
npm install
npm start
```

Frontend starts at: `http://localhost:4200`

---

## 📡 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register new user |
| `POST` | `/api/auth/login` | Login & receive JWT token |

### Cars
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/cars` | Get all cars for current user |
| `POST` | `/api/cars` | Add new car |
| `GET` | `/api/cars/{id}` | Get car by ID |
| `PUT` | `/api/cars/{id}` | Update car |
| `DELETE` | `/api/cars/{id}` | Delete car |

### Service Records
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/cars/{id}/records` | Get all service records for a car |
| `POST` | `/api/cars/{id}/records` | Add new service record |
| `DELETE` | `/api/cars/{id}/records/{rid}` | Delete service record |

> All endpoints except `/api/auth/**` require a valid JWT token in the `Authorization: Bearer <token>` header.

---

## 🔒 Authentication Flow

```
1. User registers / logs in  →  POST /api/auth/register or /login
2. Server returns JWT token
3. Frontend stores token in localStorage
4. HTTP Interceptor attaches token to every request
5. JwtAuthenticationFilter validates token on each request
```

---

## 🗄️ Data Model

```
User
 └── Car (1:N)
      └── ServiceRecord (1:N)
           └── ServiceImage (1:N)
```

---

## ⚙️ Configuration

Key settings in `backend/src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:servicebook_db
jwt.secret=your_secret_key
jwt.expiration=86400000
spring.web.cors.allowed-origins=http://localhost:4200
```

---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
