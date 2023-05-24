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
 * Servlet para registrar jugador.
 * Se hace uso en "userRegistro/registro.jsp"
 */
@WebServlet(name = "AddPlayer", urlPatterns = "/add-player")
public class AddPlayer extends HttpServlet {

    /**
     * Servicio del jugador.
     */
    private PlayerService playerService;

    /**
     * Método init - Inicializa el servicio de jugadores.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService();
    }

    /**
     * Recibe el jugador en formato JSON, lo deserializa en su objeto correspondiente
     * y llama al servicio de jugador para añadirlo a la BBDD.
     * En caso de haber un error, se asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            /**
             * Jugador recibido
             */
            Player player = mapper.readValue(req.getReader(), Player.class);
            resp.setContentType("text/plain");
            resp.getWriter().write(this.playerService.insertPlayer(player));
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace();
        }
    }
}