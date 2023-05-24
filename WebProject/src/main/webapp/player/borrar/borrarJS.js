window.addEventListener(
  "DOMContentLoaded",
  () => {
    // Handle click event on the button 'botonEliminarFirst'
    document.querySelector('#botonEliminarFirst').addEventListener('click', () => {
      const nameInput = document.querySelector('#nickname').value;
      const passInput = document.querySelector('#password').value;

      // Check if name and password inputs are empty
      if (nameInput == "" || passInput == "") { 
        alert("Faltan datos por introducir!");
      } else {
        // Show popup confirmation
        showPopup();

        // Handle click event on the button 'eliminarUsuario' inside the popup
        document.querySelector('#eliminarUsuario').addEventListener('click', () => {
          const dataSend = {
            nickname: nameInput,
            password: passInput
          };
          
          // Send a request to validate the user
          fetch('http://localhost:8080/WebProject/validate-player', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dataSend),
          })
            .then(response => {
              if (response.status == 500) {
                window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
              } else {
                return response.text();
              }
            })
            .then(data => {
              if (data == "1") {
                // Send a request to delete the user
                fetch("http://localhost:8080/WebProject/delete-player", {
                  method: 'POST',
                  headers: {
                    "Content-Type": "application/json"
                  },
                  body: JSON.stringify({
                    nickname: nameInput,
                    password: passInput
                  })
                })
                  .then(response => {
                    if (response.status == 500) {
                      window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
                    } else {
                      return response.text();
                    }
                  })
                  .then(data => {
                    if (data == "1") {
                      // User deleted successfully
                      window.alert("Usuario eliminado correctamente!")
                      window.location.replace("http://localhost:8080/WebProject/index.jsp");
                    } else {
                      // User not deleted, error occurred
                      window.alert("Usuario no se ha eliminado, error!")
                      //window.location.replace("http://localhost:8080/WebProject/index.jsp");
                    }
                  });
              } else {
                // User with given data does not exist
                window.alert("El usuario con estos datos no existe!");
                cancelAction();
              }
            });
            confirmAction();
        });
      }
    });

    // Function to show the popup
    function showPopup() {
      var popup = document.getElementById("popup");
      popup.style.display = "flex";
    }

    // Function to hide the popup
    function hidePopup() {
      var popup = document.getElementById("popup");
      popup.style.display = "none";
    }

    // Function to handle confirm action
    function confirmAction() {
      // Handle confirm action here
      hidePopup();
    }

    // Function to handle cancel action
    function cancelAction() {
      // Handle cancel action here
      hidePopup();
    }
  });