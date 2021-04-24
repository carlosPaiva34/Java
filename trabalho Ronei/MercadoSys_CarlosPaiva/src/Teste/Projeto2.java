package Teste;

import java.sql.SQLException;
import modelo.bean.Cliente;
import modelo.bean.Pedido;
import modelo.dao.ClienteDao;
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
        
        PedidoDao dao=new PedidoDao();
      try{  
        for(Pedido p:dao.listar()){
        System.out.println("codigo:"+p.getCodigo());
        System.out.println("staus:"+p.getStatus());
    
    }
      }catch(SQLException e){
          
      }
    }
    
}
