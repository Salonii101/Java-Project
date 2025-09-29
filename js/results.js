const user = JSON.parse(localStorage.getItem("loggedInUser"));
if (!user || user.role !== "ADMIN") {
  window.location.href = "login.html";
}

document.getElementById("logoutBtn").addEventListener("click", () => {
  localStorage.removeItem("loggedInUser");
  window.location.href = "login.html";
});

const tableBody = document.querySelector("#resultsTable tbody");

function loadResults() {
  fetch("/result/all")
    .then((res) => res.json())
    .then((data) => {
      tableBody.innerHTML = "";
      data.forEach((r) => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${r.id}</td><td>${r.user?.id || ""}</td><td>${
          r.quiz?.id || ""
        }</td><td>${r.score}</td>
            <td>
                <button class="delete-btn" data-id="${r.id}">Delete</button>
            </td>`;
        tableBody.appendChild(tr);
      });
    });
}
loadResults();

tableBody.addEventListener("click", (e) => {
  if (e.target.classList.contains("delete-btn")) {
    const id = e.target.dataset.id;
    if (confirm("Delete result?"))
      fetch(`/result/${id}`, { method: "DELETE" }).then((res) => {
        if (res.ok) loadResults();
      });
  }
});
