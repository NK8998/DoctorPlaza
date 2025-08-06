# 🏥 DoctorPlaza – Medical Appointment Management System

**DoctorPlaza** is a full-stack Java desktop application for managing doctor-patient relationships, visits, and medical records. It combines a clean **JavaFX frontend** with a modular **Spring Boot backend**, connected via Gradle, making it a great showcase of full-stack Java proficiency.

---

## 🧰 Tech Stack

### 🔙 Backend (Spring Boot)

* **Java 21**
* **Spring Boot** (Web, JPA, Validation)
* **PostgreSQL** for persistence
* **Gradle** for build automation

### 🔜 Frontend (JavaFX)

* **JavaFX (FXML)** for UI rendering
* **SceneManager** for handling view transitions
* **UserSession** for managing session-like state
* **Dependency injection** with Spring

---

## ✨ Features

* 👥 **User Registration**: Patients, Doctors, and more (structure is extensible)
* 📄 **Medical Records**: Log notes per visit, linked to doctors and patients
* 📅 **Visit Management**: Schedule and update patient-doctor visits
* 🔍 **Role-ready System**: Set up for future user-based access control
* 🔄 **DTO-based Data Flow**: Separation of concerns between layers
* 🧱 **Clear Backend Architecture**: Controllers, Services, Repos, and DTOs

---

## 📁 Project Structure

### Frontend (`/frontend`)

```
Enums/             # User roles, visit types, etc.
controllers/       # JavaFX controllers for each FXML view
dto/               # Frontend data transfer objects
service/           # Handles API communication and business logic
tasks/             # Background threads for async tasks
utils/             # Reusable utility functions
SceneManager.java  # Manages view transitions
UserSession.java   # Stores current user session
```

### Backend (`/backend`)

```
Enums/                  # Shared enums like UserRole
controller/             # REST controllers
dto/                    # Request/response objects
models/                 # JPA entities
repository/             # Spring Data Repositories
service/                # Business logic layer
SpringBootLauncher.java # Entry point for backend
```

---

## 🚀 Getting Started

1. **Clone the repo**

   ```bash
   git clone https://github.com/yourusername/doctorplaza.git
   cd doctorplaza
   ```

2. **Set up PostgreSQL**
   Create a DB and update the `application.properties` in the backend with your credentials.

3. **Run the backend**

   ```bash
   ./gradlew bootRun
   ```

4. **Run the frontend**
   Launch `MainApp` or your JavaFX entry point via your IDE.

---

## 🛠 Status

✅ Fully working core features (CRUD, scheduling, record logging)
🧪 Backend and frontend communication via DTOs
🔒 Role-based access to be added (Spring Security planned)
📦 Modular and maintainable codebase

---

## 🗺️ Future Improvements

* 🛡️ Add session/authentication handling (e.g., Spring Security)
* 🧪 Unit/integration tests
* 🌐 REST documentation (e.g., Swagger/OpenAPI)
* 🐳 Dockerized backend
* 📈 Admin analytics dashboard


## 🧠 Built With Learning in Mind

DoctorPlaza was built to practice real-world backend structuring, JavaFX-Spring integration, and DTO-driven workflows. If you’re a developer curious about building maintainable Java apps—or a recruiter evaluating backend depth—this project aims to show both.



You crushed this stack. Let’s show it off right.
