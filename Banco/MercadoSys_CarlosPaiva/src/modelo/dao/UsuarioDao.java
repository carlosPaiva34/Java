/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import modelo.bean.Usuario;
import modelo.conexao.FabricaConexao;

/**
 *
 * @author Davi
 */
public class UsuarioDao {
public static Usuario usuario;
    
    public boolean validarUsuario(String login, String senha) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean validar = false;

        String sql = "SELECT * FROM usuario WHERE login=? and senha=?";

        try {
            conn = FabricaConexao.pegaCoonexao();
            pst = conn.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();

            while (rs.next()) {
                usuario=new Usuario();
                usuario.setId(rs.getInt("id"));
                 usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                validar = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao encontrar nome");
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao();
        }

        return validar;

    }

}
