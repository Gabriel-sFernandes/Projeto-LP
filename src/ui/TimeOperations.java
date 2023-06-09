package ui;

import exceptions.DuplicateTeamException;

import java.io.IOException;

public interface TimeOperations {
    void registrarTime() throws DuplicateTeamException;
    void searchTeam();
    void listarTimes();
    void salvarDados();
    void removerTime();
    void atualizarTime() throws IOException;
}
