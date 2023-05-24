<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../resources/sieteymedio.png" type="image/x-icon" />
    <link href="setup.css" rel="stylesheet" type="text/css" />
    <title>Configuración de la partida</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>

<body>

    <div class=".container-fluid" id="containerToGet">

        <div class="row gx-lg-5 align-items-center mb-2 text-center">
            <div style="z-index: 10;">

                <!-- Game title -->
                <h1 class="my-lg-4 display-5 fw-bold ls-tight" style="color: hsl(0, 0%, 0%)">
                    <img src="../../resources/sieteymedio.png" class="img-fluid me-3" alt="Responsive image">SIETE
                    <span style="color: hsl(221, 100%, 50%)">Y</span>
                    <span style="color: hsl(0, 0%, 0%)">MEDIO</span>
                </h1>
            </div>
        </div>
        <div class="row my-4">

            <!-- Page title -->
            <h2 id="titulo" class="text-center ">Configuración de la partida</h2>
        </div>
        <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-body">

                        <!-- Player 1 configuration -->
                        <h5 class="card-title text-center">JUGADOR 1</h5>
                        <div class="row d-flex justify-content-center">
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="1" name="setup1" id="jugarSin">
                                    <label class="form-check-label" for="jugarSin">
                                        Jugar sin usuario
                                    </label>
                                </div>
                            </div>
    
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="1" name="setup1" id="jugarCon">
                                    <label class="form-check-label" for="jugarCon">
                                        Jugar con usuario registrado
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="input1">
                            <!-- Placeholder for player 1 input -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- Repeat the same structure for player 2, 3, and 4 -->
            <div class="col-3">
                <div class="card">
                    <div class="card-body ">
                        <h5 class="card-title text-center">JUGADOR 2</h5>
                        <div class="row d-flex justify-content-center">
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="2" name="setup2" id="jugarSin">
                                    <label class="form-check-label" for="jugarSin">
                                        Jugar sin usuario
                                    </label>
                                </div>
                            </div>
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="2" name="setup2" id="jugarCon">
                                    <label class="form-check-label" for="jugarCon">
                                        Jugar con usuario registrado
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="input2">
                            <!-- Placeholder for player 2 input -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- Repeat the same structure for player 3 and 4 -->
            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-center">JUGADOR 3</h5>
                        <div class="row d-flex justify-content-center">
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="3" name="setup3" id="jugarSin">
                                    <label class="form-check-label" for="jugarSin">
                                        Jugar sin usuario
                                    </label>
                                </div>
                            </div>
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="3" name="setup3" id="jugarCon">
                                    <label class="form-check-label" for="jugarCon">
                                        Jugar con usuario registrado
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="input3">
                            <!-- Placeholder for player 3 input -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-center">JUGADOR 4</h5>
                        <div class="row d-flex justify-content-center">
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="4" name="setup4" id="jugarSin">
                                    <label class="form-check-label" for="jugarSin">
                                        Jugar sin usuario
                                    </label>
                                </div>
                            </div>
                            <div class="col my-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" value="4" name="setup4" id="jugarCon">
                                    <label class="form-check-label" for="jugarCon">
                                        Jugar con usuario registrado
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="input4">
                            <!-- Placeholder for player 4 input -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-center">
            
            <!-- Buttons to start the game or go back to the homepage -->
            <button type="submit" class="btn btn-primary col-2 my-5" id="botonComenzar">Comenzar partida</button>
            <div class="col-1"></div>
            <button type="submit" class="btn btn-secondary col-2 my-5">Volver al inicio</button>
        </div>
    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    <script src="setup.js"></script>
</body>

</html>