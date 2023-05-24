package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameRayaClient;
import edu.fpdual.web.service.dto.GameRaya;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase de servicio de GameRaya, que actúa de intermediaria entre el cliente y los servlets,
 * aplicando cualquier lógica que no se pueda encontrar dentro de estos.
 */
public class GameRayaService {

    /**
     * Servicio de GameRaya
     */
    private GameRayaClient gameRayaClient;

    /**
     * Constructor de la clase, que inicializa el servicio
     */
    public GameRayaService() {
        this.gameRayaClient = new GameRayaClient();
    }


    /**
     * Método que recoge todas las partidas de cuatro en raya de la BBDD según nombre
     * de jugador introducido. A su vez, proporciona la información para mostrar las
     * partidas totales, ganadas y perdidas de un jugador.
     *
     * @param requestBody - String - Nombre del jugador a buscar
     * @return Map<String, Object> - Información obtenida
     */
    public Map<String, Object> ranking(String requestBody) {
        List<GameRaya> dataRetrieved = this.gameRayaClient.findByName(requestBody);
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
     * Método que registra un juego de cuatro en raya.
     *
     * @param game - GameRaya - Juego a registrar
     * @return String - Respuesta que determinará el comportamiento de la página
     */
    public String registerGame(GameRaya game) {
        Response response = this.gameRayaClient.registerGame(game);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Método que aplica un filtro para determinar cuantas partidas de cuatro en raya ha ganado
     * en total un jugador.
     *
     * @param nickname - String - Nombre del jugador del que se buscan las partidas
     * @param dataRetrieved - String - Lista de las partidas encontradas según el nombre del jugador
     * @return long - El número de partidas ganadas
     */
    public long infoGana(String nickname, List<GameRaya> dataRetrieved) {

        Predicate<GameRaya> predicatesWin = gameRaya -> {

            String winner = gameRaya.getWinner();
            int spaceIndex = winner.indexOf(" ");
            if (spaceIndex != -1) {
                String winnerFirstName = winner.substring(0, spaceIndex);
                return winnerFirstName.equals(nickname);
            }
            return false;
        };

        long count = dataRetrieved.stream()
                .filter(gameRaya -> predicatesWin.test(gameRaya))
                .count();

        return count;
    }

}