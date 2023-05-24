package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.GameRayaService;
import edu.fpdual.web.service.dto.GameRaya;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Servlet que registra en la BBDD los datos de las partidas de cuatro en raya.
 * Se usa en "cuatroRaya/juego/juego.jsp".
 */
@WebServlet(name = "RegisterGameRaya", urlPatterns = "/register-game-raya")
public class RegisterGameRaya extends HttpServlet {

    /**
     * Servicio del cuatro en raya.
     */
    private GameRayaService gameRayaService;

    /**
     * Inicializa gameRayaService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.gameRayaService = new GameRayaService();
    }

    /**
     * Recibe el juego en formato JSON, lo deserializa en su objeto correspondiente
     * y llama al servicio de registrar juego par añadirlo a la BBDD.
     * En caso de haber un error, se asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     *
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
             * Jugador recibido
             */
            GameRaya gameRaya = mapper.readValue(req.getReader(), GameRaya.class);
            String result = this.gameRayaService.registerGame(gameRaya);
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}
