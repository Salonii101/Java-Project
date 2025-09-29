// ===== Get logged-in user =====
const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user) {
  alert("Please login first!");
  window.location.href = "login.html";
}
document.getElementById("userName").innerText = user.name || "User";

// ===== Navbar buttons =====
document
  .querySelector('a[href="subjects.html"]')
  .addEventListener("click", loadSubjects);
document
  .querySelector('a[href="results.html"]')
  .addEventListener("click", loadResults);
document
  .querySelector('a[href="take-quiz.html"]')
  .addEventListener("click", () => {
    window.location.href = "quiz-choice.html";
  });
document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.clear();
  window.location.href = "login.html";
});

// ===== Center Take Quiz button =====
document.getElementById("takeQuizBtn").addEventListener("click", () => {
  window.location.href = "quiz-choice.html";
});

// ===== Load Subjects =====
function loadSubjects() {
  fetch("/subject/all")
    .then((res) => res.json())
    .then((subjects) => {
      const container = document.getElementById("subjectsContainer");
      container.innerHTML = "";
      subjects.forEach((sub) => {
        const card = document.createElement("div");
        card.className = "subject-card";
        card.innerHTML = `
          <h3>${sub.name}</h3>
          <p>${sub.description || ""}</p>
          <button class="take-subject-quiz" data-id="${
            sub.id
          }">Take Quiz</button>
        `;
        container.appendChild(card);
      });

      // Add event listeners for "Take Quiz" buttons
      document.querySelectorAll(".take-subject-quiz").forEach((btn) => {
        btn.addEventListener("click", (e) => {
          const subjectId = e.target.dataset.id;
          localStorage.setItem("selectedSubject", subjectId);
          window.location.href = "quiz-choice.html";
        });
      });
    })
    .catch((err) => console.error("Error loading subjects:", err));
}

// ===== Load User Results =====
function loadResults() {
  fetch(`/result/user/${user.id}`)
    .then((res) => res.json())
    .then((results) => {
      const container = document.getElementById("subjectsContainer");
      container.innerHTML = "<h3>Your Results</h3>";
      results.forEach((r) => {
        const div = document.createElement("div");
        div.className = "subject-card";
        div.innerHTML = `
          <p>Quiz: ${r.quiz.name}</p>
          <p>Score: ${r.score}</p>
        `;
        container.appendChild(div);
      });
    })
    .catch((err) => console.error("Error loading results:", err));
}
