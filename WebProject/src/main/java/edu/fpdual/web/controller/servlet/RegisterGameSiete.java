package edu.fpdual.web.controller.servlet;

import edu.fpdual.web.service.GameSieteService;
import edu.fpdual.web.service.dto.GameSiete;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Servlet que registra en la BBDD los datos de las partidas de siete y medio.
 * Se usa en "sieteJuego/juego.jsp".
 */
@WebServlet(name="RegisterGameSiete", urlPatterns = "/register-game-siete")
public class RegisterGameSiete extends HttpServlet {

    /**
     * Servicio del siete y medio.
     */
    private GameSieteService gameSieteService;

    /**
     * Inicializa gameSieteService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.gameSieteService = new GameSieteService();
    }

    /**
     * Recuperar los parámetros enviados a través de un form, construye el objeto a partir
     * de los mismos y llama al método del servicio para registarlos
     * En caso de haber un error, se redirigirá a la página de error.
     * @param req - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * El juego que se va a registrar.
             */
            GameSiete gameSiete = GameSiete.builder()
                    .player1(req.getParameter("player1"))
                    .player2(req.getParameter("player2"))
                    .player3(req.getParameter("player3"))
                    .dealer(req.getParameter("dealer"))
                    .player1score(Float.parseFloat(req.getParameter("player1score")))
                    .player2score(Float.parseFloat(req.getParameter("player2score")))
                    .player3score(Float.parseFloat(req.getParameter("player3score")))
                    .dealerScore(Float.parseFloat(req.getParameter("dealerScore")))
                    .player1bet(Float.parseFloat(req.getParameter("player1bet")))
                    .player2bet(Float.parseFloat(req.getParameter("player2bet")))
                    .player3bet(Float.parseFloat(req.getParameter("player3bet")))
                    .build();
            String result = this.gameSieteService.registerGame(gameSiete);
            req.getSession().setAttribute("gameSieteRegistered", true);
            if (result.equals("1")) {
                resp.sendRedirect("/WebProject/sieteMedio/resolucion/resolucion.jsp");
            } else {
                resp.sendRedirect("/WebProject/error/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/WebProject/error/error.jsp");
        }
    }
}