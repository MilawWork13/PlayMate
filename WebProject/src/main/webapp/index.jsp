<%@ page contentType="text/html; charset=UTF-8" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Play Mate</title>

    <link href="indexStyle.css" rel="stylesheet" type="text/css" />
    <link rel="icon" href="resources/logoPlayMate.png" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/a9cd6825c3.js" crossorigin="anonymous"></script>
  </head>

  <body>
    <section>
      <!-- Navigation bar -->
      <nav class="navbar sticky-top navbar sticky-navbar">
        <div class="container">
          <!-- Brand logo and name -->
          <a class="navbar-brand me-5" href="index.jsp">
            <img src="resources/logoPlayMate.png" alt="" width="100" height="100" class="d-inline-block">
            <span class="fs-5 fw-bold">Play</span><span class="fw-bold fs-5"
              style="color: hsl(219, 100%, 50%)">MATE</span>
          </a>
          <div class="justify-content-center">
            <ul class="nav nav-pills">
              <!-- Dropdown menu for user-related actions -->
              <li class="nav-item dropdown me-5">
                <a class="nav-link dropdown-toggle fw-bold" data-bs-toggle="dropdown" href="#" role="button"
                  aria-expanded="false"><i class="fa-solid fa-user fa-xl" style="color: #005eff;"></i>
                  Área de
                  usuario</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="player/registro/registro.jsp">Registrar Usuario</a></li>
                  <li><a class="dropdown-item" href="player/modificar/modificar.jsp">Modificar contraseña</a></li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li><a class="dropdown-item" href="player/borrar/borrar.jsp">Eliminar usuario</a></li>
                </ul>
              </li>
              <!-- Dropdown menu for game options -->
              <li class="nav-item dropdown me-5 fw-bold">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                  aria-expanded="false"><i class="fa-solid fa-gamepad fa-xl" style="color: #005eff;"></i> Juegos</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="sieteMedio/setup/setup.jsp">Siete y Medio</a></li>
                  <li><a class="dropdown-item" href="cuatroRaya/juego/juego.jsp">Cuatro en raya</a></li>
                </ul>
              </li>
              <!-- Dropdown menu for searching and ranking options -->
              <li class="nav-item dropdown me-5 fw-bold">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                  aria-expanded="false"><i class="fa-solid fa-magnifying-glass" style="color: #005eff;"></i> Buscar
                  partidos</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="sieteMedio/busqueda/busqueda.jsp">Partidas de Siete y medio</a>
                  </li>
                  <li><a class="dropdown-item" href="cuatroRaya/busqueda/busqueda.jsp">Partidas de Cuatro en raya</a>
                  </li>
                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li><a class="dropdown-item" href="ranking/ranking.jsp">Ranking de jugadores</a></li>
                </ul>
              </li>
            </ul>
          </div>
        </div> <!-- Close the navbar-collapse div here -->
      </nav>
      <div class="container mainSection">
        <div class="welcomeText text-center my-5">
          <h1>Bienvenido a PlayMATE</h1>
          <p>Donde la diversión no tiene edad</p>
        </div>

        <div class="row row-cols-1 row-cols-md-3 g-4">
          <!-- Card for game "Siete y medio" -->
          <div class="col">
            <div class="card">
              <img src="resources/sieteymedioIMG.png" class="card-img-top" alt="sieteMedio">
              <div class="card-body">
                <h5 class="card-title text-center"></h5>
                <h5 class="card-title text-center">Siete y medio</h5>
                <p>Una variante del Blackjack español. </p>
                <span><strong>Objetivo: </strong>Obtener una mano con un valor cercano
                  a 7,5 sin pasarse. Baraja española de 40 cartas. Cartas numeradas valen su valor nominal, figuras
                  valen medio punto. As vale 1 u 11 puntos. Estrategia y azar. ¡Diviértete jugando!</span>
              </div>
            </div>
          </div>
          <!-- Card for game "Cuatro en raya" -->
          <div class="col">
            <div class="card">
              <img src="resources/4rayaIMG.png" class="card-img-top" alt="4raya">
              <div class="card-body">
                <h5 class="card-title text-center">Cuatro en raya</h5>
                <p>Un clásico juego de estrategia. </p>
                <span><strong>Objetivo: </strong>Alinear cuatro fichas del mismo color en
                  horizontal, vertical o diagonal. Tablero de 6 filas y 7 columnas. Turnos alternados. ¡Demuestra tu
                  habilidad y gana el juego!</span>
              </div>
            </div>
          </div>
          <!-- Card for upcoming games -->
          <div class="col">
            <div class="card">
              <img src="resources/moreGames.jpeg" class="card-img-top" alt="masJuegos">
              <div class="card-body">
                <h5 class="card-title text-center">Nuevos juegos pronto!</h5>
                <p class="card-text">Nuestra página aún está en desarollo y pronto tendrá nuevos juegos y herramientas
                  para divertirte con la familia o tus amigos.</p>
              </div>
            </div>
          </div>
        </div>

        <div class="lastLine my-5">

        </div>
      </div>


    </section>
    <!-- Link to custom JavaScript file -->
    <script src="index.js"></script>
    <!-- Bootstrap and Popper.js scripts -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
      integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
      integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
      crossorigin="anonymous"></script>
  </body>

  </html>