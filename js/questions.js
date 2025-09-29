const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  window.location.href = "login.html";
}

document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

const tableBody = document.querySelector("#questionsTable tbody");
const modal = document.getElementById("questionModal");
const form = document.getElementById("questionForm");
const addBtn = document.getElementById("addQuestionBtn");
const closeBtn = document.querySelector(".closeBtn");
let editingId = null;

function loadQuestions() {
  fetch("/question/all")
    .then((res) => res.json())
    .then((data) => {
      tableBody.innerHTML = "";
      data.forEach((q) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${q.id}</td><td>${q.text}</td><td>${
          q.quiz?.id || ""
        }</td><td>${q.subject?.id || ""}</td>
            <td>
                <button class="edit-btn" data-id="${q.id}">Edit</button>
                <button class="delete-btn" data-id="${q.id}">Delete</button>
            </td>`;
        tableBody.appendChild(tr);
      });
    });
}
loadQuestions();

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
    text: document.getElementById("questionText").value,
    quiz: { id: Number(document.getElementById("questionQuizId").value) },
    subject: { id: Number(document.getElementById("questionSubjectId").value) },
  };
  if (editingId) {
    fetch(`/question/${editingId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadQuestions();
      modal.style.display = "none";
    });
  } else {
    fetch("/question/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadQuestions();
      modal.style.display = "none";
    });
  }
});

tableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("edit-btn")) {
    const id = e.target.dataset.id;
    fetch(`/question/${id}`)
      .then((res) => res.json())
      .then((q) => {
        editingId = id;
        document.getElementById("questionText").value = q.text;
        document.getElementById("questionQuizId").value = q.quiz?.id || "";
        document.getElementById("questionSubjectId").value =
          q.subject?.id || "";
        modal.style.display = "block";
      });
  }
  if (e.target.classList.contains("delete-btn")) {
    const id = e.target.dataset.id;
    if (confirm("Delete question?"))
      fetch(`/question/${id}`, { method: "DELETE" }).then((res) => {
        if (res.ok) loadQuestions();
      });
  }
});
