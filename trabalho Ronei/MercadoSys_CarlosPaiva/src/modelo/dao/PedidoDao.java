package modelo.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bean.Cliente;
import modelo.bean.Pedido;
import modelo.bean.Usuario;
import modelo.conexao.FabricaConexao;

public class PedidoDao implements DefaultGeneric<Pedido> {

    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    String sql;

    //metodo para salvar um pedido
    @Override
    public void salvar(Pedido pedido) throws SQLException {
        conexao = FabricaConexao.pegaCoonexao();
        sql = "insert into pedido(pedstatus,peddtdpedido,peddtentrega,pedvalor,pedusuid,pedclicpf)\n"
                + "values('A',current_date(),?,?,?,?)";
        pstm = conexao.prepareStatement(sql);
        pstm.setDate(1, new java.sql.Date(pedido.getDtEntrega().getTime()));
        pstm.setDouble(2, pedido.getValor());
        pstm.setInt(3, pedido.getUsuario().getId());
        pstm.setString(4, pedido.getCliente().getCpf());
        pstm.execute();
        FabricaConexao.fecharConexao();
    }

    @Override
    public List<Pedido> listar() throws SQLException {
        List<Pedido> listaPedido = new ArrayList<>();
        conexao = FabricaConexao.pegaCoonexao();
        sql = "select * from pedido \n"
                + "INNER JOIN cliente ON cliente.clicpf = pedido.pedclicpf\n"
                + "INNER JOIN usuario ON usuario.id = pedido.pedusuid";
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();
        Pedido p = null;
        Usuario u = null;
        Cliente c = null;

        while (rs.next()) {
            p = new Pedido();
            p.setCodigo(rs.getInt("codigo"));
            p.setStatus(rs.getString("pedstatus"));
            p.setDtPrdido(rs.getDate("peddtdpedido"));
            p.setDtEntrega(rs.getDate("peddtentrega"));
            p.setValor(rs.getDouble("pedvalor"));
            u = new Usuario();
            u.setId(rs.getInt("pedusuid"));
            u.setNome(rs.getString("nome"));
            p.setUsuario(u);
            c = new Cliente();
            c.setCpf(rs.getString("pedclicpf"));
            c.setNome(rs.getString("clinome"));
            p.setCliente(c);
            listaPedido.add(p);
        }
        return listaPedido;
    }

    @Override
    public List<Pedido> findByName(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Pedido t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Pedido t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
