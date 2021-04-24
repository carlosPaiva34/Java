package modelo.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
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
        String posicao = "select * from pedido";
        conexao = FabricaConexao.pegaCoonexao();
        pstm = conexao.prepareStatement(posicao);
        rs = pstm.executeQuery();
        rs.last();
        int idAberto = rs.getInt("codigo");

        sql = "update pedido set pedstatus='A',peddtdpedido=current_date(),peddtentrega=? ,pedvalor=? ,pedusuid=?,pedclicpf=? where codigo=" + idAberto;
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
            if (rs.getString("pedstatus").equals("A")) {
                p.setStatus("ABERTO");
            } else {
                p.setStatus("FECHADO");
            }

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
    public List<Pedido> findBy(int codigo) {
        List<Pedido> lista=new ArrayList<>();
        try {
            conexao = FabricaConexao.pegaCoonexao();
            sql = "select * from pedido \n"
                    + "INNER JOIN cliente ON cliente.clicpf = pedido.pedclicpf\n"
                    + "INNER JOIN usuario ON usuario.id = pedido.pedusuid where codigo=?";
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            Pedido p = null;
            Usuario u = null;
            Cliente c = null;

            if (rs.next()) {
                p = new Pedido();
                p.setCodigo(rs.getInt("codigo"));
                if (rs.getString("pedstatus").equals("A")) {
                    p.setStatus("ABERTO");
                } else {
                    p.setStatus("FECHADO");
                }

                p.setDtPrdido(rs.getDate("peddtdpedido"));
                p.setDtEntrega(rs.getDate("peddtentrega"));
                p.setValor(rs.getDouble("pedvalor"));
                u = new Usuario();
                u.setId(rs.getInt("pedusuid"));
                p.setUsuario(u);
                c = new Cliente();
                c.setCpf(rs.getString("pedclicpf"));
                p.setCliente(c);
                return lista;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao();
        }
        return null;

    }

    @Override
    public void excluir(Pedido t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Pedido t) {

        try {
            conexao = FabricaConexao.pegaCoonexao();
            sql = "update table pedido set pedstatus=?, peddtentrega=?,pedvalor=?,pedusuid=?,pedclicpf=?) where codigo=?";
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, t.getStatus());
            pstm.setDate(2, new Date(t.getDtEntrega().getTime()));
            pstm.setDouble(3, t.getValor());
            pstm.setInt(4, t.getUsuario().getId());
            pstm.setString(5, t.getCliente().getCpf());
            pstm.setInt(6, t.getCodigo());
            pstm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar");
            e.printStackTrace();
        } finally {
            FabricaConexao.pegaCoonexao();
        }

    }

    public int iniciarPedido() {
        int cod = 0;
        conexao = FabricaConexao.pegaCoonexao();
        try {
            pstm = conexao.prepareStatement("insert into pedido(pedvalor)values(?)");
            pstm.setDouble(1, 0);
            pstm.execute();
            rs = pstm.executeQuery("select * from pedido");
            rs.last();
            cod = rs.getInt("codigo");

        } catch (SQLException e) {

        } finally {
            FabricaConexao.fecharConexao();
        }
        return cod;
    }

}
