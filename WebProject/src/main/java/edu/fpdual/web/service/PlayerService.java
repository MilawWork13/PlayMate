package edu.fpdual.web.service;

import edu.fpdual.web.email.VerificationCodeGenerator;
import edu.fpdual.web.email.sender.Sender;
import edu.fpdual.web.service.client.PlayerClient;
import edu.fpdual.web.service.dto.Player;
import edu.fpdual.web.service.dto.SolicitudContraseña;
import jakarta.ws.rs.core.Response;
import lombok.Setter;

@Setter
public class PlayerService {

    private PlayerClient playerClient;
    private VerificationCodeGenerator verificationCodeGenerator;

    public PlayerService() {
        this.playerClient = new PlayerClient();
        this.verificationCodeGenerator = new VerificationCodeGenerator();
    }

    public String deletePlayer(Player player){

        String result = "0";

        Player playerFound = this.playerClient.findPlayer(player.getNickname(), player.getEmail());
        if(playerFound != null){
            if(player.getNickname().equals(playerFound.getNickname()) && player.getPassword().equals(playerFound.getPassword())){
                result = "1";
            }else{
                result= "0";
            }
        }
        if(result.equals("1")){
            Response response = this.playerClient.deletePlayer(player);
            if(response.getStatus() == 201){
                result = "1";
            }else{
                result = "0";
            }
        }

        return result;
    }

    public String insertPlayer(Player player) {

        String result = "0";
        Player playerFound = this.playerClient.findPlayer(player.getNickname(), player.getEmail());
        if (playerFound != null) {
            if (player.getNickname().equals(playerFound.getNickname())) {
                result = "1";
            } else if (player.getEmail().equals(playerFound.getEmail())) {
                result="2";
            }
        }
        if (result.equals("0")) {
            Response response = new PlayerClient().insertPlayer(player);
            if (response.getStatus() == 201) {
                return "0";
            } else {
                return "3";
            }
        }
        return result;
    }

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

    public String comprobarCodigo(SolicitudContraseña solicitudContraseña, SolicitudContraseña solicitudPresente) {

        if (solicitudContraseña.getCode().equals(solicitudPresente.getCode())) {
            return "1";
        } else {
            return "0";
        }
    }

    public String cambiarContraseña(Player cambioContraseña) {

        Response response = this.playerClient.updatePassword(cambioContraseña);
        if (response.getStatus() == 201) {
            return "1";
        } else {
            return "0";
        }
    }

    public String validatePlayer(Player player) {

        Player playerFound = new PlayerClient().findPlayerByName(player.getNickname());

        if (playerFound != null && (player.getNickname().equals(playerFound.getNickname())
                && player.getPassword().equals(playerFound.getPassword()))) {
            return "1";
        } else {
            return "0";
        }
    }

}
