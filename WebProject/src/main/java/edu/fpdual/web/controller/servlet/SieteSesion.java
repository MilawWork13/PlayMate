package edu.fpdual.web.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Servlet que crea una sesión para el siete y medio, que junto con su filtro correspondiente
 * evita que se pueda entrar sin antes haber iniciado la partida
 * Se usa en "/sieteSetup/setup.jsp"
 */
@WebServlet(name = "SieteSesion", urlPatterns = "/siete-sesion")
public class SieteSesion extends HttpServlet {

    /**
     * Añade un objeto a la sesión que será comprobado por el filtro para determinar
     * si hay un juego de 4 en raya en curso.
     * @param req - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("sieteSesion", true);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(500);
        }
    }
}
