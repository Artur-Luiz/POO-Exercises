package me.artur;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestaoFinanceira {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Gasto> gastos = new ArrayList<>();
    private static List<Ganho> ganhos = new ArrayList<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("Gestão Financeira");
            System.out.println("-----------------------");
            System.out.println("1 - Adicionar Gasto");
            System.out.println("2 - Adicionar Ganho");
            System.out.println("3 - Relatório de Gastos");
            System.out.println("4 - Relatório de Ganhos");
            System.out.println("5 - Relatório Mensal");
            System.out.println("6 - Sair");
            System.out.println("\nSelecione uma opção");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarGasto();
                    break;
                case 2:
                    adicionarGanho();
                    break;
                case 3:
                    relatorioGastos();
                    break;
                case 4:
                    relatorioGanhos();
                    break;
                case 5:
                    relatorioMensal();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    private static void adicionarGasto() {
        System.out.println("Adicionar Gasto");
        System.out.println("-----------------------");
        System.out.println("Informe o tipo de gasto:");
        String tipo = scanner.nextLine();
        System.out.println("Informe a data (dd/MM/yyyy):");
        Date data = null;
        try {
            data = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Data inválida!");
            return;
        }
        System.out.println("Informe o valor:");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Selecione a forma de pagamento:");
        System.out.println("1 - Cheque");
        System.out.println("2 - Pix");
        System.out.println("3 - Débito");
        System.out.println("4 - Crédito");
        System.out.println("5 - Boleto");
        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();
        String formaPagamento = "";
        switch (opcaoPagamento) {
            case 1:
                formaPagamento = "Cheque";
                break;
            case 2:
                formaPagamento = "Pix";
                break;
            case 3:
                formaPagamento = "Débito";
                break;
            case 4:
                formaPagamento = "Crédito";
                break;
            case 5:
                formaPagamento = "Boleto";
                break;
            default:
                System.out.println("Forma de pagamento inválida!");
                return;
        }

        Gasto gasto = new Gasto(tipo, data, valor, formaPagamento);
        gastos.add(gasto);
        System.out.println("Gasto adicionado com sucesso!");
    }

    private static void adicionarGanho() {
        System.out.println("Adicionar Ganho");
        System.out.println("-----------------------");
        System.out.println("Informe o tipo de ganho:");
        String tipo = scanner.nextLine();
        System.out.println("Informe a data (dd/MM/yyyy):");
        Date data = null;
        try {
            data = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Data inválida!");
            return;
        }
        System.out.println("Informe o valor:");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Ganho ganho = new Ganho(tipo, data, valor);
        ganhos.add(ganho);
        System.out.println("Ganho adicionado com sucesso!");
    }

    private static void relatorioGastos() {
        System.out.println("Relatório de Gastos");
        System.out.println("-----------------------");
        double totalGastos = 0;
        for (Gasto gasto : gastos) {
            System.out.println("Tipo: " + gasto.getTipo() + " | Data: " + sdf.format(gasto.getData()) + " | Valor: R$" + df.format(gasto.getValor()) + " | Forma de Pagamento: " + gasto.getFormaPagamento());
            totalGastos += gasto.getValor();
        }
        System.out.println("\nTotal de Gastos: R$" + df.format(totalGastos));
    }

    private static void relatorioGanhos() {
        System.out.println("Relatório de Ganhos");
        System.out.println("-----------------------");
        double totalGanhos = 0;
        for (Ganho ganho : ganhos) {
            System.out.println("Tipo: " + ganho.getTipo() + " | Data: " + sdf.format(ganho.getData()) + " | Valor: R$" + df.format(ganho.getValor()));
            totalGanhos += ganho.getValor();
        }
        System.out.println("\nTotal de Ganhos: R$" + df.format(totalGanhos));
    }

    private static void relatorioMensal() {
        System.out.println("Relatório Mensal");
        System.out.println("-----------------------");
        System.out.println("Selecione o mês:");
        int mes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Selecione o ano:");
        int ano = scanner.nextInt();
        scanner.nextLine();

        double totalGastos = 0;
        double totalGanhos = 0;

        Calendar cal = Calendar.getInstance();

        for (Gasto gasto : gastos) {
            cal.setTime(gasto.getData());
            if (cal.get(Calendar.MONTH) + 1 == mes && cal.get(Calendar.YEAR) == ano) {
                totalGastos += gasto.getValor();
            }
        }

        for (Ganho ganho : ganhos) {
            cal.setTime(ganho.getData());
            if (cal.get(Calendar.MONTH) + 1 == mes && cal.get(Calendar.YEAR) == ano) {
                totalGanhos += ganho.getValor();
            }
        }

        double saldo = totalGanhos - totalGastos;

        System.out.println("Relatório Mensal");
        System.out.println("-----------------------");
        System.out.println("Ano: "+ ano + " Mês: " + mes);
        System.out.println("Ganho Total : R$" + df.format(totalGanhos));
        System.out.println("Gasto Total : R$" + df.format(totalGastos));
        System.out.println("Saldo: R$" + df.format(saldo));
    }


}
