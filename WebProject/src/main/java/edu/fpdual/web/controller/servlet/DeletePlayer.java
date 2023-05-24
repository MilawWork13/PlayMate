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
 * @author : Álvaro Terrasa Moya y Artem Korzham
 * @version : 1.0
 * Servlet para eliminar a un usuario de la base de datos según el nombre y contraseña introducidos.
 * Se hace uso en "/borrar/borrar.jsp"
 */
@WebServlet(name="DeletePlayer", urlPatterns = "/delete-player")
public class DeletePlayer extends HttpServlet {
    /**
     * Servicio del jugador.
     */
    private PlayerService playerService;

    /**
     * Inicializo playerService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException{
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{
            ObjectMapper mapper = new ObjectMapper();
            Player player = mapper.readValue(req.getReader(), Player.class);
            resp.setContentType("text/plain");
            resp.getWriter().write(this.playerService.deletePlayer(player));
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
