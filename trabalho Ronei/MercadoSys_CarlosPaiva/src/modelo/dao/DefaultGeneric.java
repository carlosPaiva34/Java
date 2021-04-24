/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Davi
 * @param <T>
 */
public interface DefaultGeneric<T> {

    public void salvar(T t)throws SQLException;

    public List<T> listar()throws SQLException;

    public List<T> findByName(String name)throws SQLException;

    public void excluir(T t)throws SQLException;

    public void atualizar(T t)throws SQLException;

}
