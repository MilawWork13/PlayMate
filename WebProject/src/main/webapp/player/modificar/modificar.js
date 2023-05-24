let email = "";

function verifyDiv() {

    // Create the main container div
    const verifyDiv = document.createElement('div');
    verifyDiv.id = 'verify';

    // Create the h3 element for "El correo existe"
    const h3El1 = document.createElement('h3');
    h3El1.className = 'text-center mt-4';
    h3El1.textContent = 'El correo existe';

    // Create the first paragraph element
    const pEl1 = document.createElement('p');
    pEl1.className = 'text-center mt-4';
    pEl1.textContent = 'Se ha enviado al correo el código para cambiar contraseña';

    // Create the second paragraph element
    const pEl2 = document.createElement('p');
    pEl2.className = 'text-center';
    pEl2.textContent = 'Introduzcalo en el campo de abajo';

    // Create the div for the inner row
    const innerRowDiv = document.createElement('div');
    innerRowDiv.className = 'mb-3 mt-4 row justify-content-center';

    // Create the label for the code input field
    const labelEl1 = document.createElement('label');
    labelEl1.htmlFor = 'code';
    labelEl1.className = 'col-sm-2 col-form-label';
    labelEl1.textContent = 'Código de comprobación';

    // Create the div for the input field
    const inputDiv1 = document.createElement('div');
    inputDiv1.className = 'col-4';

    // Create the input field
    const inputEl1 = document.createElement('input');
    inputEl1.type = 'password';
    inputEl1.className = 'form-control';
    inputEl1.id = 'code';
    inputEl1.name = 'code';
    inputEl1.placeholder = 'Ejemplo: a5a@!fas59';

    // Append the input field to its parent div
    inputDiv1.appendChild(inputEl1);

    // Append the elements in the proper hierarchy
    innerRowDiv.appendChild(labelEl1);
    innerRowDiv.appendChild(inputDiv1);
    verifyDiv.appendChild(h3El1);
    verifyDiv.appendChild(pEl1);
    verifyDiv.appendChild(pEl2);
    verifyDiv.appendChild(innerRowDiv);

    // Create the div for the submit button
    const submitDiv1 = document.createElement('div');
    submitDiv1.className = 'row mt-4 d-flex justify-content-center';

    // Create the submit button
    const submitBtn1 = document.createElement('button');
    submitBtn1.type = 'button';
    submitBtn1.id = 'checkCode';
    submitBtn1.className = 'btn btn-secondary';
    submitBtn1.style.width = '200px';
    submitBtn1.style.height = '40px';
    submitBtn1.textContent = 'Verificar código';

    // Append the button to its parent div
    submitDiv1.appendChild(submitBtn1);
    verifyDiv.appendChild(submitDiv1);

    return verifyDiv;
}

function changeDiv() {

    // Create the second container div
    const changeDiv = document.createElement('div');
    changeDiv.id = 'change';

    // Create the h3 element for "Código verificado"
    const h3El2 = document.createElement('h3');
    h3El2.className = 'text-center mt-4';
    h3El2.textContent = 'Código verificado';

    // Create the third paragraph element
    const pEl3 = document.createElement('p');
    pEl3.className = 'text-center mt-2';
    pEl3.textContent = 'Introduzca su nueva contraseña';

    // Create the second inner row div
    const innerRowDiv2 = document.createElement('div');
    innerRowDiv2.className = 'mb-3 mt-4 row justify-content-center';

    // Create the second label for the password input field
    const labelEl2 = document.createElement('label');
    labelEl2.htmlFor = 'password';
    labelEl2.className = 'col-sm-2 col-form-label';
    labelEl2.textContent = 'Nueva contraseña';

    // Create the second div for the password input field
    const inputDiv2 = document.createElement('div');
    inputDiv2.className = 'col-4';

    // Create the password input field
    const inputEl2 = document.createElement('input');
    inputEl2.type = 'password';
    inputEl2.className = 'form-control';
    inputEl2.id = 'password';
    inputEl2.name = 'password';
    inputEl2.placeholder = '********';

    // Append the password input field to its parent div
    inputDiv2.appendChild(inputEl2);

    // Append the elements in the proper hierarchy
    innerRowDiv2.appendChild(labelEl2);
    innerRowDiv2.appendChild(inputDiv2);
    changeDiv.appendChild(h3El2);
    changeDiv.appendChild(pEl3);
    changeDiv.appendChild(innerRowDiv2);

    // Create the second submit div
    const submitDiv2 = document.createElement('div');
    submitDiv2.className = 'row mt-2 d-flex justify-content-center';

    // Create the second submit button
    const submitBtn2 = document.createElement('button');
    submitBtn2.type = 'submit';
    submitBtn2.id = 'changePassword';
    submitBtn2.className = 'btn btn-secondary';
    submitBtn2.style.width = '200px';
    submitBtn2.style.height = '40px';
    submitBtn2.textContent = 'Cambiar contraseña';

    // Append the second button to its parent div
    submitDiv2.appendChild(submitBtn2);
    changeDiv.appendChild(submitDiv2);

    return changeDiv;
}


// Event listener for the "DOMContentLoaded" event
window.addEventListener("DOMContentLoaded", () => {
    const inputEmail = document.querySelector("#email");
    const checkEmail = document.querySelector("#checkEmail");
    checkEmail.addEventListener("click", () => {
        email = inputEmail.value;

        // Send a GET request to check the email
        fetch("http://localhost:8080/WebProject/solicitud-contrasena?email=" + email, {
            method: "GET"
        })
        .then(response => {
            if (response.status == 500) {
                window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
            } else {
                return response.text()
            }
        })
        .then(data => {
            if (data == "0") {
                alert("No se ha encontrado un usuario con ese correo");
            } else if (data == "1") {
                // Replace the "user" div with the "verify" div
                const divUser = document.querySelector("#user");
                divUser.replaceWith(verifyDiv());

                const divVerify = document.querySelector("#verify");

                // Event listener for the "checkCode" button inside the "verify" div
                divVerify.querySelector("#checkCode").addEventListener("click", () => {
                    // Send a POST request to check the verification code
                    fetch("http://localhost:8080/WebProject/solicitud-contrasena", {
                        method: 'POST',
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            email: email,
                            code: divVerify.querySelector("#code").value
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
                            // Replace the "verify" div with the "change" div
                            const divVerify = document.querySelector("#verify");
                            divVerify.replaceWith(changeDiv());

                            const divChange = document.querySelector("#change");

                            // Event listener for the "changePassword" button inside the "change" div
                            divChange.querySelector("#changePassword").addEventListener("click", () => {
                                // Send a POST request to change the password
                                fetch("http://localhost:8080/WebProject/cambiar-contrasena", {
                                    method: "POST",
                                    headers: {
                                        "Content-Type": "application/json"
                                    },
                                    body: JSON.stringify({
                                        nickname: "",
                                        password: divChange.querySelector("#password").value,
                                        email: email
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
                                        alert("Contraseña cambiada correctamente");
                                        window.location.replace("http://localhost:8080/WebProject/index.jsp");
                                    } else if (data == "0") {
                                        console.log(data);
                                        //window.location.replace("http://localhost:8080/WebProject/error/error.jsp");
                                    }
                                })
                            });
                        } else if (data == "0") {
                            alert("El código introducido no es correcto");
                        }
                    })
                });
            }
        })
        .catch(error => {
            console.log(error);
        });
    });
});