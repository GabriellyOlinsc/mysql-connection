package org.example;
public class Unidade {
    private int numIdentificador;
    private String localizacao;

    public Unidade(){
        this.numIdentificador = 0;
        this.localizacao = "";
    }
    public Unidade(int numIdentificador, String localizacao) {
        this.numIdentificador = numIdentificador;
        this.localizacao = localizacao;
    }

    public int getNumIdentificador() {
        return numIdentificador;
    }
    public String getLocalizacao() {
        return localizacao;
    }

    public void setNumIdentificador(int id) {
        this.numIdentificador = id;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "Numero Identificador =" + numIdentificador +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
