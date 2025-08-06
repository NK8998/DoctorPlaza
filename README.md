
# 🏥 DoctorPlaza – Medical Appointment Management System

DoctorPlaza is a full-stack Java-based application designed to manage medical appointments, visits, and patient-doctor interactions. Built with a **JavaFX frontend** and a **Spring Boot backend**, the app provides a modular, maintainable architecture suitable for clinics and hospitals. The backend handles data storage, logic, and service orchestration, while the frontend offers a clean, responsive desktop UI.

---

## 🔧 Tech Stack

### Backend:

* **Java 17**
* **Spring Boot** (REST API, JPA, Validation)
* **PostgreSQL**
* **Gradle** for build automation

### Frontend:

* **JavaFX** (FXML-based UI components)
* **Controllers wired via Spring’s Dependency Injection**

---

## 🧠 Key Features

* 🧾 **Patient Management:** Register, update, and view patient details.
* 🩺 **Doctor & Specialization Handling:** Manage doctors and assign specializations.
* 📅 **Visit Scheduling:** Create, update, and log medical visits.
* 📝 **Medical Records:** Link diagnoses and notes to each visit.
* 🧑‍⚕️ **User Roles:** Base structure built for expanding into role-based access (e.g., admin, doctor, receptionist).
* 🛠️ **Modular Backend:** Separated into DTOs, services, repositories, and controllers for clean maintainability.

---

## 🗃️ Project Structure

```
DoctorPlaza/
├── backend/             # Spring Boot app
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   └── service/
├── frontend/            # JavaFX UI
│   ├── fxml/
│   ├── controller/
│   └── MainApp.java
├── build.gradle
└── settings.gradle
```

---

## ⚙️ Running the App

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/doctorplaza.git
   cd doctorplaza
   ```

2. Set up your PostgreSQL database and update `application.properties`.

3. Run the backend:

   ```bash
   ./gradlew bootRun
   ```

4. Launch the JavaFX frontend from your IDE or via `MainApp.java`.

---

## 🚧 Future Improvements

* 🔐 Implement Spring Security and session handling
* 🌐 Dockerize backend for easier deployment
* 📊 Add data analytics dashboard
* 🧪 Add unit/integration testing

---

## 🤝 Contributions & Feedback

This project was built as a learning platform and is still evolving. Feedback and pull requests are welcome!

---

Let me know if you'd like a **shorter version**, or one that's more recruiter-focused (e.g., resume-linked). I can also help you add badges, diagrams, or a simple GIF recording if you want that extra flair.
