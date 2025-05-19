package classes.product;

import java.util.Date;
import java.math.BigDecimal;


public class Produto {
    int idProduto;
    String nome;
    String descricao;
    int volume;
    BigDecimal preco;
    double peso;
    boolean entregue;
    Date dataEntrega;

    public Produto(String nome, String descricao, int volume, BigDecimal preco, double peso, boolean entregue, Date dataEntrega) {
        this.nome = nome;
        this.descricao = descricao;
        this.volume = volume;
        this.preco = preco;
        this.peso = peso;
        this.entregue = entregue;
        this.dataEntrega = dataEntrega;
    }

    //#region Setters

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }
    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    //#endregion

    //#region Getters
    public int getIdProduto() {
        return idProduto;
    }
    public String getNome() {
        return nome;
    }
    public double getPeso() {
        return peso;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public int getVolume() {
        return volume;
    }
    public String getDescricao() {
        return descricao;
    }
    public boolean getEntregue() {
        return entregue;
    }
    public Date getDataEntrega() {
        return dataEntrega;
    }
    public String toString() {
        String pesoString = String.format("%.2f", peso);
        if (pesoString.contains(",")) {
            pesoString = pesoString.replace(",", ".");
        }
        if (dataEntrega == null) {
            return String.format("'%s', '%s', '%d', " + preco + ", '%s', '%b', NULL", nome, descricao, volume, pesoString, entregue);
        }

        return String.format("'%s', '%s', '%d', " + preco + ", '%s', '%b', '%s'", nome, descricao, volume, pesoString, entregue, dataEntrega);
    }
    //#endregion
}
