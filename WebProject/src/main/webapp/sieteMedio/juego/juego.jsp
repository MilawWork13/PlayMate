<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Juego de las siete y media, para 3 jugadores, almacenando las partidas, y con una gestión de consultas de las partidas por jugadores
    Se han de almacenar las partidas, y debe incluir una gestión de consulta de partidas por jugadores -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" href="../../resources/sieteymedio.png" type="image/x-icon" />
    <title>Siete y medio</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link href="juego.css" rel="stylesheet" />
  </head>
  <body>

    <section class="overflow-hidden">
      <div class="container">
          <div class=" align-items-center text-center">
              <div style="z-index: 10;">
                  <h1 class="my-lg-4 display-5 fw-bold ls-tight" style="color: hsl(0, 0%, 0%)">
                      <img src="../../resources/sieteymedio.png" class="img-fluid me-3" alt="Responsive image">SIETE
                      <span style="color: hsl(221, 100%, 50%)">Y</span>
                      <span style="color: hsl(0, 0%, 0%)">MEDIO</span>
                  </h1>
              </div>
          </div>
      </div>
      <div class="row my-4" style="margin-top: -10px;">
          <h4 class="justify-content-center text-center" id="turno">
            <!-- Placeholder for displaying the current player's turn -->
          </h3>
      </div>
      <div class="row justify-content-center mb-2">
          <div class="btn btn-primary col-sm-3 mx-5" id="bocarriba">CARTA BOCA ARRIBA</div>
          <div class="btn btn-primary col-sm-3 mx-5" id="bocabajo">CARTA BOCA ABAJO</div>
          <div class="btn btn-primary col-sm-3 mx-5" id="plantarse">PLANTARSE</div>
      </div>
      <div class="row justify-content-center my-5" id="cartas"></div>
  </section>


    <form id="registerData" action="/WebProject/register-game-siete" method="POST">
      <!-- Hidden input fields for storing player names, scores, and bets -->
      <input type="hidden" name="player1" value="">
      <input type="hidden" name="player2" value="">
      <input type="hidden" name="player3" value="">
      <input type="hidden" name="dealer" value="">
      <input type="hidden" name="player1score" value="">
      <input type="hidden" name="player2score" value="">
      <input type="hidden" name="player3score" value="">
      <input type="hidden" name="dealerScore" value="">
      <input type="hidden" name="player1bet" value="">
      <input type="hidden" name="player2bet" value="">
      <input type="hidden" name="player3bet" value="">
    </form>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script type="text/javascript" src="juego.js"></script>

  </body>
</html>