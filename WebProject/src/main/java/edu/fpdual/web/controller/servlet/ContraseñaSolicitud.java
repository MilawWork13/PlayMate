package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.PlayerService;
import edu.fpdual.web.service.dto.SolicitudContraseña;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : Álvaro Terrasa Moya y Artem Korzham
 * @version : 1.0
 * Servlet para crear gestionar una solicitud de cambio de contraseña de un jugador.
 * Se hace uso en "/userModificar/modificar.jsp"
 */
@WebServlet(name = "SolicitudContraseña", urlPatterns = "/solicitud-contrasena")
public class ContraseñaSolicitud extends HttpServlet {

    /**
     * Servicio del jugador.
     */
    private PlayerService playerService;

    /**
     * Inicializo playerService.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.playerService = new PlayerService();
    }

    /**
     * Comprueba mediante el email que el jugador existe,
     * en cuyo caso crea la solicitud, que será un objeto con el email, y el código que se envía al correo
     * para validar la operación. Finalmente, se asigna este objeto como atributo de la sesión.
     * En caso de que haya un error, asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     *
     * @param req - HttpServletRequest
     * @param resp - HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * El email recibido introducido por el jugador
             */
            String email = req.getParameter("email");
            resp.setContentType("text/plain");
            /**
             * Solicitud para el cambio de contraseña
             */
            SolicitudContraseña solicitudContraseña = this.playerService.crearSesionCambiarContraseña(email);
            if (solicitudContraseña == null) {
                resp.getWriter().write("0");
            } else if (solicitudContraseña != null) {
                req.getSession().setAttribute(email, solicitudContraseña);
                resp.getWriter().write("1");
            }
        } catch (Exception e) {
            resp.setStatus(500);
        }

    }

    /**
     * Recibe un JSON que deserializa en un objeto SolicitudContraseña, que contiene el email del usuario y el código
     * introducido por el usuario. Llama al servicio para comprobar si existe una solicitud para el jugador y si el código coincide.
     * En caso de que haya un error, asigna a la respuesta el código de error 500, que
     * redirigirá a la página de error.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("text/plain");
            /**
             * Objeto de solicitud de contraseña para validar con la presente en la sesión
             */
            SolicitudContraseña solicitudContraseña = mapper.readValue(req.getReader(), SolicitudContraseña.class);
            /**
             * Solicitud para el cambio de contraseña ya presente en la sesión
             */
            SolicitudContraseña solicitudPresente = (SolicitudContraseña) req.getSession().getAttribute(solicitudContraseña.getEmail());
            resp.getWriter().write(this.playerService.comprobarCodigo(solicitudContraseña, solicitudPresente));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}
