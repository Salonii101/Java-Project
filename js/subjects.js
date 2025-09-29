const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  window.location.href = "login.html";
}

document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

const tableBody = document.querySelector("#subjectsTable tbody");
const modal = document.getElementById("subjectModal");
const form = document.getElementById("subjectForm");
const addBtn = document.getElementById("addSubjectBtn");
const closeBtn = document.querySelector(".closeBtn");
let editingId = null;

function loadSubjects() {
  fetch("/subject/all")
    .then((res) => res.json())
    .then((data) => {
      tableBody.innerHTML = "";
      data.forEach((s) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${s.id}</td><td>${s.name}</td>
            <td>
                <button class="edit-btn" data-id="${s.id}">Edit</button>
                <button class="delete-btn" data-id="${s.id}">Delete</button>
            </td>`;
        tableBody.appendChild(tr);
      });
    });
}
loadSubjects();

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
  const data = { name: document.getElementById("subjectName").value };
  if (editingId) {
    fetch(`/subject/${editingId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadSubjects();
      modal.style.display = "none";
    });
  } else {
    fetch("/subject/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadSubjects();
      modal.style.display = "none";
    });
  }
});

tableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("edit-btn")) {
    const id = e.target.dataset.id;
    fetch(`/subject/${id}`)
      .then((res) => res.json())
      .then((s) => {
        editingId = id;
        document.getElementById("subjectName").value = s.name;
        modal.style.display = "block";
      });
  }
  if (e.target.classList.contains("delete-btn")) {
    const id = e.target.dataset.id;
    if (confirm("Delete subject?"))
      fetch(`/subject/${id}`, { method: "DELETE" }).then((res) => {
        if (res.ok) loadSubjects();
      });
  }
});
