package org.example;
import java.sql.Blob;
import java.util.Date;

public class Pagamento {
    private int id;
    private int idPagador;
    private int idUnidade;
    private Date dataPagamento;
    private Date dataRegistro;
    private Blob comprovante;
    private int ano;
    private int mes;

    public Pagamento() {
        this.id = 0;
        this.idPagador = 0;
        this.idUnidade = 0;
        this.dataPagamento = null;
        this.dataRegistro = null;
        this.comprovante = null;
        this.ano = 0;
        this.mes = 0;
    }

    public Pagamento(int id, Date dataPagamento, Date dataRegistro, Blob comprovante, int ano, int mes, int idPagador, int idUnidade) {
        this.id = id;
        this.idPagador = idPagador;
        this.idUnidade = idUnidade;
        this.dataPagamento = dataPagamento;
        this.dataRegistro = dataRegistro;
        this.comprovante = comprovante;
        this.ano = ano;
        this.mes = mes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPagador() {
        return idPagador;
    }

    public void setIdPagador(int idPagador) {
        this.idPagador = idPagador;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Date getdataPagamento() {
        return dataPagamento;
    }

    public void setdataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getdataRegistro() {
        return dataRegistro;
    }

    public void setdataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Blob getComprovante() {
        return comprovante;
    }

    public void setComprovante(Blob comprovante) {
        this.comprovante = comprovante;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
