package modelo.bean;

import java.util.Date;

public class Pedido {
    private int codigo;
    private String status;
    private Date dtPrdido;
    private Date dtEntrega;
    private double valor;
    private Usuario usuario;
    private Cliente cliente;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDtPrdido() {
        return dtPrdido;
    }

    public void setDtPrdido(Date dtPrdido) {
        this.dtPrdido = dtPrdido;
    }

    public Date getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(Date dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
