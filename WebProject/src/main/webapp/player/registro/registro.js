window.addEventListener(
  "DOMContentLoaded",
  () => {
    // Event listener for the "DOMContentLoaded" event
    // This ensures that the code inside will run when the page finishes loading

    document.querySelector("#botonRegistro").addEventListener(
      "click",
      (e) => {
        // Event listener for the "botonRegistro" button click event

        const nameInput = document.querySelector("#nickname").value;
        const passwordInput = document.querySelector("#password").value;
        const emailInput = document.querySelector("#email").value;
        
        if (nameInput != "" && passwordInput != "") {
            // Check if the name and password inputs are not empty

            // Check if the password or name contains spaces
            if (passwordInput.indexOf(" ") !== -1 || nameInput.indexOf(" ") !== -1) {
                // Display an alert if the password or name contains spaces
                alert("La contraseña y el nombre no pueden tener espacios");
            } else {
              // Create the user data object
              const userData = {
                nickname: nameInput,
                password: passwordInput,
                email: emailInput
              };
              
              // Send a POST request to register the user
              fetch("http://localhost:8080/WebProject/add-player", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(userData)
              })
                .then((response) => {
                  // Check the response status
                  if (response.status == 500) {
                    // If the response status is 500, redirect to the error page
                    window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
                  } else {
                    return response.text()
                  }
                })
                .then((data) => {
                  // Handle the response data

                  if (data == "1") {
                    // Display an alert if there is already a user with the same name
                    alert("Ya existe un usuario con ese nombre");
                  } else if (data == "2") {
                    // Display an alert if there is already a user with the same email
                    alert("Ya existe un usuario con ese correo");
                  } else if (data == "0") {
                    // Display an alert if the user is successfully registered and redirect to the index page
                    alert("Usuario registrado");
                    window.location.replace("http://localhost:8080/WebProject/index.jsp");
                  } else if (data == "3") {
                    // If the response data is 3, redirect to the error page
                    window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
                  }
                })
                .catch((error) => {
                  // If there is an error, redirect to the error page
                  window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
                });
            }
        } else {
          // Display an alert if the name or password inputs are empty
          alert("No puede haber campos en blanco");
        }
      },
      false
    );
  },
  false
);