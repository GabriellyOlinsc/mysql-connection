package org.example;
import java.sql.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            Pagador pagador = new Pagador();
            Unidade unidade = new Unidade();
            Pagamento pagamento = new Pagamento();

            DataSourceMySQL ds = new DataSourceMySQL();
            Connection conexao = ds.conectaBD();

            int opcao;
            do {
                menu();
                opcao = input.nextInt();
                System.out.println();
                switch (opcao) {
                    case -1 -> System.out.println("Até a próxima!");

                    case 1 -> Operacoes.inserirPagador(conexao, pagador);

                    case 2 -> Operacoes.inserirUnidade(conexao, unidade);

                    case 3 -> Operacoes.inserirPagamento(conexao, pagamento);

                    case 4 -> {
                        System.out.println("=-=-=-=-=-= LISTAGEM DE PAGADORES =-=-=-=-=-=");
                        Operacoes.buscarPagadores(conexao);
                    }

                    case 5 -> {
                        System.out.println("=-=-=-=-=-= LISTAGEM DE PAGAMENTOS =-=-=-=-=-=");
                        Operacoes.buscarPagamentos(conexao);
                    }

                    case 6 -> {
                        System.out.println("=-=-=-=-=-= LISTAGEM DE UNIDADES =-=-=-=-=-=");
                        Operacoes.buscarUnidades(conexao);
                    }

                    case 7 -> {
                        System.out.print("Informe o ID do Pagador: ");
                        int idPagador = input.nextInt();
                        Operacoes.excluirPagador(conexao,idPagador);
                    }

                    case 8 -> {
                        System.out.print("Informe o ID do Pagamento: ");
                        int idPagamento = input.nextInt();
                        Operacoes.excluirPagamento(conexao,idPagamento);
                    }

                    case 9 -> {
                        System.out.print("Informe o ID da Unidade: ");
                        int idUnidade = input.nextInt();
                        Operacoes.excluirUnidade(conexao,idUnidade);
                    }

                    case 10 -> Operacoes.baixarComprovante(conexao);

                    default -> System.out.println("Opção inválida!");
                }
                System.out.println("\nPressione Enter para continuar...");
                new Scanner(System.in).nextLine();
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
                Opção:\s""" );
    }
}