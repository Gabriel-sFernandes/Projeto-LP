package ui;

import data.PersistenciaDados;
import exceptions.DuplicateTeamException;
import exceptions.InvalidInputException;
import exceptions.InvalidMenuOptionException;
import model.Time;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Menu implements TimeOperations{
    private final List<Time> times;

    public Menu() {
        times = PersistenciaDados.carregarDados();
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean opcao = true;

        while (opcao) {
            System.out.println("----- MENU -----");
            System.out.println("1. Cadastrar time");
            System.out.println("2. Remover time");
            System.out.println("3. Atualizar time");
            System.out.println("4. Listar times");
            System.out.println("5. Pesquisar time");
            System.out.println("6. Salvar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> registrarTime();
                    case 2 -> removerTime();
                    case 3 -> {
                        try {
                            atualizarTime();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case 4 -> listarTimes();
                    case 5 -> searchTeam();
                    case 6 -> salvarDados();
                    case 0 -> {
                        opcao = false;
                        salvarDadosAoSair();
                    }
                    default -> throw new InvalidMenuOptionException("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                try {
                    throw new InvalidInputException("Opção inválida! Digite um número correspondente à opção desejada.");
                } catch (InvalidInputException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (DuplicateTeamException e) {
                System.out.println("Time com o mesmo nome já existe!");
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }
        System.out.println("Encerrando o programa...");
        scanner.close();
    }

    @Override
    public void registrarTime() throws DuplicateTeamException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do time: ");
        String nome = scanner.nextLine();

        if (teamExists(nome)) {
            try {
                throw new DuplicateTeamException("Time com o mesmo nome já existe!");
            } catch (DuplicateTeamException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print("Digite a quantidade de vitórias: ");
        int vitorias = scanner.nextInt();
        System.out.print("Digite a quantidade de derrotas: ");
        int derrotas = scanner.nextInt();
        System.out.print("Digite a quantidade de empates: ");
        int empates = scanner.nextInt();

        Time time = new Time(nome, vitorias, derrotas, empates);
        times.add(time);
        System.out.println("Time cadastrado com sucesso!");
    }

    @Override
    public void removerTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do time a ser removido: ");
        String nome = scanner.nextLine();

        for (Iterator<Time> iterator = times.iterator(); iterator.hasNext();) {
            Time time = iterator.next();
            if (time.getNome().equalsIgnoreCase(nome)) {
                iterator.remove();
                System.out.println("Time removido com sucesso!");
                return;
            }
        }
        System.out.println("Time não encontrado!");
    }

    private static void atualizarTime() throws IOException {
        System.out.println("\nAtualizar informações de um time");
        System.out.print("Nome do time: ");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();

        PersistenciaDados persistencia = new PersistenciaDados();
        List<Time> times = persistencia.carregarDados();

        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);
            if (time.getNome().equals(nome)) {
                System.out.println("Novos dados:");
                System.out.print("Pontos: ");
                int pontos = Integer.parseInt(scanner.nextLine());
                System.out.print("Jogos: ");
                int jogos = Integer.parseInt(scanner.nextLine());
                System.out.print("Vitórias: ");
                int vitorias = Integer.parseInt(scanner.nextLine());
                System.out.print("Empates: ");
                int empates = Integer.parseInt(scanner.nextLine());
                System.out.print("Derrotas: ");
                int derrotas = Integer.parseInt(scanner.nextLine());

                time.atualizarTime(pontos, jogos, vitorias, empates, derrotas);

                persistencia.salvarDados(times); // Salvar os dados atualizados

                System.out.println("Informações atualizadas com sucesso!");
                return;
            }
        }

        System.out.println("Time não encontrado.");
    }


    @Override
    public void searchTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do time a ser pesquisado: ");
        String nome = scanner.nextLine();

        for (Time time : times) {
            if (time.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Time encontrado: " + time);
                return;
            }
        }
        System.out.println("Time não encontrado!");
    }

    @Override
    public void listarTimes() {
        for (Time time : times) {
            System.out.println(time);
        }
    }

    @Override
    public void salvarDados() {
        PersistenciaDados.salvarDados(times);
    }

    private boolean teamExists(String nome) {
        for (Time team : times) {
            if (team.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;

    }
    private void salvarDadosAoSair() {
        PersistenciaDados.salvarDados(times);
    }
}