package org.example;
public class Pagador {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public Pagador(){
        this.id = 0;
        this.nome = "";
        this.email = "";
        this.cpf = "";
        this.telefone = "";
    }
    public Pagador(int id, String nome, String email, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getId(){
        return id;
    }

    public  void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
