<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resolucion.css" rel="stylesheet" type="text/css" />
    <link rel="icon" href="../images/4raya_logo.png" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Resultados</title>
</head>

<body>
    <section class="overflow-hidden">
        <div class="container">
            <div class="row gx-lg-5 align-items-center mb-2 text-center">
                
                <!-- Heading -->
                <div class="row">
                    <h5>Se han jugado los siguiente partidos con estos resultados:</h5>
                </div>

                <!-- Table of Results -->
                <div class="row">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Jugador 1</th>
                                <th scope="col">Jugador 2</th>
                                <th scope="col">Ganador</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody id="bodyTable">
                            <!-- Results will be dynamically added here -->
                        </tbody>
                    </table>
                </div>

                <!-- Form and Button -->
                <div class="row">
                    <form action="/WebProject/index.jsp" method="GET">
                        <!-- Button to navigate to the home page -->
                        <button class="btn btn-secondary col-md-1 position-relative col-sm-2 my-4 top-50 start-50 translate-middle">
                            INICIO
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Linking the JavaScript file -->
    <script src="resolucion.js"></script>
</body>

</html>