package edu.fpdual.web.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Objeto que representa una partida de siete y medio de la BBDD.
 */
public class GameSiete {

    private String player1;
    private String player2;
    private String player3;
    private String dealer;
    private float player1score;
    private float player2score;
    private float player3score;
    private float dealerScore;
    private float player1bet;
    private float player2bet;
    private float player3bet;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date timestamp;

}
