function sendGuess() {

    // input থেকে number নেওয়া
    let guess = document.getElementById("guess").value;

    // যদি user কিছু না লিখে
    if (guess === "") {
        document.getElementById("result").innerText = "⚠️ Please enter a number!";
        return;
    }

    // backend এ request পাঠানো
    fetch("http://localhost:8000/guess?number=" + guess)

    .then(response => response.text())

    .then(data => {

        // result show করা
        document.getElementById("result").innerText = data;

        // bonus UI color change
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