window.addEventListener(
  "DOMContentLoaded",
  () => {
    // Event listener for the "DOMContentLoaded" event
    // This ensures that the code inside will run when the page finishes loading
    
    // Hide the "juegos" element
    document.querySelector("#juegos").style.display = "none";
    
    // Event listener for the "botonBusqueda" button click event
    document.querySelector("#botonBusqueda").addEventListener("click", (e) => {
      if (document.querySelector("#nickname").value != "") {
        // If the nickname input is not empty, call the fetchData function
        fetchData();
      } else if (document.querySelector("#nickname").value == "No registrado") {
        // If the nickname input is "No registrado", display an alert
        alert("No se puede buscar ese nombre");
      } else {
        // If the nickname input is empty, display an alert
        alert("Debe introducir un nombre para buscar");
      }
    });

    // Event listener for the '.btn-secondary' button click event
    document.querySelector('.btn-secondary').addEventListener('click', () => {
      // Redirect to the index page
      window.location.replace("http://localhost:8080/WebProject/index.jsp")
    });
  },
  false
);

function fetchData() {
  // Function to fetch data from the server
    
  const nombre = document.querySelector("#nickname").value;
  const url = "http://localhost:8080/WebProject/busqueda-siete?nombre=" + nombre;
  
  // Send a GET request to fetch data from the server
  fetch(url, {
    method: "GET"
  })
    .then((response) => {
      // Check the response status
      if (response.status == 500) {
        // If the response status is 500, redirect to the error page
        window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
      } else {
        // Check the response content type
        if (response.headers.get("Content-Type").includes("application/json")) {
          return response.json();
        } else {
          return response.text();
        }
      }
    })
    .then((data) => {
      // Handle the response data
      
      if (data.length == 0) {
        // If no games are found, display an alert and hide the "juegos" element
        alert("No se han encontrado partidas");
        document.querySelector("#juegos").style.display = "none";
      } else {
        // If games are found, generate the table
        generarTabla(data);
      }
    })
    .catch((error) => {
      // Handle any errors
      console.error(error);
    });
}

function generarTabla(data) {
  // Function to generate the table based on the data
  
  const ranking = document.querySelector("#ranking");
  const wins = data.winCount;
  
  // Update the ranking information
  ranking.querySelector("#total").textContent = "Total de partidas: " + data.gameData.length;
  ranking.querySelector("#wins").textContent = "Partidas ganadas: " + wins;
  ranking.querySelector("#loss").textContent = "Partidas perdidas: " + (data.gameData.length - wins);
  
  // Clear the "campos" element
  document.querySelector("#campos").innerHTML = '';
  
  // Show the "juegos" element
  document.querySelector("#juegos").style.display = "inline-block";
  
  // Loop through the game data and generate table rows and cells
  data.gameData.forEach((campo) => {
    // Create a new table row element
    const tableRow = document.createElement("tr");
    
    const arrayCell = [];
    
    // Loop through 12 times to create 12 table cell elements and append them to the table row
    for (let i = 0; i < 12; i++) {
      const tableCell = document.createElement("td");
      arrayCell.push(tableCell);
      tableRow.appendChild(tableCell);
    }
    
    // Populate the table cells with the data
    arrayCell[0].textContent = campo.player1;
    arrayCell[1].textContent = campo.player2;
    arrayCell[2].textContent = campo.player3;
    arrayCell[3].textContent = campo.dealer;
    arrayCell[4].textContent = campo.player1score;
    arrayCell[5].textContent = campo.player2score;
    arrayCell[6].textContent = campo.player3score;
    arrayCell[7].textContent = campo.dealerScore;
    arrayCell[8].textContent = campo.player1bet;
    arrayCell[9].textContent = campo.player2bet;
    arrayCell[10].textContent = campo.player3bet;
    arrayCell[11].textContent = campo.timestamp;
    
    // Append the table row to the "campos" element
    const body = document.querySelector("#campos");
    body.appendChild(tableRow);
  });
}