package edu.fpdual.web.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Objeto que se utiliza para gestionar los cambios de contraseña de jugadores
 */
public class SolicitudContraseña {

    private String email;
    private String code;

}
