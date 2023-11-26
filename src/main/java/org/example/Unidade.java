package org.example;
public class Unidade {
    private int id;
    private String localizacao;

    public Unidade(){
        this.id = 0;
        this.localizacao = "";
    }
    public Unidade(int id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }
    public String getLocalizacao() {
        return localizacao;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
