package modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricaConexao {
    
    private static final String USUARIO="root";
    private static final String SENHA="senha";
    private static final String URL="jdbc:mysql://localhost:3306/teste_mercado";
    private static Connection con = null;
    
    public static Connection pegaCoonexao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL,USUARIO,SENHA);
            System.out.println("Conectado com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("Arquivo não encontrado "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar "+ex.getMessage());
        }
        return con;
    }//fim do metodo
    public static void fecharConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//fim
    
}
