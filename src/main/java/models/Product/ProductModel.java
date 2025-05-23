package models.Product;

import models.Insertable;
import java.util.Date;

public class ProductModel implements Insertable {
    int idProduto;
    String nome;
    String descricao;
    int volume;
    float preco;
    float peso;
    boolean entregue;
    Date dataEntrega;
    int remetenteId;
    int destinatarioId;
    
    @Override
    public String toString() 
    {
        String pesoString = String.format("%.2f", peso);
        if (pesoString.contains(",")) {
            pesoString = pesoString.replace(",", ".");
        }
        if (dataEntrega == null) {
            return String.format("'%s', '%s', '%d', " + preco + ", '%s', '%b', NULL", nome, descricao, volume, pesoString, entregue);
        }

        return String.format("'%s', '%s', '%d', " + preco + ", '%s', '%b', '%s'", nome, descricao, volume, pesoString, entregue, dataEntrega);
    }
    
    @Override
    public String typeName()
    {
    	return nome;
    }

    public ProductModel(String nome, String descricao, int volume, float preco, float peso, boolean entregue, Date dataEntrega) 
    {
	    this.nome = nome;
	    this.descricao = descricao;
	    this.volume = volume;
	    this.preco = preco;
	    this.peso = peso;
	    this.entregue = entregue;
	    this.dataEntrega = dataEntrega;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setId(int id)
    {
    	this.idProduto = id;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public void setPreco(float preco) {
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
    public void setDestinatarioId(int destinatarioId) {
		this.destinatarioId = destinatarioId;
	}
    public void setRemetenteId(int remetenteId) {
		this.remetenteId = remetenteId;
	}

    public int getIdProduto() {
        return idProduto;
    }
    public String getNome() {
        return nome;
    }
    public float getPeso() {
        return peso;
    }
    public float getPreco() {
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
    public int getRemetenteId() {
		return remetenteId;
	}
	public int getDestinatarioId() {
		return destinatarioId;
	}
}