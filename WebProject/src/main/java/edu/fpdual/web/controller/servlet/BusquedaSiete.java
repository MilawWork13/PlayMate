package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.GameSieteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Servlet para realizar la búsqueda por nombre de partidas de siete y medio almacenadas en la BBDD.
 * Se hace uso en "/sieteBusqueda/busqueda.jsp"
 */
@WebServlet(name = "BusquedaSiete", urlPatterns = "/busqueda-siete")
public class BusquedaSiete extends HttpServlet {

    /**
     * Servicio del siete y medio.
     */
    private GameSieteService gameSieteService;

    /**
     * Inicializo gameSieteService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.gameSieteService = new GameSieteService();
    }

    /**
     * Recibe el nombre del usuario a través de la URL, y comprueba si es nulo o vacío.
     * En caso de serlo, devuelve una respuesta que informará que no se ha encontrado.
     * Si no lo es, llama al método del servicio de Game, que crea un mapa de String y Object,
     * en el cual almaceno la lista de partidas encontradas, y el número de veces que ha ganado el usuario.
     * Este mapa equivale a un JSON, por lo que, si no es nulo, se serializa en String y se manda al
     * cliente para tratarlo. En caso de ser nulo, devuelve una respuesta que manejará el cliente.
     * En caso de haber un error, se asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     *
     * @param req  - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * Nombre recibido para la búsqueda
             */
            String requestBody = req.getParameter("nombre");
            if (requestBody == null || requestBody.isEmpty()) {
                resp.setContentType("text/plain");
                resp.getWriter().write("0");
            }
            ObjectMapper mapper = new ObjectMapper();
            /**
             * Lista con el número de partidas ganadas y las partidas del jugador
             */
            Map<String, Object> responseMap = this.gameSieteService.ranking(requestBody);
            if (responseMap != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(responseMap));
            } else {
                resp.setContentType("text/plain");
                resp.getWriter().write("0");
            }
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace();
        }
    }
}
