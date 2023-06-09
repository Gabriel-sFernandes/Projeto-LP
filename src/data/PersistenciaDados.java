package data;

import model.Time;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDados {
    private static final String FILE_PATH = "team.txt";

    public static void salvarDados(List<Time> times) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Time time : times) {
                writer.write(time.toString());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static List<Time> carregarDados() {
        List<Time> times = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] teamData = line.split(",");
                    String nome = teamData[0].split("=")[1];
                    int pontos = Integer.parseInt(teamData[1].split("=")[1]);
                    int vitorias = Integer.parseInt(teamData[2].split("=")[1]);
                    int derrotas = Integer.parseInt(teamData[3].split("=")[1]);
                    int empates = Integer.parseInt(teamData[4].split("=")[1]);
                    int jogos = Integer.parseInt(teamData[5].split("=")[1].replace("]", ""));
                    Time time = new Time(nome, vitorias, derrotas, empates);
                    times.add(time);
                }
                System.out.println("Dados carregados com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao carregar os dados: " + e.getMessage());
            }
        }
        return times;
    }
}
