package edu.fpdual.web.controller.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import edu.fpdual.web.pdf.PdfItext;
import edu.fpdual.web.service.dto.GameSiete;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@WebServlet(name="PDFSiete", urlPatterns = "/pdf-siete")
public class PDFSiete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<GameSiete> listaPartidas = mapper.readValue(req.getReader(), new TypeReference<List<GameSiete>>(){});
            new PdfItext().createPDF("PartidasSieteMedio", "Resultados de búsqueda para el usuario: a fecha: ", listaPartidas);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
