package edu.fpdual.persistence.manager;

import java.sql.Connection;

/**
 * Interfaz que asegura que haya un método insert en todas
 * las clases de implementación de SQL. Es extendida por todos
 * los manager de cada implementación
 * @param <T> - Genérico
 */
public interface Manager<T> {

    int insert(Connection con, T element);

}
