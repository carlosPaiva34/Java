
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.bean;

/**
 *
 * @author Davi
 */
public class ItemPedido {
    private int codigo;
    private Produto produto;
    private Pedido pedido;
    private int quantidade;
    private double p_unitario;
    private double p_total;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getP_unitario() {
        return p_unitario;
    }

    public void setP_unitario(double p_unitario) {
        this.p_unitario = p_unitario;
    }

    public double getP_total() {
        return p_total;
    }

    public void setP_total(double p_total) {
        this.p_total = p_total;
    }
    
    
    
}
