import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorTarefas gerenciador = new GerenciadorTarefas();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== GERENCIADOR DE TAREFAS ====");
            System.out.println("         MENU PRINCIPAL         ");
            System.out.println("-----------------------------------");
            System.out.println("[1] Cadastrar tarefa");
            System.out.println("[2] Listar todas as tarefas");
            System.out.println("[3] Filtrar tarefas por status");
            System.out.println("[4] Listar tarefas ordenadas por data limite");
            System.out.println("[0] Sair");
            System.out.println("-----------------------------------");
            System.out.print("Escolha uma opção: ");

            try {  int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        cadastrarTarefa();
                        break;
                    case 2:
                        listarTodas();
                        break;
                    case 3:
                        filtrarPorStatus();
                        break;
                    case 4:
                        listarOrdenadasPorDataLimite();
                        break;
                    case 0:
                        System.out.println("\nSaindo...");
                        return;
                    default:
                        System.out.println("\nOpção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n**** ENTRADA INVÁLIDA!!!!! Digite um número. ****");
            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }
    }

    private static void cadastrarTarefa() {
        ValidadorEntrada validadorTitulo = entrada -> entrada != null && entrada.trim().length() >= 5;
        String titulo;
        while (true) {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
            if (validadorTitulo.validar(titulo)) {
                break;
            } else {
                System.out.println("O título deve ter pelo menos 5 caracteres. Tente novamente.");
            }
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Data limite (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        LocalDate dataLimite = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Status (1-PENDENTE, 2-EM_ANDAMENTO, 3-CONCLUÍDA): ");
        int statusInt = Integer.parseInt(scanner.nextLine());
        StatusTarefa status;
        if (statusInt == 1) status = StatusTarefa.PENDENTE;
        else if (statusInt == 2) status = StatusTarefa.EM_ANDAMENTO;
        else if (statusInt == 3) status = StatusTarefa.CONCLUIDA;
        else throw new IllegalArgumentException("Status inválido!");

        Tarefa tarefa = new Tarefa(titulo, descricao, dataLimite, status);
        gerenciador.adicionarTarefa(tarefa);
        System.out.println("Tarefa cadastrada com sucesso!");
    }

    private static void listarTodas() {
        List<Tarefa> todas = gerenciador.listarTodas();
        if (todas.isEmpty()) {
            System.out.println("\n**** NÃO EXISTE NENHUMA TAREFA CADASTRADA AINDA! **** \n               Tente novamente!");
        } else {
            System.out.println("\n--- Todas as Tarefas ---");
            gerenciador.listarTodas()
                    .forEach(System.out::println);
        }
    }

    private static void filtrarPorStatus() {
        //System.out.println(" ");
        System.out.println("\nFiltrar por qual status? \n");
        // System.out.println(" ");
        System.out.println("[1] PENDENTE ");
        System.out.println("[2] EM_ANDAMENTO ");
        System.out.println("[3] CONCLUÍDA ");
        //System.out.println("");
        System.out.print("\n Escolha: ");
        int statusInt = Integer.parseInt(scanner.nextLine());
        StatusTarefa status;
        if (statusInt == 1) status = StatusTarefa.PENDENTE;
        else if (statusInt == 2) status = StatusTarefa.EM_ANDAMENTO;
        else if (statusInt == 3) status = StatusTarefa.CONCLUIDA;
        else throw new IllegalArgumentException("Status inválido!");

        List<Tarefa> filtradas = gerenciador.filtrarPorStatus(status);
        if (filtradas.isEmpty()) {
            System.out.print("\n                  !!!! ATENÇÃO !!!! ");
            System.out.println("\n NÃO EXISTE NENHUMA TAREFA CADASTRADA COM ESSE STATUS!");
            System.out.println("                  Tente novamente!");
        } else {
            System.out.println("\n--- Tarefas com status " + status + " ---");
            filtradas.forEach(System.out::println);
        }
    }
    private static void listarOrdenadasPorDataLimite() {
        System.out.println("\n--- Tarefas Ordenadas por Data Limite ---");
        gerenciador.listarOrdenadasPorDataLimite().forEach(System.out::println);
    }
}