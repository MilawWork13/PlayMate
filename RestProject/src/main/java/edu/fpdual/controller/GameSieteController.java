package edu.fpdual.controller;

import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.GameSieteManagerImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import edu.fpdual.api.dto.GameSiete;
import edu.fpdual.service.GameSieteService;

import java.util.List;

import java.sql.SQLException;


/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Clase que contiene la API para gestionar la BBDD del siete y medio.
 * Se accede a sus métodos mediante la URL "/RestProject/api/gameSiete"
 */
@Path("/gameSiete")
public class GameSieteController {

    /**
     * Servicio del siete y medio, que media entre el controlador y la
     * capa de peristencia.
     */
    private GameSieteService gameSieteService;

    /**
     * Constructor de la clase, que inicializa el servicio
     */
    public GameSieteController() {
        this.gameSieteService = new GameSieteService(new MySQLConnector(), new GameSieteManagerImpl());
    }

    /**
     * Realiza una operación insert que introduce una partida en la BBDD.
     *
     * @param gameSiete - GameSiete - Juego de siete y medio a registrar
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
    public Response insertGame(GameSiete gameSiete) throws SQLException, ClassNotFoundException {

        int result = gameSieteService.insertGame(gameSiete);
        if (result > 0) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.serverError().build();
        }
    }

    /**
     * Realiza una consulta select en la BBDD que devuelve una lista de GameSiete
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
    public Response findByName(@QueryParam("nickname") String nickname) throws SQLException, ClassNotFoundException {

        List<GameSiete> listado = gameSieteService.findGameByName(nickname);
        return Response.ok().entity(listado)
                .status(Response.Status.CREATED).build();
    }
}