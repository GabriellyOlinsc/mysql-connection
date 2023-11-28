package org.example;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.Blob;

public class Operacoes {
    //////////////////////////////////////////// INSERIR ///////////////////////////////////////////////////////////
    public static void inserirPagador(Connection conec, Pagador pagador) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Nome completo: ");
        pagador.setNome(input.nextLine());
        System.out.print("Email: ");
        pagador.setEmail(input.nextLine());
        System.out.print("CPF: ");
        pagador.setCpf(input.nextLine());
        System.out.print("Telefone: ");
        pagador.setTelefone(input.nextLine());

        try {
            PreparedStatement ps = conec.prepareStatement("call InserirPagador(?,?,?,?)");
            ps.setString(1, pagador.getNome());
            ps.setString(2, pagador.getEmail());
            ps.setString(3, pagador.getCpf());
            ps.setString(4, pagador.getTelefone());
            ps.execute();
            System.out.println("Pagador registrado com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void inserirUnidade(Connection conec, Unidade unidade) {
        Scanner input = new Scanner(System.in);
        System.out.print("Numero Identificador: ");
        unidade.setNumIdentificador(input.nextInt());
        System.out.print("Localização: ");
        unidade.setLocalizacao(input.nextLine());
        try {
            PreparedStatement ps = conec.prepareStatement("call InserirUnidade(?,?)");
            ps.setInt(1, unidade.getNumIdentificador());
            ps.setString(2, unidade.getLocalizacao());
            ps.execute();
            System.out.println("Unidade registrada com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void inserirPagamento(Connection conec, Pagamento pagamento) throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Id do Pagador: ");
        pagamento.setIdPagador(input.nextInt());
        System.out.print("Id da Unidade: ");
        pagamento.setIdUnidade(input.nextInt());
        System.out.print("Ano: ");
        pagamento.setAno(input.nextInt());
        System.out.print("Mês: ");
        pagamento.setMes(input.nextInt());
        input.nextLine();
        System.out.print("Data atual (yyyy-MM-dd): ");
        String data = input.nextLine();

        try {
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = formatoUsuario.parse(data);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pagamento.setdataPagamento(sqlDate);
        } catch (ParseException e) {
            System.err.println("Erro ao formatar a data. Certifique-se de fornecer no formato correto (yyyy-MM-dd).");
            return;
        }

        byte[] dadosDoComprovante = criarDadosDoComprovante(pagamento);

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(dadosDoComprovante);
            PreparedStatement ps = conec.prepareStatement("call InserirPagamento(?,?,?,?,?,?)");
            ps.setInt(1, pagamento.getIdPagador());
            ps.setDate(2,pagamento.getdataPagamento());
            ps.setInt(3,pagamento.getIdUnidade());
            ps.setInt(4, pagamento.getAno());
            ps.setInt(5, pagamento.getMes());
            ps.setBlob(6, inputStream, dadosDoComprovante.length);
            ps.execute();
            System.out.println("Pagamento registrado com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    private static byte[] criarDadosDoComprovante(Pagamento pagamento) {
        String conteudoComprovante = pagamento.toString();
        return conteudoComprovante.getBytes();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// BUSCAR ////////////////////////////////////////////////////////////////
    public static void buscarPagadores(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Pagador");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Nome: " + resultSet.getString("nomeCompleto"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Numero Documento: " + resultSet.getString("numeroDocumento"));
                System.out.println("Telefone: " + resultSet.getString("telefone"));
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void buscarPagamentos(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    """
                            Select P.id, Pagador.nomeCompleto as nomePagador, P.dataPagamento, P.anoReferencia, P.mesReferencia, P.idUnidade, P.comprovante, P.dataRegistro  from Pagamento P
                            JOIN Pagador ON P.idPagador = Pagador.id
                            ORDER BY P.anoReferencia, P.mesReferencia;

                            """);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nomePagador");
                int id = resultSet.getInt("id");
                Date dataPagamento = resultSet.getDate("dataPagamento");
                int ano = resultSet.getInt("anoReferencia");
                int mes = resultSet.getInt("mesReferencia");
                int idUnidade = resultSet.getInt("idUnidade");
                Blob comprovante = resultSet.getBlob("comprovante");
                Date dataRegsitro = resultSet.getDate("dataRegistro");
                System.out.printf("%n%nID Pagamento: %s%nPagador: %s%nData de pagamento: %s%nAno: %s%nMês: %s%nID Unidade: %s%nComprovante: %s%nData de Registro: %s%n",
                        id, nome, dataPagamento, ano, mes, idUnidade, comprovante, dataRegsitro);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void buscarUnidades(Connection c) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Unidade");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID Unidade:" + resultSet.getInt("id"));
                System.out.println("Numero Identificador: " + resultSet.getInt("numeroIdentificador"));
                System.out.println("Localização: " + resultSet.getString("localizacao"));
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// DELETAR ///////////////////////////////////////////////////////////////10
    public static void excluirPagador(Connection con, int idPagador) {
        try {
            PreparedStatement ps = con.prepareStatement("CALL DeletarPagador(?)");
            ps.setInt(1, idPagador);
            ps.execute();
            System.out.println("Pagador excluido com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void excluirPagamento(Connection c,int idPagamento) {
        try {
            PreparedStatement ps = c.prepareStatement("CALL DeletarPagamento(?)");
            ps.setInt(1, idPagamento);
            ps.execute();
            System.out.println("Pagamento excluido com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void excluirUnidade(Connection c,int idUnidade) {
        try {
            PreparedStatement ps = c.prepareStatement("CALL DeletarUnidade(?)");
            ps.setInt(1, idUnidade);
            ps.execute();
            System.out.println("Unidade excluida com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void baixarComprovante(Connection conec) {
          Scanner input = new Scanner(System.in);
          System.out.print("Informe o id do pagamento: ");
          int idPagamento = input.nextInt();
          input.nextLine();
          System.out.print("Informe o caminho do arquivo a ser baixado:");
          String path = input.nextLine();

          try {
              PreparedStatement ps = conec.prepareStatement("SELECT comprovante FROM Pagamento WHERE id=?");
              ps.setInt(1, idPagamento);
              ResultSet rs = ps.executeQuery() ;
              if (rs.next()) {
                  Blob blob = rs.getBlob("comprovante");
                  File directory = new File(path);
                  File f = new File(directory,"comprovante_downloaded.txt");
                  try (FileOutputStream fos = new FileOutputStream(f)) {
                      byte[] dados = blob.getBytes(1, (int) blob.length());
                      fos.write(dados);
                      System.out.println("Comprovante baixado com sucesso em: " + f.getAbsolutePath());
                  }
              }else {
                  System.err.println("Comprovante não encontrado para o ID do Pagamento: " + idPagamento);
              }
          } catch (Exception e) {
              System.err.println(e.getMessage());
          }
        }
}
