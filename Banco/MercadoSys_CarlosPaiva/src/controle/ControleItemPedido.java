/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.bean.ItemPedido;
import modelo.bean.Pedido;
import modelo.bean.Produto;
import modelo.conexao.FabricaConexao;
import modelo.dao.ItemPedidoDao;

/**
 *
 * @author Davi
 */
public class ControleItemPedido {
    
    Connection conn=FabricaConexao.pegaCoonexao();
    int cod_produto;
    
    public void adcionarItem(Produto prod,Pedido ped,int qtd,double total){
        ItemPedido itens = new ItemPedido();
        ItemPedidoDao dao = new ItemPedidoDao();
       try{
        itens.setProduto(prod);
        itens.setPedido(ped);
        itens.setQuantidade(qtd);
        itens.setP_unitario(prod.getPreco_venda());
        itens.setP_total(total);
         
     
        dao.salvar(itens);
           System.out.println("Item adcionado no pedido com sucesso:"+prod.getCodigo());
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Erro ao salvar item do pedido: "+ e);
       }finally{
           FabricaConexao.fecharConexao();
       }
       
       }
    
    
}
