const gamesPlayed = JSON.parse(sessionStorage.getItem("gamesPlayed")); // Retrieve the gamesPlayed data from sessionStorage and parse it as JSON
window.addEventListener('DOMContentLoaded', gameResults, false); // Add a DOMContentLoaded event listener to call the gameResults function
console.log(gamesPlayed); // Log the gamesPlayed data to the console

// Function to display game results in a table
function gameResults() {
    const bodyTable = document.getElementById('bodyTable'); // Get the table body element

    gamesPlayed.forEach(game => {
        // Iterate over each game in the gamesPlayed array

        const tr = document.createElement('tr'); // Create a table row element
        bodyTable.appendChild(tr); // Append the row to the table body

        const tdGame = document.createElement('td'); // Create a table data cell element for the game number
        tdGame.setAttribute('scope', 'row');
        tdGame.textContent = "1"; // Set the game number (currently hardcoded as "1")
        tr.appendChild(tdGame); // Append the cell to the row

        const Player1name = document.createElement('td'); // Create a table data cell element for player1 name
        Player1name.textContent = game.player1; // Set the player1 name
        tr.appendChild(Player1name); // Append the cell to the row

        const Player2name = document.createElement('td'); // Create a table data cell element for player2 name
        Player2name.textContent = game.player2; // Set the player2 name
        tr.appendChild(Player2name); // Append the cell to the row

        const winnerNameColor = document.createElement('td'); // Create a table data cell element for the winner name/color
        winnerNameColor.textContent = game.winner; // Set the winner name/color
        tr.appendChild(winnerNameColor); // Append the cell to the row

        const date = document.createElement('td'); // Create a table data cell element for the date
        date.textContent = game.date; // Set the date
        tr.appendChild(date); // Append the cell to the row
    });

    // Clean the session of unnecessary data
    sessionStorage.clear();
}