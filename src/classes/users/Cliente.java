package classes.users;

public class Cliente {
    
    String nome;
    String cpfCnpj;
    String telefone;
    String endereco;
    TipoCliente tipoCliente;
    TipoPessoa tipoPessoa;
    int idProduto;

    public Cliente(String nome, String cpfCnpj, String telefone, String endereco, TipoCliente tipoCliente, TipoPessoa tipoPessoa, int idProduto) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipoCliente = tipoCliente;
        this.tipoPessoa = tipoPessoa;
        this.idProduto = idProduto;
    }

    //#region Setters
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    //#endregion

    //#region Getters

    public String getNome() {
        return nome;
    }
    public String getCpfCnpj() {
        return cpfCnpj;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }
    public int getIdProduto() {
        return idProduto;
    }
    public String toString() {
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%d'", nome, cpfCnpj, telefone, endereco, tipoCliente.toString(), tipoPessoa.toString(), idProduto);
    }
    //#endregion
}