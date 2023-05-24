<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>CUATRO EN RAYA</title>
    <link href="juego.css" rel="stylesheet" type="text/css" />
    <link rel="icon" href="../images/4raya_logo.png" type="image/x-icon" />
</head>


<body>
    <section>
        <div class="container">
            
            <!-- Title for the page -->
            <div class="title">
                <h1>cuatro en raya</h1>
            </div>

            <div class="cardsContainer">
                <!-- Card for Player 1 -->
                <div class="card">
                    <div class="card-body" id="user1">
                        <h5 class="card-title1">JUGADOR 1</h5>
                        <div id="nameUser1">
                            <!-- Identification for Player 1 will be displayed here -->
                        </div>
                        <button type="button" class="btn-danger" style="display: none;">Cancelar</button>
                        <button type="button" class="btn-classic">Jugador nuevo</button>
                        <button type="button" class="btn-secondary">Identificarse</button>
                    </div>
                </div>
                <div class="card-separator">
                    <span class="turn">Turno de:</span>
                    <span class="status"></span>
                </div>
                <!-- Card for Player 2 -->
                <div class="card">
                    <div class="card-body" id="user2">
                        <h5 class="card-title2">JUGADOR 2</h5>
                        <div id="nameUser2">
                            <!-- Identification for Player 2 will be displayed here -->
                        </div>
                        <button type="button" class="btn-danger" style="display: none;">Cancelar</button>
                        <button type="button" class="btn-classic">Jugador nuevo</button>
                        <button type="button" class="btn-secondary">Identificarse</button>
                    </div>
                </div>
            </div>

            <div class="game-board">
                <!-- The game board will be displayed here -->
            </div>

            <div class="footer">
                <button class="reset">Empezar de nuevo</button>
                <button class="btn-result">Ver resultados</button>
                <button class="btn-back">Volver al inicio</button>
            </div>
        </div>
    </section>

    <script src="juego.js"></script>
</body>

</html>