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
 * Servlet que valida las credenciales introducidas de un jugador.
 * Se usa en "/sieteSetup/setup.jsp" y "rayaSetup/setup.jsp".
 */
@WebServlet(name="ValidatePlayer", urlPatterns = "/validate-player")
public class ValidatePlayer extends HttpServlet {

    /**
     * Servicio del jugador.
     */
    private PlayerService playerService;

    /**
     * Inicializa playerService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService();
    }

    /**
     * Deserializa la petición en un objeto Player, y llama al playerService para validar las credenciales,
     * devolviendo una respuesta que será enviada al cliente en la respuesta para responder de forma pertitnente.
     * En caso de haber un error, se asigna a la respuesta el código de error 500, que
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
            Player player = mapper.readValue(req.getReader(), Player.class);
            resp.setContentType("text/plain");
            resp.getWriter().write(this.playerService.validatePlayer(player));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
