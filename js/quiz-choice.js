// Load subjects for dropdown
fetch("/subject/all")
  .then((res) => res.json())
  .then((subjects) => {
    const select = document.getElementById("subject-select");
    subjects.forEach((sub) => {
      const option = document.createElement("option");
      option.value = sub.id;
      option.text = sub.name;
      select.appendChild(option);
    });

    // Preselect subject if coming from subject card
    const selectedSubject = localStorage.getItem("selectedSubject");
    if (selectedSubject) select.value = selectedSubject;
  });

document.getElementById("start-quiz").addEventListener("click", () => {
  const difficulty = document.getElementById("difficulty").value;
  const subjectId = document.getElementById("subject-select").value;

  if (!difficulty || !subjectId) {
    alert("Please select both difficulty and subject!");
    return;
  }

  localStorage.setItem("quizDifficulty", difficulty);
  localStorage.setItem("quizSubjectId", subjectId);

  // Redirect to quiz page (you can create quiz.html to fetch questions)
  window.location.href = "quiz.html";
});
