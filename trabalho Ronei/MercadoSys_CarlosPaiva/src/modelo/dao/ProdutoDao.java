package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.bean.Bairro;
import modelo.bean.Cliente;
import modelo.bean.Produto;
import modelo.conexao.FabricaConexao;

public class ProdutoDao {
    
    Connection conexao;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;
    //metodo para salvar um pedido
    public Produto buscarProduto(int codigo) throws SQLException{
        conexao = FabricaConexao.pegaCoonexao();
        sql = "SELECT * FROM produto where prodid = ? ";
        pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, codigo);
        rs = pstm.executeQuery();
        Produto p = null;
        if(rs.next()){
            p = new Produto();
            p.setCodigo(rs.getInt("prodid"));
            p.setNome(rs.getString("prodnome"));
            p.setPreco(rs.getDouble("prodpreco"));
        }
        FabricaConexao.fecharConexao();
        return p;
    }//fim
    
}
