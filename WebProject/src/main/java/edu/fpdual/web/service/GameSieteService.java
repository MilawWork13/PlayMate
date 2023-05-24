package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameSieteClient;
import edu.fpdual.web.service.dto.GameSiete;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GameSieteService {

    private GameSieteClient gameSieteClient;

    public GameSieteService() {
        this.gameSieteClient = new GameSieteClient(ClientBuilder.newClient());
    }

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

    public String registerGame(GameSiete game) {
        Response response = this.gameSieteClient.insertGame(game);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

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
