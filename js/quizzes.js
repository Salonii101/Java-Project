const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  window.location.href = "login.html";
}

document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

const tableBody = document.querySelector("#quizzesTable tbody");
const modal = document.getElementById("quizModal");
const form = document.getElementById("quizForm");
const addBtn = document.getElementById("addQuizBtn");
const closeBtn = document.querySelector(".closeBtn");
let editingId = null;

function loadQuizzes() {
  fetch("/quiz/all")
    .then((res) => res.json())
    .then((data) => {
      tableBody.innerHTML = "";
      data.forEach((q) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${q.id}</td><td>${q.title}</td><td>${
          q.subject?.id || ""
        }</td>
            <td>
                <button class="edit-btn" data-id="${q.id}">Edit</button>
                <button class="delete-btn" data-id="${q.id}">Delete</button>
            </td>`;
        tableBody.appendChild(tr);
      });
    });
}
loadQuizzes();

addBtn.addEventListener("click", () => {
  modal.style.display = "block";
  form.reset();
  editingId = null;
});

closeBtn.addEventListener("click", () => (modal.style.display = "none"));
window.addEventListener("click", (e) => {
  if (e.target == modal) modal.style.display = "none";
});

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const data = {
    title: document.getElementById("quizTitle").value,
    subject: { id: Number(document.getElementById("quizSubjectId").value) },
  };
  if (editingId) {
    fetch(`/quiz/${editingId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadQuizzes();
      modal.style.display = "none";
    });
  } else {
    fetch("/quiz/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadQuizzes();
      modal.style.display = "none";
    });
  }
});

tableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("edit-btn")) {
    const id = e.target.dataset.id;
    fetch(`/quiz/${id}`)
      .then((res) => res.json())
      .then((q) => {
        editingId = id;
        document.getElementById("quizTitle").value = q.title;
        document.getElementById("quizSubjectId").value = q.subject?.id || "";
        modal.style.display = "block";
      });
  }
  if (e.target.classList.contains("delete-btn")) {
    const id = e.target.dataset.id;
    if (confirm("Delete quiz?"))
      fetch(`/quiz/${id}`, { method: "DELETE" }).then((res) => {
        if (res.ok) loadQuizzes();
      });
  }
});
