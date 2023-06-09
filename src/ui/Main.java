package ui;

import exceptions.DuplicateTeamException;
import exceptions.InvalidInputException;
import exceptions.InvalidMenuOptionException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);
        while (shouldContinue) {
            try {
                menu.exibirMenu();
            } catch (RuntimeException e) {
                if (e.getCause() instanceof InvalidInputException) {
                    System.out.println(e.getCause().getMessage());
                } else {
                    System.out.println("Ocorreu um erro inesperado.");
                }
            }

            System.out.println("Deseja continuar? (S/N): ");
            String continuar;
            try {
                continuar = scanner.nextLine();
            } catch (NoSuchElementException exception) {
                continuar = "N";
            }
            shouldContinue = continuar.equalsIgnoreCase("S");
        }

        scanner.close();
    }
}