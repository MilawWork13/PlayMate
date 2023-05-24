package edu.fpdual.controller;

import edu.fpdual.api.dto.GameRaya;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.GameRayaManagerImpl;
import edu.fpdual.service.GameRayaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;


/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Clase que contiene la API para gestionar la BBDD del juego de 4 en raya.
 * Se accede a sus métodos mediante la URL "/RestProject/api/gameRaya"
 */
@Path("/gameRaya")
public class GameRayaController {

    /**
     * Servicio del 4 en raya, que media entre el controlador y la
     * capa de peristencia.
     */
    private GameRayaService gameRayaService;

    /**
     * Constructor de la clase, que inicializa el servicio
     */
    public GameRayaController() {
        this.gameRayaService = new GameRayaService(new MySQLConnector(), new GameRayaManagerImpl());
    }

    /**
     * Realiza una operación insert que introduce una partida en la BBDD.
     *
     * @param gameRaya - GameRaya - Partida de 4 en raya a registrar
     * @return <u>
     * <li>Response.status(Response.Status.CREATED) - Operación exitosa</li>
     * <li>Response.serverError() - Operación fallida</li>
     * </u>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @POST
    @Path("/insertGame")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertGame(GameRaya gameRaya) throws SQLException, ClassNotFoundException {

        int result = gameRayaService.insertGame(gameRaya);
        if (result > 0) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.serverError().build();
        }
    }

    /**
     * Realiza una consulta select en la BBDD que devuelve una lista de GameRaya
     * que contengan el nombre aportado en el QueryParam.
     *
     * @param nickname - String - QueryParam
     * @return - Response con el listado de partidas.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @GET
    @Path("/findByName")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGameByName(@QueryParam("nickname") String nickname) throws SQLException, ClassNotFoundException {

        List<GameRaya> listado = gameRayaService.findGameByName(nickname);
        return Response.ok().entity(listado).status(Response.Status.CREATED).build();
    }

}
