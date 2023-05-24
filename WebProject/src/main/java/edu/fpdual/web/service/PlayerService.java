package edu.fpdual.web.service;

import edu.fpdual.web.email.VerificationCodeGenerator;
import edu.fpdual.web.email.sender.Sender;
import edu.fpdual.web.service.client.PlayerClient;
import edu.fpdual.web.service.dto.Player;
import edu.fpdual.web.service.dto.SolicitudContraseña;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import lombok.Setter;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase de servicio de Player, que actúa de intermediaria entre el cliente y los servlets,
 * aplicando cualquier lógica que no se pueda encontrar dentro de estos.
 */
@Setter
public class PlayerService {

    /**
     * Cliente de Player
     */
    private PlayerClient playerClient;
    /**
     * Clase para generar el código de autorización de cambio de contraseña
     * que se envia al usuario por email.
     */
    private VerificationCodeGenerator verificationCodeGenerator;

    /**
     * Constructo de la clase, en la que se inicializan sus atributos
     */
    public PlayerService() {
        this.playerClient = new PlayerClient(ClientBuilder.newClient());
        this.verificationCodeGenerator = new VerificationCodeGenerator();
    }

    /**
     * Método que controla el borrado de un jugador de la BBDD. Primero busca si el jugador existe,
     * y si son válidas las credenciales aportadas. Tras ello, se procede a su borrado.
     *
     * @param player - Player - Jugador a borrar
     * @return - String - Resultado que determina el comportamiento del cliente según determinadas
     * condiciones que se den
     */
    public String deletePlayer(Player player) {

        String result = "0";

        Player playerFound = this.playerClient.findPlayer(player.getNickname(), player.getEmail());
        if (playerFound != null) {
            if (player.getNickname().equals(playerFound.getNickname()) && player.getPassword().equals(playerFound.getPassword())) {
                result = "1";
            } else {
                result = "0";
            }
        }
        if (result.equals("1")) {
            Response response = this.playerClient.deletePlayer(player);
            if (response.getStatus() == 201) {
                result = "1";
            } else {
                result = "0";
            }
        }

        return result;
    }

    /**
     * Método para insertar un jugador. Primero comprueba si existe, y si es así
     *
     * @param player - Player - Jugador a insertar
     * @return - String - Resultado que determina el comportamiento de la página según determinadas
     * condiciones que se den. <u>
     * <li>0: Operación correcta</li>
     * <li>1: Jugador con ese nombre ya existe</li>
     * <li>2: Jugador con ese email ya existe</li>
     * <li>3: Error en la operación</li>
     * </u>
     */
    public String insertPlayer(Player player) {

        String result = "0";
        Player playerFound = this.playerClient.findPlayer(player.getNickname(), player.getEmail());
        if (playerFound != null) {
            if (player.getNickname().equals(playerFound.getNickname())) {
                result = "1";
            } else if (player.getEmail().equals(playerFound.getEmail())) {
                result = "2";
            }
        }
        if (result.equals("0")) {
            Response response = this.playerClient.insertPlayer(player);
            if (response.getStatus() == 201) {
                return "0";
            } else {
                return "3";
            }
        }
        return result;
    }

    /**
     * Método que crea la sesión que se utilice para controlar el proceso de cambio
     * de contraseña.
     *
     * @param email - String - Correo del jugador
     * @return SolicitudContraseña - Objeto que gestiona la solicitud de cambio de contraseña.
     */
    public SolicitudContraseña crearSesionCambiarContraseña(String email) {

        Player playerFound = this.playerClient.findPlayer("", email);
        if (playerFound == null) {
            return null;
        } else if (playerFound.getEmail().equals(email)) {
            SolicitudContraseña solicitudContraseña = new SolicitudContraseña();
            solicitudContraseña.setEmail(email);
            String codigo = this.verificationCodeGenerator.generateVerificationCode();
            solicitudContraseña.setCode(codigo);
            new Sender().send("just.alvaroo@gmail.com", email, "Cambiar contraseña",
                    "<h3>Su clave para cambiar la contraseña es: " + codigo);
            return solicitudContraseña;
        } else {
            return null;
        }
    }

    /**
     * Método que comprueba el código de autorización de cambio de contraseña
     *
     * @param solicitudContraseña - SolicitudContraseña - La solicitud de contraseña que contiene el código
     * introducido por el jugador
     * @param solicitudPresente - SolicitudContraseña - La solicitud de contraseña que contiene el código generado
     * @return String - Respuesta que determinará el comportamiento de la página.
     */
    public String comprobarCodigo(SolicitudContraseña solicitudContraseña, SolicitudContraseña solicitudPresente) {

        if (solicitudContraseña.getCode().equals(solicitudPresente.getCode())) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Método que se encarga de gestionar el cambio de contraseña de un jugador con el cliente.
     *
     * @param cambioContraseña - Player - Jugador cuya contraseña es la que será nueva.
     * @return - String - Respuesta que determinará el comportamiento de la página.
     */
    public String cambiarContraseña(Player cambioContraseña) {

        Response response = this.playerClient.updatePassword(cambioContraseña);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Método que se encarga de validar las credenciales de un jugador.
     *
     * @param player - Player - Jugador cuyas credenciales se van a verficiar
     * @return - String - Respuesta que determinará el comportamiento de la página.
     */
    public String validatePlayer(Player player) {

        Player playerFound = this.playerClient.findPlayerByName(player.getNickname());

        if (playerFound != null && (player.getNickname().equals(playerFound.getNickname())
                && player.getPassword().equals(playerFound.getPassword()))) {
            return "1";
        } else {
            return "0";
        }
    }

}