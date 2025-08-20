📘 Java Quiz Application

A simple console-based Quiz Application built in Java with features like user registration, login, timed quizzes, scoring, accuracy calculation, and history tracking.

🚀 Features

🔑 User Authentication

Register new users with password hashing (SHA-256)
Login system with secure validation

📝 Quiz System

Subjects available: English, Maths, Computer
Questions loaded dynamically from .txt files in Question text files/
Timer support for each question
Displays score and correct answers at the end

📂 File Handling

User data stored in User/users.txt
Quiz history stored in User/<UserId>_history.txt
Questions stored in Question text files/

📊 Performance Tracking

Calculates accuracy and time taken for each quiz
Stores quiz history for each user

<img width="651" height="582" alt="Screenshot 2025-08-21 025924" src="https://github.com/user-attachments/assets/c86ae2d1-910f-456e-afee-882ec5ff203a" />


⚙️ Setup & Run

1. Clone the repository
   
git clone https://github.com/your-username/Java-QuizApp.git

cd Java-Project

2. Compile the project
   
javac -d "class files" *.java

3. Run the application
   
java -cp "class files" QuizApp


