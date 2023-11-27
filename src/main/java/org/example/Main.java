package org.example;
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Scanner input = new Scanner(System.in);
            Pagador pagador = new Pagador();
            Unidade unidade = new Unidade();
            Pagamento pagamento = new Pagamento();

            DataSourceMySQL ds = new DataSourceMySQL();
            Connection conexao = ds.conectaBD();

            int opcao = 0;
            do {
                menu();
                opcao = input.nextInt();
                switch (opcao) {
                    case -1 -> System.out.println("Até a próxima!");
                    case 1 -> Operacoes.inserirPagador(conexao, pagador);
                    case 2 -> Operacoes.inserirUnidade(conexao, unidade);
                    case 3 -> Operacoes.inserirPagamento(conexao, pagamento);
                    case 4 -> Operacoes.buscarPagadores();
                    case 5 -> Operacoes.buscarPagamentos();
                    case 6 -> Operacoes.buscarUnidades();
                    case 7 -> {
                        System.out.print("Informe o ID do Pagador: ");
                        int idPagador = input.nextInt();
                        Operacoes.excluirPagador(idPagador);
                    }
                    case 8 -> {
                        System.out.print("Informe o ID do Pagamento: ");
                        int idPagamento = input.nextInt();
                        Operacoes.excluirPagamento(idPagamento);
                    }
                    case 9 -> {
                        System.out.print("Informe o ID da Unidade: ");
                        int idUnidade = input.nextInt();
                        Operacoes.excluirUnidade(idUnidade);
                    }
                    case 10 ->{
                        Operacoes.baixarComprovante(conexao);
                    }
                    default -> System.out.println("Opção inválida!");
                }
                System.in.read();
            } while (opcao != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void menu() {
        System.out.print("""
                Menu
                1 - INSERIR Pagador       
                2 - INSERIR Unidade       
                3 - INSERIR Pagamento    
                4 - BUSCAR Pagador
                5 - BUSCAR Pagamento
                6 - BUSCAR Unidade
                7 - DELETAR Pagador    
                8 - DELETAR Pagamento   
                9 - DELETAR Unidade
                10- BAIXAR Comprovante de Pagamento
                -1 - Sair 
                Opção:\s""");
    }
}