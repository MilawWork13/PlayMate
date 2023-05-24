const apuestas = JSON.parse(sessionStorage.getItem("apuestas")); // Retrieve 'apuestas' data from session storage and parse it as JSON
const resultadoPartida = JSON.parse(sessionStorage.getItem("resultadoPartida")); // Retrieve 'resultadoPartida' data from session storage and parse it as JSON
const nombresJugadores = JSON.parse(sessionStorage.getItem("nombres")); // Retrieve 'nombres' data from session storage and parse it as JSON

// Create an object with the data to be sent
const dataSend = {
  player1: nombresJugadores[0],
  player2: nombresJugadores[1],
  player3: nombresJugadores[2],
  dealer: nombresJugadores[3],
  player1score: resultadoPartida[0],
  player2score: resultadoPartida[1],
  player3score: resultadoPartida[2],
  dealerScore: resultadoPartida[3],
  player1bet: apuestas[0],
  player2bet: apuestas[1],
  player3bet: apuestas[2]
}

window.addEventListener('DOMContentLoaded', resolucion, false);

function resolucion() {
    const divTitulo = document.createElement("div");
    divTitulo.classList.add("row");

    const h2 = document.createElement("h2");
    h2.textContent = "Resolución de la partida";
    h2.classList.add("text-center");
    h2.classList.add("mt-2");
    divTitulo.appendChild(h2);

    const divContainer = document.createElement("div");
    divContainer.classList.add("row");

    const colSpacing = document.createElement("div");
    colSpacing.classList.add("col-2");
    divContainer.appendChild(colSpacing);

    const colContent = document.createElement("div");
    colContent.classList.add("col-8");

    // create the table with class "table"
    const table = document.createElement("table");
    table.classList.add("table");

    // create the table head
    const thead = document.createElement("thead");

    // create the row for the table head
    const trHead = document.createElement("tr");

    // create the table headers
    const thJugador = document.createElement("th");
    const thNombre = document.createElement("th");
    const thApuesta = document.createElement("th");
    const thPuntos = document.createElement("th");
    const thResultado = document.createElement("th");

    // set the header text content
    thJugador.textContent = "Nº jugador";
    thNombre.textContent = "Nombre";
    thApuesta.textContent = "Apuesta";
    thPuntos.textContent = "Puntos";
    thResultado.textContent = "Resultado";

    // append the table headers to the table head row
    trHead.appendChild(thJugador);
    trHead.appendChild(thNombre);
    trHead.appendChild(thApuesta);
    trHead.appendChild(thPuntos);
    trHead.appendChild(thResultado);

    // append the table head row to the table head
    thead.appendChild(trHead);

    // create the table body with id "filasInfo"
    const tbody = document.createElement("tbody");
    tbody.id = "filasInfo";

    // append the table head and body to the table
    table.appendChild(thead);

    const puntosBanca = resultadoPartida[resultadoPartida.length - 1];
    for (let i = 3; i >= 0; i--) {
      const tr = document.createElement("tr");

      // create four table cells
      const td1 = document.createElement("td");
      if (i == 3) {
        td1.textContent = i + 1 + " (Banca)";
      } else {
        td1.textContent = i + 1;
      }
      const td2 = document.createElement("td");
      td2.textContent = nombresJugadores[i];
      const td3 = document.createElement("td");
      if (i != 3) {
        td3.textContent = apuestas[i];
      }
      const td4 = document.createElement("td");
      td4.textContent = resultadoPartida[i];
      const td5 = document.createElement("td");

      const puntosJugador = resultadoPartida[i];

      /*
        Sólo se va a marcar que la banca pierde si saca más de 7.5, pues automáticamente perdería contra todos.
        El jugador pierde si:
          -Saca más de 7.5
          -Saca menos que la banca, siempre que esta tenga menos de 7.5
          -Tenga los mismos puntos que la banca
        El jugador gana si:
          -Tiene menos o igual de 7.5 y la banca saca más de 7.5
          -Tiene más que la banca, siempre que el jugador tenga menos de 7.5
        Aparte, si gana con un 7.5 se indicará
      */
      
      if (puntosJugador > 7.5) {
        td5.textContent = "Pierde";
      } else if (puntosJugador < puntosBanca && puntosBanca <= 7.5) {
        td5.textContent = "Pierde";
      } else if (puntosJugador <= 7.5 && puntosBanca > 7.5) {
        td5.textContent = "Gana";
        if (puntosJugador == 7.5) {
          td5.textContent += " (Siete y medio)";
        }
      } else if (puntosJugador > puntosBanca && puntosJugador <= 7.5) {
        td5.textContent = "Gana";
        if (puntosJugador == 7.5) {
          td5.textContent += " (Siete y medio)";
        }
      } else if (puntosJugador == puntosBanca && i+1 != 4) {
        td5.textContent = "Pierde";
      }

      // append the table cells to the table row
      tr.appendChild(td1);
      tr.appendChild(td2);
      tr.appendChild(td3);
      tr.appendChild(td4);
      tr.appendChild(td5);

      tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    colContent.appendChild(table);
    divContainer.appendChild(colContent);

    const form = document.createElement('form');
    form.action="/WebProject/index.jsp";
    form.method="GET";

    const row = document.createElement('div');
    row.setAttribute('class', 'row');

    const col = document.createElement('div');
    col.setAttribute('class', 'col d-flex justify-content-center');

    const returnButton = document.createElement('button');
    returnButton.setAttribute('type', 'submit');
    returnButton.setAttribute('class', 'btn btn-primary');
    returnButton.setAttribute('id', 'botonReturn');

    // Set the button text
    returnButton.textContent = 'Volver al menú principal';
    
    col.appendChild(returnButton);

    // Add the col div to the row div
    row.appendChild(col);

    // Add the row div to the DOM

    form.appendChild(row);

    divContainer.appendChild(form);

    document.body.appendChild(divTitulo);
    document.body.appendChild(divContainer);

    sessionStorage.clear();
}