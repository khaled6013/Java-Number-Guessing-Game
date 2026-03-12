function sendGuess() {
    let guess = document.getElementById("guess").value;
    if (guess === "") {
        document.getElementById("result").innerText = "⚠️ Please enter a number!";
        return;
    }
    fetch("http://localhost:8000/guess?number=" + guess)

    .then(response => response.text())

    .then(data => {
        document.getElementById("result").innerText = data;
        if (data.includes("High")) {
            document.getElementById("result").style.color = "red";
        }
        else if (data.includes("Low")) {
            document.getElementById("result").style.color = "orange";
        }
        else if (data.includes("Correct")) {
            document.getElementById("result").style.color = "lightgreen";
        }

    })

    .catch(error => {
        document.getElementById("result").innerText = "❌ Server error!";
        console.error(error);
    });

}