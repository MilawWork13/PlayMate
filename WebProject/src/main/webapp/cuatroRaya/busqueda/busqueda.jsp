<%@ page contentType="text/html; charset=UTF-8" %>

<!-- The page content type is set to HTML with UTF-8 encoding -->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="busqueda.css" rel="stylesheet" type="text/css" />
    <link rel="icon" href="../images/4raya_logo.png" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Buscar partidos 4Raya</title>
</head>

<body>
    <section>
        <div class="container">
            <div class="row text-center">

                <!-- Logo and title section -->
                <h1 class="my-lg-4 display-7 fw-bold ls-tight" style="color: hsl(0, 0%, 0%)">
                    <img src="../../resources/logoPlayMate.png" class="img-fluid me-3"
                        style="width: 100px; height: 100px;" alt="Responsive image">Play
                    <span style="color: hsl(216, 100%, 50%)">MATE</span>
                </h1>
            </div>
            <div class="row">

                <!-- Heading for game search -->
                <h2 class="text-center my-2">BUSQUEDA DE PARTIDOS <span class="badge bg-primary">4 EN RAYA</span>
                </h2>
            </div>
            <div class="row d-flex justify-content-center">

                <!-- Search input field -->
                <label for="nickname" class="col-sm-2 col-form-label my-5">Nombre</label>
                <div class="col-4">
                    <input type="text" class="nickname form-control my-5" name="nickname"
                        placeholder="Nombre de usuario">
                </div>
            </div>
        </div>
        <div class="row justify-content-center my-2">

            <!-- Search buttons -->
            <button type="button" class="btn btn-primary col-2">Buscar por nombre</button>
            <div class="col-1"></div>
            <button type="button" class="btn btn-secondary col-2">Menú principal</button>
        </div>
        </div>
    </section>
    <section>
        <div class="container text-center my-2">
            <div class="gamesPlayedWonLoss">
                <div id="total">
                    <!-- Placeholder for total games played -->
                </div>
                <div id="wins">
                    <!-- Placeholder for number of wins -->
                </div>
                <div id="loss">
                    <!-- Placeholder for number of losses -->
                </div>
            </div>
            
            <!-- Table for displaying game results -->
            <table class="table ">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Jugador 1</th>
                        <th scope="col">Jugador 2</th>
                        <th scope="col">Ganador</th>
                        <th scope="col">Fecha</th>
                    </tr>
                </thead>
                <tbody class="table-body">
                    <!-- Placeholder for game results -->
                </tbody>
            </table>
        </div>
    </section>

    <script src="busqueda.js"></script>
</body>

</html>