window.addEventListener("DOMContentLoaded", () => {
    let segundos = 3;
    const countdown = document.querySelector("#countdown");
    const interval = setInterval( () => {
        countdown.textContent = segundos;
        segundos--;
        if (segundos == -1) {
            clearInterval(interval);
            window.location.replace("http://localhost:8080/WebProject/index.jsp")
        }
    }, 1000);
});