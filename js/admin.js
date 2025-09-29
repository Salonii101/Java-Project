const content = document.getElementById("content");

// Check if logged in user is admin
const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  alert("Access denied! Only admins can access this page.");
  window.location.href = "login.html";
}

// Logout
document.getElementById("logout").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

// Navigation buttons
document.getElementById("nav-users").addEventListener("click", loadUsers);
document.getElementById("nav-subjects").addEventListener("click", loadSubjects);
document.getElementById("nav-quizzes").addEventListener("click", loadQuizzes);
document
  .getElementById("nav-questions")
  .addEventListener("click", loadQuestions);
document.getElementById("nav-results").addEventListener("click", loadResults);

// ================== USERS ==================
function loadUsers() {
  fetch("/user/all")
    .then((res) => res.json())
    .then((data) => {
      content.innerHTML = `
        <h3>Users</h3>
        <button id="add-user">Add User</button>
        <table>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Role</th><th>Actions</th></tr>
          </thead>
          <tbody id="userTable"></tbody>
        </table>
      `;
      const table = document.getElementById("userTable");
      data.forEach((u) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${u.id}</td>
          <td>${u.name}</td>
          <td>${u.role}</td>
          <td>
            <button class="edit-user" data-id="${u.id}">Edit</button>
            <button class="delete-user" data-id="${u.id}">Delete</button>
          </td>
        `;
        table.appendChild(tr);
      });

      // Add user button
      document.getElementById("add-user").addEventListener("click", () => {
        const name = prompt("Enter name:");
        const password = prompt("Enter password:");
        const role = prompt("Enter role (USER/ADMIN):", "USER");
        if (name && password && role) {
          fetch("/user/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, password, role }),
          }).then(() => loadUsers());
        }
      });

      // Delete user
      table.addEventListener("click", (e) => {
        if (e.target.classList.contains("delete-user")) {
          const id = e.target.dataset.id;
          if (confirm("Delete user?")) {
            fetch(`/user/${id}`, { method: "DELETE" }).then(() => loadUsers());
          }
        }
      });

      // Edit user
      table.addEventListener("click", (e) => {
        if (e.target.classList.contains("edit-user")) {
          const id = e.target.dataset.id;
          const name = prompt("New name:");
          const role = prompt("New role (USER/ADMIN):");
          if (name && role) {
            fetch(`/user/${id}`, {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({ name, role }),
            }).then(() => loadUsers());
          }
        }
      });
    });
}

// ================== SUBJECTS ==================
function loadSubjects() {
  fetch("/subject/all")
    .then((res) => res.json())
    .then((data) => {
      content.innerHTML = `
        <h3>Subjects</h3>
        <button id="add-subject">Add Subject</button>
        <table>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Actions</th></tr>
          </thead>
          <tbody id="subjectTable"></tbody>
        </table>
      `;
      const table = document.getElementById("subjectTable");
      data.forEach((s) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${s.id}</td>
          <td>${s.name}</td>
          <td>
            <button class="edit-subject" data-id="${s.id}">Edit</button>
            <button class="delete-subject" data-id="${s.id}">Delete</button>
          </td>
        `;
        table.appendChild(tr);
      });

      document.getElementById("add-subject").addEventListener("click", () => {
        const name = prompt("Subject name:");
        if (name) {
          fetch("/subject/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name }),
          }).then(() => loadSubjects());
        }
      });

      table.addEventListener("click", (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains("delete-subject")) {
          if (confirm("Delete subject?"))
            fetch(`/subject/${id}`, { method: "DELETE" }).then(() =>
              loadSubjects()
            );
        } else if (e.target.classList.contains("edit-subject")) {
          const name = prompt("New name:");
          if (name)
            fetch(`/subject/${id}`, {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({ name }),
            }).then(() => loadSubjects());
        }
      });
    });
}

// ================== QUIZZES ==================
function loadQuizzes() {
  fetch("/quiz/all")
    .then((res) => res.json())
    .then((data) => {
      content.innerHTML = `
        <h3>Quizzes</h3>
        <button id="add-quiz">Add Quiz</button>
        <table>
          <thead>
            <tr><th>ID</th><th>Title</th><th>Subject ID</th><th>Actions</th></tr>
          </thead>
          <tbody id="quizTable"></tbody>
        </table>
      `;
      const table = document.getElementById("quizTable");
      data.forEach((q) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${q.id}</td>
          <td>${q.title}</td>
          <td>${q.subject?.id || ""}</td>
          <td>
            <button class="edit-quiz" data-id="${q.id}">Edit</button>
            <button class="delete-quiz" data-id="${q.id}">Delete</button>
          </td>
        `;
        table.appendChild(tr);
      });

      document.getElementById("add-quiz").addEventListener("click", () => {
        const title = prompt("Quiz title:");
        const subjectId = prompt("Subject ID:");
        if (title && subjectId) {
          fetch("/quiz/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              title,
              subject: { id: parseInt(subjectId) },
            }),
          }).then(() => loadQuizzes());
        }
      });

      table.addEventListener("click", (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains("delete-quiz")) {
          if (confirm("Delete quiz?"))
            fetch(`/quiz/${id}`, { method: "DELETE" }).then(() =>
              loadQuizzes()
            );
        } else if (e.target.classList.contains("edit-quiz")) {
          const title = prompt("New title:");
          const subjectId = prompt("New Subject ID:");
          if (title && subjectId)
            fetch(`/quiz/${id}`, {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                title,
                subject: { id: parseInt(subjectId) },
              }),
            }).then(() => loadQuizzes());
        }
      });
    });
}

// ================== QUESTIONS ==================
function loadQuestions() {
  fetch("/question/all")
    .then((res) => res.json())
    .then((data) => {
      content.innerHTML = `
        <h3>Questions</h3>
        <button id="add-question">Add Question</button>
        <table>
          <thead>
            <tr><th>ID</th><th>Text</th><th>Quiz ID</th><th>Subject ID</th><th>Actions</th></tr>
          </thead>
          <tbody id="questionTable"></tbody>
        </table>
      `;
      const table = document.getElementById("questionTable");
      data.forEach((q) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${q.id}</td>
          <td>${q.text}</td>
          <td>${q.quiz?.id || ""}</td>
          <td>${q.subject?.id || ""}</td>
          <td>
            <button class="edit-question" data-id="${q.id}">Edit</button>
            <button class="delete-question" data-id="${q.id}">Delete</button>
          </td>
        `;
        table.appendChild(tr);
      });

      document.getElementById("add-question").addEventListener("click", () => {
        const text = prompt("Question text:");
        const quizId = prompt("Quiz ID:");
        const subjectId = prompt("Subject ID:");
        if (text && quizId && subjectId) {
          fetch("/question/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              text,
              quiz: { id: parseInt(quizId) },
              subject: { id: parseInt(subjectId) },
            }),
          }).then(() => loadQuestions());
        }
      });

      table.addEventListener("click", (e) => {
        const id = e.target.dataset.id;
        if (e.target.classList.contains("delete-question")) {
          if (confirm("Delete question?"))
            fetch(`/question/${id}`, { method: "DELETE" }).then(() =>
              loadQuestions()
            );
        } else if (e.target.classList.contains("edit-question")) {
          const text = prompt("New text:");
          const quizId = prompt("New Quiz ID:");
          const subjectId = prompt("New Subject ID:");
          if (text && quizId && subjectId)
            fetch(`/question/${id}`, {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                text,
                quiz: { id: parseInt(quizId) },
                subject: { id: parseInt(subjectId) },
              }),
            }).then(() => loadQuestions());
        }
      });
    });
}

// ================== RESULTS ==================
function loadResults() {
  fetch("/result/all")
    .then((res) => res.json())
    .then((data) => {
      content.innerHTML = `
        <h3>Results</h3>
        <table>
          <thead>
            <tr><th>ID</th><th>User ID</th><th>Quiz ID</th><th>Score</th><th>Actions</th></tr>
          </thead>
          <tbody id="resultTable"></tbody>
        </table>
      `;
      const table = document.getElementById("resultTable");
      data.forEach((r) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td>${r.id}</td>
          <td>${r.user?.id || ""}</td>
          <td>${r.quiz?.id || ""}</td>
          <td>${r.score}</td>
          <td>
            <button class="delete-result" data-id="${r.id}">Delete</button>
          </td>
        `;
        table.appendChild(tr);
      });

      table.addEventListener("click", (e) => {
        if (e.target.classList.contains("delete-result")) {
          const id = e.target.dataset.id;
          if (confirm("Delete result?"))
            fetch(`/result/${id}`, { method: "DELETE" }).then(() =>
              loadResults()
            );
        }
      });
    });
}
