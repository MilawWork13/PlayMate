package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.PlayerService;
import edu.fpdual.web.service.dto.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Servlet que realiza un cambio de contraseña de un jugador en la BBDD.
 * Se usa en "/userModificar/modificar.jsp"
 */
@WebServlet(name = "CambiarContraseña", urlPatterns = "/cambiar-contrasena")
public class ContraseñaCambiar extends HttpServlet {

    /**
     * Servicio del jugador.
     */
    private PlayerService playerService;

    /**
     * Inicializo el servicio de jugador.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService();
    }

    /**
     * Deserializa los datos en un objeto Player, que tiene el email del usuario y la nueva contraseña
     * que se va a modificar en la BBDD. Finalmente, se llama al servicio de Player para realizar la operación
     * DML pertinente, que, dependiendo de su resoulición, devolverá una respuesta que será devuelta al cliente.
     * En caso de haber error, se asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     * @param req - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            /**
             * El objeto Player con la nueva contraseña
             */
            Player cambioContraseña = mapper.readValue(req.getReader(), Player.class);
            resp.setContentType("text/plain");
            resp.getWriter().write(this.playerService.cambiarContraseña(cambioContraseña));
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace();
        }

    }
}
