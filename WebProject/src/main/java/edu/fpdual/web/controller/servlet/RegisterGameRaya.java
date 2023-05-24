package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.GameRayaService;
import edu.fpdual.web.service.dto.GameRaya;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterGameRaya", urlPatterns = "/register-game-raya")
public class RegisterGameRaya extends HttpServlet {

    private GameRayaService gameRayaService;

    @Override
    public void init() throws ServletException {
        this.gameRayaService = new GameRayaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            GameRaya gameRaya = mapper.readValue(req.getReader(), GameRaya.class);
            String result = this.gameRayaService.registerGame(gameRaya);
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}
