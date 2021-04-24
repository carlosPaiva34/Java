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
import java.util.ArrayList;
import java.util.List;
import modelo.bean.Cliente;
import modelo.bean.ItemPedido;
import modelo.bean.Pedido;
import modelo.bean.Produto;
import modelo.bean.Usuario;
import modelo.conexao.FabricaConexao;

/**
 *
 * @author Davi
 */
public class ItemPedidoDao implements DefaultGeneric<ItemPedido> {

    Connection conexao;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    @Override
    public void salvar(ItemPedido t) throws SQLException {
        conexao = FabricaConexao.pegaCoonexao();
        sql = "insert into itempedido(produto,pedido,quantidade,v_unitario,v_total) values(?,?,?,?,?)";
        pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, t.getProduto().getCodigo());
        pstm.setInt(2, t.getPedido().getCodigo());
        pstm.setInt(3, t.getQuantidade());
        pstm.setDouble(4, t.getP_unitario());
        pstm.setDouble(5, t.getP_total());
        pstm.execute();
        FabricaConexao.fecharConexao();
    }

    @Override
    public List<ItemPedido> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> findBy(int codigo) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ItemPedido> listaItens = new ArrayList<>();
        String select = "select * from itempedido inner join produto on produto.prodid=itempedido.produto\n" +
"inner join pedido on pedido.codigo=itempedido.pedido where pedido.codigo=?";
        
        try{
        conn=FabricaConexao.pegaCoonexao();
        stm=conn.prepareStatement(select);
        stm.setInt(1, codigo);
        rs=stm.executeQuery();
        while(rs.next()){
            ItemPedido item=new ItemPedido();
            item.setCodigo(rs.getInt("codigo"));
            
            Produto prod=new Produto();
            prod.setNome(rs.getString("produto.prodnome"));
            Pedido ped=new Pedido();
            ped.setCodigo(rs.getInt("pedido.codigo"));
            
            item.setP_unitario(rs.getDouble("v_unitario"));
            item.setQuantidade(rs.getInt("quantidade"));
            
            item.setP_total(rs.getDouble("v_total"));
            
            item.setProduto(prod);
            item.setPedido(ped);
            listaItens.add(item);
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return listaItens;
    }

    @Override
    public void excluir(ItemPedido t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(ItemPedido t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
