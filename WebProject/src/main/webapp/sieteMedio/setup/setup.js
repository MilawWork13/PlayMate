const apuestas = [];
const nombres = [];
const ready = [0, 0, 0, 0];

const bloqueCon = (i) => {
  // Create div with class "row d-flex justify-content-center"
  const row = document.createElement('div');
  row.classList.add('row', 'd-flex', 'justify-content-center');

  // Create first label div
  const labelDiv1 = document.createElement('div');
  labelDiv1.classList.add('col-2', 'mt-4');
  row.appendChild(labelDiv1);

  // Create label element for "Nombre" and append to first label div
  const label1 = document.createElement('label');
  label1.setAttribute('for', 'nickname');
  label1.classList.add('col-sm-2', 'col-form-label');
  label1.textContent = 'Nombre';
  labelDiv1.appendChild(label1);

  // Create input div for "Nombre"
  const inputDiv1 = document.createElement('div');
  inputDiv1.classList.add('col-10', 'mt-4');
  row.appendChild(inputDiv1);

  // Create input element for "Nombre" and append to input div for "Nombre"
  const input1 = document.createElement('input');
  input1.setAttribute('type', 'text');
  input1.setAttribute('id', 'nickname');
  input1.setAttribute('name', 'nickname');
  input1.setAttribute('placeholder', 'Nombre de usuario');
  input1.classList.add('form-control');
  inputDiv1.appendChild(input1);

  // Create second label div
  const labelDiv2 = document.createElement('div');
  labelDiv2.classList.add('col-2', 'mt-4');
  row.appendChild(labelDiv2);

  // Create label element for "Contraseña" and append to second label div
  const label2 = document.createElement('label');
  label2.setAttribute('for', 'password');
  label2.classList.add('col-sm-2', 'col-form-label');
  label2.textContent = 'Contraseña';
  labelDiv2.appendChild(label2);

  // Create input div for "Contraseña"
  const inputDiv2 = document.createElement('div');
  inputDiv2.classList.add('col-10', 'mt-4');
  row.appendChild(inputDiv2);

  // Create input element for "Contraseña" and append to input div for "Contraseña"
  const input2 = document.createElement('input');
  input2.setAttribute('type', 'password');
  input2.setAttribute('id', 'password');
  input2.setAttribute('name', 'password');
  input2.setAttribute('placeholder', '********');
  input2.classList.add('form-control');
  inputDiv2.appendChild(input2);

  // Create div with id "inputApuesta" and classes "mb-3", "mt-4", and "row"
  const inputApuesta = document.createElement('div');
  inputApuesta.setAttribute('id', 'inputApuesta');
  inputApuesta.classList.add('mb-3', 'mt-4', 'row');
  row.appendChild(inputApuesta);

  // Create input div for "Euros" and append to inputApuesta
  const inputDiv3 = document.createElement('div');
  inputDiv3.classList.add('col-4');
  inputApuesta.appendChild(inputDiv3);

  if (i != 4) {
    // Create input element for "Euros" and append to input div for "Euros"
    const input3 = document.createElement('input');
    input3.setAttribute('type', 'number');
    input3.setAttribute('id', 'euros');
    input3.setAttribute('min', '0');
    input3.setAttribute('max', '2');
    input3.setAttribute('placeholder', 'Euros (máx. 2€)');
    input3.classList.add('form-control');
    inputDiv3.appendChild(input3);

    // Create input div for "Céntimos" and append to inputApuesta
    const inputDiv4 = document.createElement('div');
    inputDiv4.classList.add('col-7');
    inputApuesta.appendChild(inputDiv4);

    // Create input element for "Céntimos" and append to input div for "Céntimos"
    const input4 = document.createElement('input');
    input4.setAttribute('type', 'number');
    input4.setAttribute('id', 'cents');
    input4.setAttribute('min', '0');
    input4.setAttribute('max', '99');
    input4.setAttribute('placeholder', 'Céntimos (máx. 99 céntimos)');
    input4.classList.add('form-control');
    inputDiv4.appendChild(input4);
  }

  const confirmDiv = document.createElement('div');
  confirmDiv.id = 'confirm';
  confirmDiv.classList.add('row');

  const confirmBtnDiv = document.createElement('div');
  confirmBtnDiv.classList.add('col-8', 'mt-4', 'mb-3', 'd-flex', 'justify-content-center');

  const confirmBtn = document.createElement('button');
  confirmBtn.type = 'button';
  confirmBtn.classList.add('btn', 'btn-primary');
  confirmBtn.id = 'introducirDatos';
  confirmBtn.textContent = 'Comprobar y confirmar';

  confirmBtnDiv.appendChild(confirmBtn);

  const modifyBtnDiv = document.createElement('div');
  modifyBtnDiv.classList.add('col-1', 'mt-4', 'mb-3', 'd-flex', 'justify-content-center');

  const modifyBtn = document.createElement('button');
  modifyBtn.type = 'button';
  modifyBtn.classList.add('btn', 'btn-primary');
  modifyBtn.id = 'modificarDatos';
  modifyBtn.textContent = 'Modificar';
  modifyBtn.disabled = true;

  modifyBtnDiv.appendChild(modifyBtn);

  confirmDiv.appendChild(confirmBtnDiv);
  confirmDiv.appendChild(modifyBtnDiv);

  row.appendChild(confirmDiv);

  return row;

}

const bloqueSin = (i) => {

  // Create row div and set class and justify content
  const row = document.createElement('div');
  row.classList.add('row', 'd-flex', 'justify-content-center');

  // Create inputApuesta div and set class
  const inputApuesta = document.createElement('div');
  inputApuesta.setAttribute('id', 'inputApuesta');
  inputApuesta.classList.add('mb-3', 'mt-4', 'row');
  row.appendChild(inputApuesta);

  // Create input div for "Euros" and append to inputApuesta
  const inputDiv1 = document.createElement('div');
  inputDiv1.classList.add('col-5');
  inputApuesta.appendChild(inputDiv1);

  if (i != 4) {
    // Create input element for "Euros" and append to input div for "Euros"
    const input1 = document.createElement('input');
    input1.setAttribute('type', 'number');
    input1.setAttribute('id', 'euros');
    input1.setAttribute('min', '0');
    input1.setAttribute('max', '2');
    input1.setAttribute('placeholder', 'Euros (máx. 2€)');
    input1.classList.add('form-control');
    inputDiv1.appendChild(input1);

    // Create input div for "Céntimos" and append to inputApuesta
    const inputDiv2 = document.createElement('div');
    inputDiv2.classList.add('col-7');
    inputApuesta.appendChild(inputDiv2);

    // Create input element for "Céntimos" and append to input div for "Céntimos"
    const input2 = document.createElement('input');
    input2.setAttribute('type', 'number');
    input2.setAttribute('id', 'cents');
    input2.setAttribute('min', '0');
    input2.setAttribute('max', '99');
    input2.setAttribute('placeholder', 'Céntimos (máx. 99 céntimos)');
    input2.classList.add('form-control');
    inputDiv2.appendChild(input2);
  }

  const confirmDiv = document.createElement('div');
  confirmDiv.id = 'confirm';
  confirmDiv.classList.add('row');

  const confirmBtnDiv = document.createElement('div');
  confirmBtnDiv.classList.add('col-8', 'mt-4', 'mb-3', 'd-flex', 'justify-content-center');

  const confirmBtn = document.createElement('button');
  confirmBtn.type = 'button';
  confirmBtn.classList.add('btn', 'btn-primary');
  confirmBtn.id = 'introducirDatos';
  confirmBtn.textContent = 'Comprobar y confirmar';

  confirmBtnDiv.appendChild(confirmBtn);

  const modifyBtnDiv = document.createElement('div');
  modifyBtnDiv.classList.add('col-1', 'mt-4', 'mb-3', 'd-flex', 'justify-content-center');

  const modifyBtn = document.createElement('button');
  modifyBtn.type = 'button';
  modifyBtn.classList.add('btn', 'btn-primary');
  modifyBtn.id = 'modificarDatos';
  modifyBtn.textContent = 'Modificar';

  modifyBtnDiv.appendChild(modifyBtn);

  confirmDiv.appendChild(confirmBtnDiv);
  confirmDiv.appendChild(modifyBtnDiv);
  row.appendChild(confirmDiv);

  return row;

}

window.addEventListener('DOMContentLoaded', () => {
  // Event listener for the change event on the containerToGet element
  document.querySelector('#containerToGet').addEventListener('change', (e) => {
    if (e.target.matches('#jugarCon') && e.target.checked) {
      for (let i = 1; i <= 4; i++) {
        if (parseInt(e.target.value) == i) {
          nombres[i - 1] = "";
          apuestas[i - 1] = 0;
          document.querySelector('#input' + i).innerHTML = '';
          document.querySelector('#input' + i).appendChild(bloqueCon(i));
          eventConfig(i);
        }
      }
    } else if (e.target.matches('#jugarSin') && e.target.checked) {
      for (let i = 1; i <= 4; i++) {
        if (parseInt(e.target.value) == i) {
          nombres[i - 1] = "";
          apuestas[i - 1] = 0;
          document.querySelector('#input' + i).innerHTML = '';
          document.querySelector('#input' + i).appendChild(bloqueSin(i));
          eventConfigSin(i);
        }
      }
    } else {
      e.stopPropagation();
    }
  });

  // Event listener for the click event on the botonComenzar button
  document.querySelector("#botonComenzar").addEventListener("click", (e) => {
    // Check if all players are ready
    let comienzo = true;
    ready.forEach(listo => {
      if (listo == 0) {
        comienzo = false;
      }
    });

    // Proceed if all players are ready
    if (comienzo == true) {
      sessionStorage.setItem("apuestas", JSON.stringify(apuestas));
      sessionStorage.setItem("nombres", JSON.stringify(nombres));
      fetch("http://localhost:8080/WebProject/siete-sesion", {
        method: "GET"
      })
        .then(response => {
          window.location.replace("http://localhost:8080/WebProject/sieteMedio/juego/juego.jsp");
        })
        .catch(error => {
          console.log(error);
        });
    } else {
      alert("Faltan jugadores");
    }
  });

  // Event listener to come back to index.jsp
  document.querySelector('.btn-secondary').addEventListener('click', () => {
    window.location.replace("http://localhost:8080/WebProject/index.jsp")
  });
});

function eventConfigSin(i) {
  const bloqueInput = document.querySelector("#input" + i);
  if (i != 4) {
    bloqueInput.addEventListener('keyup', (e) => {
      if (e.target.matches('#euros')) {
        checkEur(e, bloqueInput);
      } else if (e.target.matches('#cents')) {
        checkCents(e, bloqueInput);
      }
    });
  }
  bloqueInput.querySelector('#introducirDatos').value = i;
  const modify = bloqueInput.querySelector("#modificarDatos");
  const confirm = bloqueInput.querySelector("#introducirDatos");
  confirm.addEventListener('click', (e) => {
    const euros = bloqueInput.querySelector('#euros');
    const cents = bloqueInput.querySelector('#cents');
    if (i != 4) {
      if (cents.value == "" && euros.value == "") {
        alert("Debe introducir datos");
      } else {
        nombres[i - 1] = "No registrado";
        if (euros.value == "") {
          euros.value = 0;
        } else if (cents.value == "" || (parseFloat(euros.value) + (parseFloat(cents.value) / 100)) > 2) {
          cents.value = 0;
        }
        apuestas[i - 1] = parseFloat(euros.value) + parseFloat(cents.value) / 100;
        confirm.textContent = "Datos registrados";
        confirm.setAttribute("disabled", "disabled");
        modify.removeAttribute("disabled");
        ready[i - 1] = 1;
      }
    } else {
      nombres[i - 1] = "No registrado";
      confirm.textContent = "Datos registrados";
      confirm.setAttribute("disabled", "disabled");
      modify.removeAttribute("disabled");
      ready[i - 1] = 1;
    }
  });
  modify.addEventListener('click', (e) => {
    nombres[i - 1] = "";
    apuestas[i - 1] = 0;
    confirm.textContent = "Comprobar y confirmar";
    confirm.removeAttribute("disabled");
    modify.setAttribute("disabled", "disabled");
    ready[i - 1] = 0;
  });
}

function eventConfigSin(i) {
  // Get the input block element based on the given index
  const bloqueInput = document.querySelector("#input" + i);

  // Add keyup event listener to the input fields for euros and cents
  if (i != 4) {
    bloqueInput.addEventListener('keyup', (e) => {
      if (e.target.matches('#euros')) {
        checkEur(e, bloqueInput);
      } else if (e.target.matches('#cents')) {
        checkCents(e, bloqueInput);
      }
    });
  }

  // Set the value of the "introducirDatos" input field to the current index
  bloqueInput.querySelector('#introducirDatos').value = i;

  // Get the "modificarDatos" and "introducirDatos" buttons
  const modify = bloqueInput.querySelector("#modificarDatos");
  const confirm = bloqueInput.querySelector("#introducirDatos");

  // Add click event listener to the "introducirDatos" button
  confirm.addEventListener('click', (e) => {
    const euros = bloqueInput.querySelector('#euros');
    const cents = bloqueInput.querySelector('#cents');

    if (i != 4) {
      if (cents.value == "" && euros.value == "") {
        alert("Debe introducir datos");
      } else {
        nombres[i - 1] = "No registrado";

        // Set the default value of euros to 0 if it is empty
        if (euros.value == "") {
          euros.value = 0;
        } else if (cents.value == "" || (parseFloat(euros.value) + (parseFloat(cents.value) / 100)) > 2) {
          // Set the default value of cents to 0 if it is empty or the total exceeds 2
          cents.value = 0;
        }

        // Calculate the total amount and assign it to the corresponding index in the apuestas array
        apuestas[i - 1] = parseFloat(euros.value) + parseFloat(cents.value) / 100;

        confirm.textContent = "Datos registrados";
        confirm.setAttribute("disabled", "disabled");
        modify.removeAttribute("disabled");
        ready[i - 1] = 1;
      }
    } else {
      nombres[i - 1] = "No registrado";

      confirm.textContent = "Datos registrados";
      confirm.setAttribute("disabled", "disabled");
      modify.removeAttribute("disabled");
      ready[i - 1] = 1;
    }
  });

  // Add click event listener to the "modificarDatos" button
  modify.addEventListener('click', (e) => {
    // Reset the values and enable the "introducirDatos" button for modification
    nombres[i - 1] = "";
    apuestas[i - 1] = 0;
    confirm.textContent = "Comprobar y confirmar";
    confirm.removeAttribute("disabled");
    modify.setAttribute("disabled", "disabled");
    ready[i - 1] = 0;
  });
}

function eventConfig(i) {
  // Get the input block element based on the given index
  const bloqueInput = document.querySelector('#input' + i);

  // Add keyup event listener to the input fields for euros and cents
  if (i != 4) {
    bloqueInput.addEventListener('keyup', (e) => {
      if (e.target.matches('#euros')) {
        checkEur(e, bloqueInput);
      } else if (e.target.matches('#cents')) {
        checkCents(e, bloqueInput);
      }
      bloqueInput.querySelector('#introducirDatos').value = i;
    });
  }

  // Set the value of the "introducirDatos" input field to the current index
  bloqueInput.querySelector('#introducirDatos').value = i;

  // Get the "modificarDatos" and "introducirDatos" buttons
  const modify = bloqueInput.querySelector("#modificarDatos");
  const confirm = bloqueInput.querySelector("#introducirDatos");

  // Add click event listener to the "introducirDatos" button
  confirm.addEventListener('click', (e) => {
    if (e.target.matches('#introducirDatos')) {
      let nameFound = false;
      const name = bloqueInput.querySelector("#nickname");
      const password = bloqueInput.querySelector("#password");
      const euros = bloqueInput.querySelector("#euros");
      const cents = bloqueInput.querySelector("#cents");

      // Check if name and password fields are empty
      if (name.value == "" || password.value == "") {
        alert("Debe introducir todos los datos");
      } else {
        if (i != 4 && (cents.value == "" && euros.value == "")) {
          alert("Debe introducir todos los datos");
        }

        // Check if the name already exists in the game
        for (let j = 0; j < nombres.length; j++) {
          if (nombres[j] == name.value) {
            alert("El usuario con nombre '" + name.value + "' ya se encuentra en la partida");
            j = nombres.length;
            nameFound = true;
          }
        }

        // If the name is not found, validate the player credentials
        if (nameFound == false) {
          const dataSend = {
            nickname: name.value,
            password: password.value
          }

          // Make a fetch request to validate the player
          fetch('http://localhost:8080/WebProject/validate-player', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dataSend),
          })
            .then(response => {
              if (response.status == 500) {
                window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
              } else {
                return response.text()
              }
            })
            .then(data => {
              if (data == "1") {
                nombres[i - 1] = name.value;

                if (i != 4) {
                  if (euros.value == "") {
                    euros.value = 0;
                  } else if (cents.value == "" || (parseFloat(euros.value) + parseFloat(cents.value) / 100) > 2) {
                    cents.value = 0;
                  }
                  apuestas[i - 1] = parseFloat(euros.value) + parseFloat(cents.value) / 100;
                }

                confirm.textContent = "Datos registrados";
                confirm.setAttribute("disabled", "disabled");
                modify.removeAttribute("disabled");
                ready[i - 1] = 1;
              } else {
                alert("Credenciales no válidas");
              }
            })
            .catch(error => {
              console.error(error);
            });
        }
      }
    }
  });
  modify.addEventListener('click', (e) => {
    // Reset the values and state of the input fields
    nombres[i - 1] = "";
    apuestas[i - 1] = 0;
    confirm.textContent = "Comprobar y confirmar";
    confirm.removeAttribute("disabled");
    modify.setAttribute("disabled", "disabled");
    ready[i - 1] = 0;
  });
}
function checkEur(e, bloqueInput) {
  // Get the euros and cents input fields
  const euros = bloqueInput.querySelector('#euros');
  const cents = bloqueInput.querySelector('#cents');

  // Validate the value of euros
  if (parseInt(euros.value) >= 2) {
    euros.value = 2;
    cents.value = 0;
  } else if (parseInt(euros.value) < 0) {
    euros.value = 0;
  } else if (
    Number(parseFloat(euros.value)) === parseFloat(euros.value) &&
    parseFloat(euros.value) % 1 !== 0
  ) {
    euros.value = Math.floor(parseFloat(euros.value));
  } else {
    e.stopPropagation();
  }
}

function checkCents(e, bloqueInput) {
  // Get the euros and cents input fields
  const euros = bloqueInput.querySelector('#euros');
  const cents = bloqueInput.querySelector('#cents');

  // Validate the value of cents
  if (parseInt(cents.value) > 99) {
    cents.value = 99;
  } else if (parseInt(cents.value) < 0 || parseInt(euros.value) == 2) {
    cents.value = 0;
  } else if (
    Number(parseFloat(cents.value)) === parseFloat(cents.value) &&
    parseFloat(cents.value) % 1 !== 0
  ) {
    cents.value = Math.floor(parseFloat(cents.value));
  } else {
    e.stopPropagation();
  }
}