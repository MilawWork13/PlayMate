package edu.fpdual.web.controller.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fpdual.web.service.GameRayaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "BusquedaRaya", urlPatterns = "/busqueda-raya")
public class BusquedaRaya extends HttpServlet {

    private GameRayaService gameRayaService;

    @Override
    public void init() throws ServletException {
        this.gameRayaService = new GameRayaService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String requestBody = req.getParameter("name");
            if (requestBody == null || requestBody.isEmpty()) {
                resp.setContentType("text/plain");
                resp.getWriter().write("0");
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseMap = this.gameRayaService.ranking(requestBody);
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
