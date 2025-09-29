const form = document.getElementById("loginForm");
const message = document.getElementById("message");

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const data = {
    name: document.getElementById("name").value,
    password: document.getElementById("password").value,
  };

  fetch("/user/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then((res) => {
      if (res.status === 200) return res.json();
      else
        return res.text().then((t) => {
          throw new Error(t);
        });
    })
    .then((user) => {
      localStorage.setItem("loggedInUser", JSON.stringify(user));

      message.style.color = "green";
      message.innerText = `Welcome, ${user.name}! Redirecting...`;

      setTimeout(() => {
        if (user.role === "ADMIN") {
          window.location.href = "dashboard.html"; // admin dashboard
        } else {
          window.location.href = "user-dashboard.html"; // normal user page
        }
      }, 1000);
    })
    .catch((err) => {
      message.style.color = "red";
      message.innerText = err.message;
    });
});
