package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameSieteClient;
import edu.fpdual.web.service.dto.GameSiete;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase de servicio de GameSiete, que actúa de intermediaria entre el cliente y los servlets,
 * aplicando cualquier lógica que no se pueda encontrar dentro de estos.
 */
public class GameSieteService {

    /**
     * Cliente de GameSiete
     */
    private GameSieteClient gameSieteClient;

    /**
     * Constructor de la clase, que inicializa el cliente de GameSiete
     */
    public GameSieteService() {
        this.gameSieteClient = new GameSieteClient(ClientBuilder.newClient());
    }

    /**
     * Método que recoge todas las partidas de siete y medio de la BBDD según nombre
     * de jugador introducido. A su vez, proporciona la información para mostrar las
     * partidas totales, ganadas y perdidas de un jugador.
     *
     * @param requestBody - String - Nombre del jugador a buscar
     * @return Map<String, Object> - Información obtenida
     */
    public Map<String, Object> ranking(String requestBody) {
        List<GameSiete> dataRetrieved = this.gameSieteClient.findByName(requestBody);
        if (dataRetrieved != null) {
            long winCount = this.infoGana(requestBody, dataRetrieved);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("gameData", dataRetrieved);
            responseMap.put("winCount", winCount);
            return responseMap;
        } else {
            return null;
        }
    }

    /**
     * Método que registra un juego de siete y medio.
     *
     * @param game - GameSiete - Juego a registrar
     * @return String - Respuesta que determinará el comportamiento de la página
     */
    public String registerGame(GameSiete game) {
        Response response = this.gameSieteClient.insertGame(game);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Método que aplica un filtro para determinar cuantas partidas de siete y medio ha ganado
     * en total un jugador.
     *
     * @param nickname - String - Nombre del jugador del que se buscan las partidas
     * @param dataRetrieved - String - Lista de las partidas encontradas según el nombre del jugador
     * @return long - El número de partidas ganadas
     */
    public long infoGana(String nickname, List<GameSiete> dataRetrieved) {

        List<Predicate<GameSiete>> predicatesWin = List.of(
                gameSiete -> gameSiete.getPlayer1().equals(nickname) && ((gameSiete.getDealerScore() > 7.5f && gameSiete.getPlayer1score() < 7.5f) || (gameSiete.getPlayer1score() > gameSiete.getDealerScore())),
                gameSiete -> gameSiete.getPlayer2().equals(nickname) && ((gameSiete.getDealerScore() > 7.5f && gameSiete.getPlayer2score() < 7.5f) || (gameSiete.getPlayer2score() > gameSiete.getDealerScore())),
                gameSiete -> gameSiete.getPlayer3().equals(nickname) && ((gameSiete.getDealerScore() > 7.5f && gameSiete.getPlayer3score() < 7.5f) || (gameSiete.getPlayer3score() > gameSiete.getDealerScore()))

        );

        long count = dataRetrieved.stream()
                .filter(gameSiete -> predicatesWin.stream().anyMatch(predicate -> predicate.test(gameSiete)))
                .count();

        return count;
    }

}