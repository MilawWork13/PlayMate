package edu.fpdual.web.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import edu.fpdual.web.service.dto.GameSiete;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PdfItext {

    public void createPDF(String fileName, String text, List<GameSiete> gameData) throws IOException, DocumentException, URISyntaxException {

        Path logoURL = Paths.get(ClassLoader.getSystemResource("logoPlayMate.png").toURI());

        Document document = new Document();
        //PdfWriter pdfWriter =
        PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
        //Forma de encriptacion de fichero previo a su creacion
        // pdfWriter.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);

        document.open();

        Paragraph paragraph = createParagraph(text);
        Image logoIMG = createImage(logoURL, 5);

        PdfPTable pdfPTable = new PdfPTable(12);
        pdfPTable.setSpacingBefore(5f);
        addTableHeaders(pdfPTable);
        addTableSimpleRows(pdfPTable, gameData);
        addTableCustomRows(pdfPTable, gameData);

        document.add(paragraph);
        //document.add(Chunk.NEWLINE);
        document.add(logoIMG);
        document.add(pdfPTable);
        document.close();

        //Forma de encriptacion de fichero posterior a su creacion
        //PdfReader pdfReader = new PdfReader(fileName + ".pdf");
        //PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(fileName + "_encrypted.pdf"));
        //pdfStamper.setEncryption("user".getBytes(), "1234".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);
        //pdfStamper.close();
    }

    private Image createImage(Path path, float scale) throws BadElementException, IOException {
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scalePercent(scale);
        return image;
    }

    private Paragraph createParagraph(String text) {
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.RED);
        //Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(1f);
        return paragraph;
    }

    private void addTableHeaders(PdfPTable pdfPTable) {
        Stream.of("Jugador 1", "Jugador 2", "Jugador 3", "Banca", "Puntuacion J1", "Puntuacion J2",
                        "Puntuacion J3", "Puntuacion Banca", "Apuesta J1", "Apuesta J2", "Apuesta J3", "Fecha de partida")
                .forEach(nombreColumna -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.GREEN);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(nombreColumna));
                    pdfPTable.addCell(header);
                });
    }


    private void addTableSimpleRows(PdfPTable pdfPTable, List<GameSiete> gameData) throws URISyntaxException, BadElementException, IOException {

        gameData.forEach(gameSiete -> {
            pdfPTable.addCell(gameSiete.getPlayer1());
            pdfPTable.addCell(gameSiete.getPlayer2());
            pdfPTable.addCell(gameSiete.getPlayer3());
            pdfPTable.addCell(gameSiete.getDealer());
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer1score()));
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer2score()));
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer3score()));
            pdfPTable.addCell(Float.toString(gameSiete.getDealerScore()));
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer1bet()));
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer2bet()));
            pdfPTable.addCell(Float.toString(gameSiete.getPlayer3bet()));
            pdfPTable.addCell(gameSiete.getTimestamp().toString());
        });
    }

    private void addTableCustomRows(PdfPTable pdfPTable, List<GameSiete> gameData) throws URISyntaxException, BadElementException, IOException {

        gameData.forEach(gameSiete -> {
            PdfPCell columnPlayer1 = new PdfPCell(new Phrase(gameSiete.getPlayer1()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer1);
            PdfPCell columnPlayer2 = new PdfPCell(new Phrase(gameSiete.getPlayer2()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer2);
            PdfPCell columnPlayer3 = new PdfPCell(new Phrase(gameSiete.getPlayer3()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer3);
            PdfPCell columnDealer = new PdfPCell(new Phrase(gameSiete.getDealer()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnDealer);
            PdfPCell columnPlayer1Score = new PdfPCell(new Phrase(gameSiete.getPlayer1score()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer1Score);
            PdfPCell columnPlayer2Score = new PdfPCell(new Phrase(gameSiete.getPlayer2score()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer2Score);
            PdfPCell columnPlayer3Score = new PdfPCell(new Phrase(gameSiete.getPlayer3score()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer3Score);
            PdfPCell columnDealerScore = new PdfPCell(new Phrase(gameSiete.getDealerScore()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnDealerScore);
            PdfPCell columnPlayer1Bet = new PdfPCell(new Phrase(gameSiete.getPlayer1bet()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer1Bet);
            PdfPCell columnPlayer2Bet = new PdfPCell(new Phrase(gameSiete.getPlayer2bet()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer2Bet);
            PdfPCell columnPlayer3Bet = new PdfPCell(new Phrase(gameSiete.getPlayer3bet()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnPlayer3Bet);
            PdfPCell columnTimestamp = new PdfPCell(new Phrase(gameSiete.getTimestamp().toString()));
            columnPlayer1.setBackgroundColor(BaseColor.MAGENTA);
            columnPlayer1.setBorderWidth(1);
            columnPlayer1.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnPlayer1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPTable.addCell(columnTimestamp);
        });
    }

    /*public static void main(String[] args) throws DocumentException, IOException, URISyntaxException {

        List<GameSiete> gameData = Arrays.asList(new GameSiete("1", "2", "3", "B", 1, 2, 3, 4, 11, 22, 33, new Date(2020, 11, 23))
        , new GameSiete("1", "2", "3", "B", 1, 2, 3, 4, 11, 22, 33, new Date(2020, 11, 23)));

        new PdfItext().createPDF("PartidasSieteMedio", "Resultados de búsqueda para el usuario: a fecha: ", gameData);
    }*/

}
