package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.bean.Bairro;
import modelo.bean.Cliente;
import modelo.bean.Pedido;
import modelo.bean.Zona;
import modelo.conexao.FabricaConexao;

public class ClienteDao {

    Connection conexao;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    //metodo para salvar um pedido
    public Cliente buscarClienteCPF(String cpf) throws SQLException {
        conexao = FabricaConexao.pegaCoonexao();
        sql = "SELECT * FROM cliente inner join bairro on id = clibaiid where clicpf = ? ";
        pstm = conexao.prepareStatement(sql);
        pstm.setString(1, cpf);
        rs = pstm.executeQuery();
        Cliente c = null;
        if (rs.next()) {

            Bairro b = new Bairro();
            b.setCodigo(rs.getInt("id"));
            b.setNome(rs.getString("nome"));

            c = new Cliente();
            c.setCpf(rs.getString("clicpf"));
            c.setNome(rs.getString("clinome"));
            c.setRua(rs.getString("clirua"));
            c.setNumero(rs.getString("clinumero"));
            c.setCep(rs.getString("clicep"));
            c.setBairro(b);
        }
        
        FabricaConexao.fecharConexao();
        return c;
    }//fim

}
