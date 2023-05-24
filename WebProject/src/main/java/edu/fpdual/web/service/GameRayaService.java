package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameRayaClient;
import edu.fpdual.web.service.dto.GameRaya;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GameRayaService {

    private GameRayaClient gameRayaClient;

    public GameRayaService() {
        this.gameRayaClient = new GameRayaClient();
    }

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

    public String registerGame(GameRaya game) {
        Response response = this.gameRayaClient.registerGame(game);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

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
