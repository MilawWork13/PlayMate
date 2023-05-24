<%@ page contentType="text/html; charset=UTF-8" %>

  <!doctype html>
  <html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../../resources/logoPlayMate.png" type="image/x-icon" />
    <title>Página de registro de usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>

  <body>
    <div class="container">
      <div class="row text-center">
        <h1 class="my-lg-4 display-7 fw-bold ls-tight" style="color: hsl(0, 0%, 0%)">
          <img src="../../resources/logoPlayMate.png" class="img-fluid me-3" style="width: 100px; height: 100px;"
            alt="Responsive image">Play
          <span style="color: hsl(216, 100%, 50%)">MATE</span>
        </h1>
      </div>
      <div class="row">
        <div class="mb-3 mt-4 row">
          <div class="col-3"></div>
          <label for="nickname" class="col-sm-2 col-form-label">Nombre</label>
          <div class="col-4">
            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nombre de usuario">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="mb-3 mt-4 row">
          <div class="col-3"></div>
          <label for="email" class="col-sm-2 col-form-label">Email</label>
          <div class="col-4">
            <input type="text" class="form-control" id="email" name="email" placeholder="Correo electrónico">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="mb-3 mt-4 row">
          <div class="col-3"></div>
          <label for="password" class="col-sm-2 col-form-label">Contraseña</label>
          <div class="col-4">
            <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-3"></div>
        <div class="col-4 mt-4" style="margin-left: -20px;">
          <button id="botonRegistro" type="submit" class="btn btn-primary" style="width:200px; height:40px">Registrar
            usuario</button>
        </div>
        <div class="col-1 mt-4">
          <form action="/WebProject/index.jsp" method="GET">
            <button type="submit" class="btn btn-secondary" style="width:200px; height:40px">Salir sin añadir</button>
          </form>
        </div>
      </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"></script>
    <script src="registro.js"></script>
  </body>

  </html>