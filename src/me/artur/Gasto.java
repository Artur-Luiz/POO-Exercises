package me.artur;

import java.util.Date;

class Gasto {
    private String tipo;
    private Date data;
    private double valor;
    private String formaPagamento;

    public Gasto(String tipo, Date data, double valor, String formaPagamento) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
