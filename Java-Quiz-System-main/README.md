# 📘 Java Console Quiz System

This is a **Java-based console quiz application** with role-based login.  
Users can log in as either an **Admin** or a **Student**:

- **Admins** can add multiple-choice questions (MCQs) to a question bank stored in `quiz.json`.
- **Students** can take quizzes with 10 randomly selected questions from the bank and receive feedback.

---
## Video presentation for Admin
https://drive.google.com/file/d/1-k4kQxFbiSKdUd14Alq8b9qkcqqNIFbc/view?usp=sharing
## Video presentation for Students
https://drive.google.com/file/d/1XQm9LfnkTAFOv4Bt5vhhiAtZ4mJ_YbLP/view?usp=drive_link

---
## 🚀 Features

- ✅ Role-based login (`admin` or `student`)
- 📝 Admins can add questions with 4 options and correct answers
- 🧠 Students can take quizzes with:
  - 10 random questions (no repetition)
  - Correctness feedback for each answer
  - Score and performance summary
- 🗂️ JSON-based data storage (`users.json`, `quiz.json`)
- 🔁 Option to retake the quiz

---

## 💻 Technologies Used

| Tool             | Purpose                                |
|------------------|----------------------------------------|
| **Java (JDK 17)**| Core programming language              |
| **IntelliJ IDEA**| Recommended IDE for development        |
| **JSON.simple**  | JSON parsing and writing library       |

---

## 📁 Project Structure
project/

├── src/

│ └── main/

│ ├── java/

│ │ └── project/

│ │ └── quiz_System.java # Main program file

│ └── resources/

│ ├── users.json # Login credentials and roles

│ └── quiz.json # Question bank (MCQs)

└── README.md # Project instructions

---

## 📦 Dependencies

Make sure to include the [`json-simple`](https://code.google.com/archive/p/json-simple/) library in your project.

**If you're using Gradle**, add this to your `build.gradle`:

dependencies {
    implementation 'com.googlecode.json-simple:json-simple:1.1.1'
}

---
## 🔧 Setup Instructions

1.Open IntelliJ IDEA

2.Create or open your Java project

3.Add the provided quiz_System.java file under src/main/java/project

4.Create a resources folder under src/main/resources/

5.Add a users.json file with initial users (see below)

6.The quiz.json file will be created/updated by the admin during runtime

7.Add the json-simple library dependency

8.Run the program

---

