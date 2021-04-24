package Teste;

import java.sql.SQLException;
import java.util.Iterator;
import modelo.bean.Cliente;
import modelo.bean.ItemPedido;
import modelo.bean.Pedido;
import modelo.dao.ClienteDao;
import modelo.dao.ItemPedidoDao;
import modelo.dao.PedidoDao;

/**
 *
 * @author vronei
 */
public class Projeto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        System.out.println("total: ");
        ItemPedidoDao dao = new ItemPedidoDao();
        for (Iterator<ItemPedido> it = dao.findBy(51).iterator(); it.hasNext();) {
            ItemPedido i = it.next();
            System.out.print("Pedido: " + i.getPedido().getCodigo());
            System.out.print(" Item: " + i.getCodigo());
            System.out.print(" produto: " + i.getProduto().getNome());
            System.out.print(" valor: " + i.getP_unitario());
            System.out.print(" quatidade: " + i.getQuantidade());
            System.out.print(" total: " + i.getP_total());
            
             System.out.print("\n");
           
        }
    }
}
