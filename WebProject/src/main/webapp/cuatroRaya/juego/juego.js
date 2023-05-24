// DOM Elements

// Identify user buttons
const newPlayerButtons = document.getElementsByClassName('btn-classic');
console.log(newPlayerButtons);
const identifyButtons = document.getElementsByClassName('btn-secondary');
const cancelNewPlayerButtons = document.getElementsByClassName('btn-danger');

//Create Date with new appearence
const date = new Date();

let currentDay = String(date.getDate()).padStart(2, '0');
let currentMonth = String(date.getMonth() + 1).padStart(2, "0");
let currentYear = date.getFullYear();

// To display the date as DD-MM-YYYY 
let currentDate = `${currentDay}-${currentMonth}-${currentYear}`;

const gameBoardDiv = document.querySelector('.game-board');
// Create the top row elements
for (let colCounter = 0; colCounter < 7; colCounter++) {
    const divRowTop = document.createElement('div');
    divRowTop.classList.add('cell', 'row-top', 'col-' + colCounter);
    gameBoardDiv.appendChild(divRowTop);
}

// Create the remaining rows and columns
for (let rowCounter = 0; rowCounter < 6; rowCounter++) {
    for (let colCounter = 0; colCounter < 7; colCounter++) {
        const divRowsCols = document.createElement('div');
        if (rowCounter === 0 && colCounter === 0) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'left-border', 'top-border');
        } else if (rowCounter === 0 && colCounter === 6) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'top-border', 'right-border');
        } else if (rowCounter === 0 && (colCounter >= 1 && colCounter <= 5)) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'top-border');
        } else if (rowCounter === 5 && colCounter === 0) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'bottom-border', 'left-border');
        } else if (rowCounter === 5 && colCounter === 6) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'bottom-border', 'right-border');
        } else if (rowCounter === 5 && (colCounter >= 1 && colCounter <= 5)) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'bottom-border');
        } else if (rowCounter >= 1 && rowCounter <= 4 && colCounter === 0) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'left-border');
        } else if (rowCounter >= 1 && rowCounter <= 4 && colCounter === 6) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter, 'right-border');
        } else if (rowCounter >= 1 && rowCounter <= 4 && (colCounter >= 1 && colCounter <= 5)) {
            divRowsCols.classList.add('cell', 'row-' + rowCounter, 'col-' + colCounter);
        }

        // Adds appropriate classes based on the position in the grid to create a border effect
        gameBoardDiv.appendChild(divRowsCols);
    }
}

// Create all elements to store or use in the future
const allCells = document.querySelectorAll('.cell:not(.row-top)');
const topCells = document.querySelectorAll('.cell.row-top');
const resetButton = document.querySelector('.reset');
const statusSpan = document.querySelector('.status');
const columns = [];
const rows = [];
const topRow = [];
const names = [];
const gamesPlayed = [];
const inputNameArr = [];
let winnerName = '';
let winnerColor = '';

// Fill columns array
for (let colCounter = 0; colCounter < 7; colCounter++) {
    const column = [];

    for (let rowCounter = 0; rowCounter < 6; rowCounter++) {
        column.push(allCells[rowCounter * 7 + colCounter]);
    }

    // Pushes cells into the column array
    column.push(topCells[colCounter]);
    columns.push(column);
}

// Fill rows array
for (let rowCounter = 0; rowCounter < 6; rowCounter++) {
    const row = [];

    for (let colCounter = 0; colCounter < 7; colCounter++) {
        row.push(allCells[rowCounter * 7 + colCounter]);
    }

    // Pushes cells into the row array
    rows.push(row);

}
// Top row
for (let colCounter = 0; colCounter < 7; colCounter++) {
    topRow.push(topCells[colCounter]);
}

rows.push(topRow);




window.addEventListener(
    "DOMContentLoaded",
    () => {
        // variables
        let gameIsLive = true;
        let yellowIsNext = true;

        // Function to update the turn indicator
        const updateTurnIndicator = () => {
            const turnIndicator = yellowIsNext ? names[0] : names[1];
            document.querySelector('.turn').textContent = `Turno de: ${turnIndicator}`;
        };



        // Functions

        // Get an array of class names from a cell element
        const getClassListArray = (cell) => {
            const classList = cell.classList;
            return [...classList];
        };

        // Get the row and column location of a cell element
        const getCellLocation = (cell) => {
            const classList = getClassListArray(cell);

            const rowClass = classList.find(className => className.includes('row'));
            const colClass = classList.find(className => className.includes('col'));
            const rowIndex = rowClass[4];
            const colIndex = colClass[4];
            const rowNumber = parseInt(rowIndex, 10);
            const colNumber = parseInt(colIndex, 10);

            return [rowNumber, colNumber];
        };

        // Get the first open cell in a column
        const getFirstOpenCellForColumn = (colIndex) => {
            const column = columns[colIndex];
            const columnWithoutTop = column.slice(0, 6).reverse(); // Reverse the column to start from the bottom

            for (const cell of columnWithoutTop) {
                const classList = getClassListArray(cell);
                if (!classList.includes('yellow') && !classList.includes('red')) {
                    return cell;
                }
            }

            return null;
        };

        // Clear the color from the top cell in a column
        const clearColorFromTop = (colIndex) => {
            const topCell = topCells[colIndex];
            topCell.classList.remove('yellow');
            topCell.classList.remove('red');
        };

        // Get the color of a cell
        const getColorOfCell = (cell) => {
            const classList = getClassListArray(cell);
            if (classList.includes('yellow')) {
                return 'yellow';
            }
            if (classList.includes('red')) {
                return 'red';
            }

            return null;
        };

        // Check if the given cells form a winning combination
        const checkWinningCells = (cells) => {
            if (cells.length < 4) return false;

            gameIsLive = false;
            for (const cell of cells) {
                cell.classList.add('win');
            }
            statusSpan.textContent = `${yellowIsNext ? names[0] : names[1]} ha ganado el partido!`;

            winnerName = `${yellowIsNext ? names[0] : names[1]}`;
            if (winnerName == names[0]) {
                winnerColor = '(Amarillo)';
            } else {
                winnerColor = '(Rojo)';
            }

            const game = {
                player1: names[0],
                player2: names[1],
                winner: winnerName + " " + winnerColor,
                date: currentDate
            };

            // Fetch the session endpoint
            fetch("http://localhost:8080/WebProject/raya-sesion", {
                method: "GET"
            })
                .then(response => {
                    console.log("illo");
                })
                .catch(error => {
                    console.log(error);
                });

            // Send the game data to the server
            fetch('http://localhost:8080/WebProject/register-game-raya', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(game)
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Data sent successfully');
                    } else {
                        console.error('Error sending data');
                    }
                })
                .catch(error => {
                    console.error('Error sending data:', error);
                });

            gamesPlayed.push(game);
            return true;
        };

        const checkStatusOfGame = (cell) => {
            const color = getColorOfCell(cell);
            if (!color) return;
            const [rowIndex, colIndex] = getCellLocation(cell);

            // Check horizontally
            let winningCells = [cell];
            let rowToCheck = rowIndex;
            let colToCheck = colIndex - 1;
            while (colToCheck >= 0) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    colToCheck--;
                } else {
                    break;
                }

            }
            colToCheck = colIndex + 1;
            while (colToCheck <= 6) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    colToCheck++;
                } else {
                    break;
                }
            }
            let isWinningCombo = checkWinningCells(winningCells);
            if (isWinningCombo) return;


            // Check vertically
            winningCells = [cell];
            rowToCheck = rowIndex - 1;
            colToCheck = colIndex;
            while (rowToCheck >= 0) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck--;
                } else {
                    break;
                }
            }
            rowToCheck = rowIndex + 1;
            while (rowToCheck <= 5) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck++;
                } else {
                    break;
                }
            }
            isWinningCombo = checkWinningCells(winningCells);
            if (isWinningCombo) return;


            // Check diagonally /
            winningCells = [cell];
            rowToCheck = rowIndex + 1;
            colToCheck = colIndex - 1;
            while (colToCheck >= 0 && rowToCheck <= 5) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck++;
                    colToCheck--;
                } else {
                    break;
                }
            }
            rowToCheck = rowIndex - 1;
            colToCheck = colIndex + 1;
            while (colToCheck <= 6 && rowToCheck >= 0) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck--;
                    colToCheck++;
                } else {
                    break;
                }
            }
            isWinningCombo = checkWinningCells(winningCells);
            if (isWinningCombo) return;


            // Check diagonally \
            winningCells = [cell];
            rowToCheck = rowIndex - 1;
            colToCheck = colIndex - 1;
            while (colToCheck >= 0 && rowToCheck >= 0) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck--;
                    colToCheck--;
                } else {
                    break;
                }
            }
            rowToCheck = rowIndex + 1;
            colToCheck = colIndex + 1;
            while (colToCheck <= 6 && rowToCheck <= 5) {
                const cellToCheck = rows[rowToCheck][colToCheck];
                if (getColorOfCell(cellToCheck) === color) {
                    winningCells.push(cellToCheck);
                    rowToCheck++;
                    colToCheck++;
                } else {
                    break;
                }
            }
            isWinningCombo = checkWinningCells(winningCells);
            if (isWinningCombo) return;


            // Check to see if we have a tie
            const rowsWithoutTop = rows.slice(0, 6);
            for (const row of rowsWithoutTop) {
                for (const cell of row) {
                    const classList = getClassListArray(cell);
                    if (!classList.includes('yellow') && !classList.includes('red')) {
                        return;
                    }
                }
            }

            gameIsLive = false;
            statusSpan.textContent = "¡Se ha empatado!";
            winnerName = 'Empate';
            winnerColor = '';
        };




        // Event Handlers

        // Handler for cell mouseover event
        const handleCellMouseOver = (e) => {
            if (!gameIsLive) return;
            const cell = e.target;
            const [rowIndex, colIndex] = getCellLocation(cell);

            const topCell = topCells[colIndex];
            topCell.classList.add(yellowIsNext ? 'yellow' : 'red');
        };

        // Handler for cell mouseout event
        const handleCellMouseOut = (e) => {
            const cell = e.target;
            const [rowIndex, colIndex] = getCellLocation(cell);
            clearColorFromTop(colIndex);
        };

        // Handler for cell click event
        const handleCellClick = (event) => {
            if (names.length == 2) {
                if (!gameIsLive) return;

                const cell = event.target;
                const [rowIndex, colIndex] = getCellLocation(cell);

                const openCell = getFirstOpenCellForColumn(colIndex);
                if (!openCell) return;

                const color = yellowIsNext ? 'yellow' : 'red';
                openCell.classList.add(color);

                const winningCells = checkStatusOfGame(openCell);

                if (winningCells) {
                    gameIsLive = false;
                    return;
                }

                clearColorFromTop(colIndex);

                yellowIsNext = !yellowIsNext;
                updateTurnIndicator();
            } else {
                window.alert('Tienen que participar 2 usuarios mínimo!');
            }
        };

        // Adding Event Listeners

        // Add event listeners to each cell
        for (const row of rows) {
            for (const cell of row) {
                cell.addEventListener('mouseover', handleCellMouseOver);
                cell.addEventListener('mouseout', handleCellMouseOut);
                cell.addEventListener('click', handleCellClick);
            }
        }

        // Add event listener to the reset button
        resetButton.addEventListener('click', () => {
            // Reset the game board
            for (const row of rows) {
                for (const cell of row) {
                    cell.classList.remove('red');
                    cell.classList.remove('yellow');
                    cell.classList.remove('win');
                }
            }

            updateTurnIndicator();

            gameIsLive = true;
            yellowIsNext = true;
            statusSpan.textContent = '';
        });

        // Handle identify buttons and input fields
        for (let i = 0; i < identifyButtons.length; i++) {
            // Add event listener for identify button click
            identifyButtons[i].addEventListener('click', () => {
                const userCard = document.querySelector('#user' + (i + 1));

                const inputsContainer = document.createElement('div');
                inputsContainer.setAttribute('id', 'inputsContainer');
                userCard.appendChild(inputsContainer);

                const fieldName = document.createElement('input');
                fieldName.type = 'text';
                fieldName.className = 'form-control';
                fieldName.placeholder = 'Nombre';
                inputNameArr.push(fieldName);

                // Create input field for password
                const fieldPass = document.createElement('input');
                fieldPass.type = 'password';
                fieldPass.className = 'form-control';
                fieldPass.placeholder = 'Contraseña';

                const confirmButton = document.createElement('button');
                confirmButton.classList.add('btn-accept');
                confirmButton.textContent = 'Aceptar';

                const backButton = document.createElement('button');
                backButton.classList.add('btn-dark');
                backButton.textContent = 'Volver';

                inputsContainer.appendChild(fieldName);
                inputsContainer.appendChild(fieldPass);
                userCard.appendChild(confirmButton);
                userCard.appendChild(backButton);

                // Handle back button click
                backButton.addEventListener('click', () => {
                    inputsContainer.remove();
                    confirmButton.remove();
                    backButton.remove();
                    newPlayerButtons[i].style.display = 'initial';
                    identifyButtons[i].style.display = 'initial';
                });

                newPlayerButtons[i].style.display = 'none';
                identifyButtons[i].style.display = 'none';

                // Check if user name display exists
                if (typeof (document.querySelector('#nameUser' + (i + 1))) != 'undefined' && document.querySelector('#nameUser' + (i + 1)) != null) {
                } else {
                    document.querySelector('.textPlayer' + (i + 1)).remove();
                }
                cancelNewPlayerButtons[i].style.display = 'none';

                // Handle confirm button click
                confirmButton.addEventListener('click', () => {
                    checkNamePassUser(fieldName.value, fieldPass.value, confirmButton, inputNameArr);
                });
            });

            function checkNamePassUser(name, pass, confirmButton, inputNameArr) {
                // Create a data object with the collected information
                const data = {
                    nickname: name,
                    password: pass
                };

                // Send a request to validate the user
                fetch('http://localhost:8080/WebProject/validate-player', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => response.text())
                    .then(result => {
                        if (result == "1") {
                            console.log(inputNameArr);
                            console.log(inputNameArr[0].value);
                            if (inputNameArr.length > 1 && inputNameArr[0].value.trim() === inputNameArr[1].value.trim()) {
                                // User exists, confirm the data
                                window.alert('El usuario ' + name + ' ya está identificado!');
                            } else if (inputNameArr.length == 1) {
                                confirmData(name);
                            } else {
                                confirmData(name);
                                console.log(names);
                            }
                        } else {
                            // User does not exist
                            window.alert('Jugador no existe!');
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
                return names;

                function confirmData() {
                    if (contains(document.querySelector('#user1'), confirmButton)) {
                        names[0] = name;
                    } else if (contains(document.querySelector('#user2'), confirmButton)) {
                        names[1] = name;
                    }
                    confirmButton.disabled = true;
                    window.alert('Jugador confirmado con éxito!');
                }
            }
        }

        // Handle new player buttons and cancel buttons
        for (let i = 0; i < newPlayerButtons.length; i++) {
            // Handle new player button click
            newPlayerButtons[i].addEventListener('click', () => {
                const textPlayer = document.createElement('span');
                textPlayer.setAttribute('class', 'textPlayer' + (i + 1));
                if (typeof (document.querySelector('#nameUser' + (i + 1))) != 'undefined' && document.querySelector('#nameUser' + (i + 1)) != null) {
                    textPlayer.textContent = 'Se ha identificado como "Jugador ' + (i + 1) + '"';
                    document.querySelector('#nameUser' + (i + 1)).appendChild(textPlayer);
                    identifyButtons[i].style.display = 'none';
                    newPlayerButtons[i].style.display = 'none';
                    cancelNewPlayerButtons[i].style.display = 'initial';

                    names[i] = 'Jugador' + (i + 1);
                    console.log(names);
                } else {
                }
            });

            // Handle cancel button click
            cancelNewPlayerButtons[i].addEventListener('click', () => {
                if (typeof (document.querySelector('#nameUser' + (i + 1))) != 'undefined' && document.querySelector('#nameUser' + (i + 1)) != null) {
                    document.querySelector('.textPlayer' + (i + 1)).remove();
                } else {

                }
                cancelNewPlayerButtons[i].style.display = 'none';
                newPlayerButtons[i].style.display = 'initial';
                identifyButtons[i].style.display = 'initial';
            });
        }

        // Handle result button click
        document.querySelector('.btn-result').addEventListener('click', () => {
            if (gamesPlayed.length > 0) {
                sessionStorage.setItem("gamesPlayed", JSON.stringify(gamesPlayed));
                window.location.replace("../resolucion/resolucion.jsp");
            } else {
                window.alert('No se ha jugado ningun partido aún!');
            }
        });

        // Function to check if a parent contains a child element
        function contains(parent, child) {
            return parent !== child && parent.contains(child);
        }

        // Handle back button click
        document.querySelector('.btn-back').addEventListener('click', () => {
            window.location.replace("http://localhost:8080/WebProject/index.jsp");
        });
    });