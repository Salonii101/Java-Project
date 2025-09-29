const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  alert("Access denied. Admins only.");
  window.location.href = "login.html";
}

document.getElementById("welcomeMsg").innerText = `Welcome, ${user.name}`;
document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

const usersTableBody = document.querySelector("#usersTable tbody");
const modal = document.getElementById("userModal");
const userForm = document.getElementById("userForm");
const addUserBtn = document.getElementById("addUserBtn");
const modalTitle = document.getElementById("modalTitle");
const closeBtn = document.querySelector(".closeBtn");

let editingUserId = null;

// Fetch all users
function loadUsers() {
  fetch("/user/all")
    .then((res) => res.json())
    .then((data) => {
      usersTableBody.innerHTML = "";
      data.forEach((u) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.role}</td>
                <td>
                    <button class="edit-btn" data-id="${u.id}">Edit</button>
                    <button class="delete-btn" data-id="${u.id}">Delete</button>
                </td>
            `;
        usersTableBody.appendChild(tr);
      });
    });
}
loadUsers();

// Open Add User Modal
addUserBtn.addEventListener("click", () => {
  modal.style.display = "block";
  modalTitle.innerText = "Add User";
  userForm.reset();
  editingUserId = null;
});

// Close Modal
closeBtn.addEventListener("click", () => (modal.style.display = "none"));
window.addEventListener("click", (e) => {
  if (e.target == modal) modal.style.display = "none";
});

// Save (Add/Edit) User
userForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const data = {
    name: document.getElementById("userName").value,
    password: document.getElementById("userPassword").value,
    role: document.getElementById("userRole").value,
  };

  if (editingUserId) {
    // Edit
    fetch(`/user/${editingUserId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadUsers();
      modal.style.display = "none";
    });
  } else {
    // Add
    fetch("/user/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    }).then((res) => {
      if (res.ok) loadUsers();
      modal.style.display = "none";
    });
  }
});

// Edit/Delete Buttons
usersTableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("edit-btn")) {
    const id = e.target.dataset.id;
    fetch(`/user/${id}`)
      .then((res) => res.json())
      .then((u) => {
        editingUserId = u.id;
        modalTitle.innerText = "Edit User";
        document.getElementById("userName").value = u.name;
        document.getElementById("userPassword").value = u.password;
        document.getElementById("userRole").value = u.role;
        modal.style.display = "block";
      });
  }
  if (e.target.classList.contains("delete-btn")) {
    const id = e.target.dataset.id;
    if (confirm("Are you sure to delete this user?")) {
      fetch(`/user/${id}`, { method: "DELETE" }).then((res) => {
        if (res.ok) loadUsers();
      });
    }
  }
});
