// Initialize the deck with 4 cards of each suit (basto, copa, espada, oro)
const baraja = [4, 4, 4, 4, 4, 4, 4, 4, 4];
const cartasRestantes = [];
for (let i = 0; i <= baraja.length; i++) {
  cartasRestantes.push(["basto", "copa", "espada", "oro"]);
}

// Retrieve player names from session storage
const nombres = JSON.parse(sessionStorage.getItem("nombres"));
const resultadoPartida = [];

let puntuacionValue = 0; // Current score of the player
let nroJugador = 0; // Current player's number

// Select DOM elements
const bloqueCartas = document.querySelector("#cartas");
const colSpace = document.createElement("div");
colSpace.classList.add("col-2");

// Add event listener to execute code when the DOM is loaded
document.addEventListener("DOMContentLoaded", initialize, false);

function initialize() {
  // Set the current player's name and number in the UI
  const nombreTurno = document.querySelector("#turno");
  nombreTurno.textContent = nombres[nroJugador] + " (Número " + (nroJugador + 1) + ")";

  if (nroJugador == 0) {
    // Add event listeners to buttons for generating cards and ending the turn
    document.querySelector("#bocarriba").addEventListener("click", (e) => {
      generarCarta(e);
    });
    document.querySelector("#bocabajo").addEventListener("click", (e) => {
      generarCarta(e);
    });
    document.querySelector("#plantarse").addEventListener("click", (e) => {
      nextTurn();
    });
  }

  abajo(obtenerNumero()); // Display a facedown card for the current player's turn
}

function generarCarta(e) {
  let numeroGenerado = obtenerNumero();
  if (e.target == document.querySelector("#bocarriba")) {
    const clone = pintarImagen(numeroGenerado);
    document.querySelector("#cartas").appendChild(clone);
    if (puntuacionValue > 7.5) {
      alert("Puntuacio sobrepasada con " + puntuacionValue);
      nextTurn();
    }
  } else {
    const oldCartaAbajo = document.querySelector("#imagenHide");
    oldCartaAbajo.src = oldCartaAbajo.getAttribute("showUrl");
    oldCartaAbajo.removeEventListener("mouseleave", hideValue);
    oldCartaAbajo.id = "imagen";
    oldCartaAbajo.removeAttribute("showUrl");
    abajo(numeroGenerado);
    if (puntuacionValue > 7.5) {
      alert("Puntuacio sobrepasada con " + puntuacionValue);
      nextTurn();
    }
  }
}

function pintarImagen(numeroGenerado) {
  const clone = colSpace.cloneNode(true);
  const palo = obtenerPalo(numeroGenerado);
  const imagenCartas = new Image(100, 200);

  // Adjust the card number for the image file name
  if (numeroGenerado > 6) {
    numeroGenerado += 3;
  } else {
    numeroGenerado += 1;
  }

  imagenCartas.id = "imagen";
  imagenCartas.src = "AllCards/" + palo + numeroGenerado + ".png";
  clone.appendChild(imagenCartas);
  return clone;
}

function abajo(numeroGenerado) {
  const clone = pintarImagen(numeroGenerado);
  const imagen = clone.querySelector("#imagen");
  const showUrlFull = imagen.src;
  //const showUrl = imagen.src.substring(28);
  imagen.setAttribute("showUrl", showUrlFull);
  imagen.id = "imagenHide";
  imagen.src = "AllCards/dorso.png";
  imagen.addEventListener("mouseenter", () => {
    imagen.src = showUrlFull;
  });
  imagen.addEventListener(
    "mouseleave",
    (hideValue = function fn() {
      imagen.src = "AllCards/dorso.png";
    }),
    false
  );
  document.querySelector("#cartas").appendChild(clone);
}

function obtenerNumero() {
  let eleccion = Math.floor(Math.random() * 9);
  let numeroGenerado = 0;

  if (baraja[eleccion] != 0) {
    baraja[eleccion]--;
    if (eleccion > 6) {
      numeroGenerado = 0.5;
    } else {
      numeroGenerado = eleccion + 1;
    }
  } else {
    obtenerNumero();
  }

  puntuacionValue += numeroGenerado; // Add the card value to the current score
  return eleccion; // Return the card index
}

function obtenerPalo(eleccion) {
  const random = Math.floor(
    Math.random() * (cartasRestantes[eleccion].length - 1)
  );
  const palo = cartasRestantes[eleccion][random];
  cartasRestantes[eleccion].splice(random, 1);
  return palo;
}

function nextTurn() {
  resultadoPartida.push(puntuacionValue); // Save the current player's score

  if (nroJugador == 3) {
    // If all players have played, submit the form data
    sessionStorage.setItem(
      "resultadoPartida",
      JSON.stringify(resultadoPartida)
    );
    const nombres = JSON.parse(sessionStorage.getItem("nombres"));
    const apuestas = JSON.parse(sessionStorage.getItem("apuestas"));
    submitFormData(nombres, apuestas);
  } else {
    // Move to the next player's turn
    nroJugador++;
    puntuacionValue = 0; // Reset the score
    document.querySelector("#cartas").innerHTML = ''; // Clear the cards on the table
    initialize();
  }
}

function submitFormData(nombres, apuestas) {
  // Fill the form fields with the player names, scores, and bets
  const formData = document.querySelector("#registerData");
  const player1Input = formData.querySelector('input[name="player1"]');
  const player2Input = formData.querySelector('input[name="player2"]');
  const player3Input = formData.querySelector('input[name="player3"]');
  const dealerInput = formData.querySelector('input[name="dealer"]');
  const player1ScoreInput = formData.querySelector(
    'input[name="player1score"]'
  );
  const player2ScoreInput = formData.querySelector(
    'input[name="player2score"]'
  );
  const player3ScoreInput = formData.querySelector(
    'input[name="player3score"]'
  );
  const dealerScoreInput = formData.querySelector('input[name="dealerScore"]');
  const player1BetInput = formData.querySelector('input[name="player1bet"]');
  const player2BetInput = formData.querySelector('input[name="player2bet"]');
  const player3BetInput = formData.querySelector('input[name="player3bet"]');

  player1Input.value = nombres[0];
  player2Input.value = nombres[1];
  player3Input.value = nombres[2];
  dealerInput.value = nombres[3];
  player1ScoreInput.value = resultadoPartida[0];
  player2ScoreInput.value = resultadoPartida[1];
  player3ScoreInput.value = resultadoPartida[2];
  dealerScoreInput.value = resultadoPartida[3];
  player1BetInput.value = apuestas[0];
  player2BetInput.value = apuestas[1];
  player3BetInput.value = apuestas[2];

  formData.submit(); // Submit the form
}