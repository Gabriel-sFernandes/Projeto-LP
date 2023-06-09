package ui;

import exceptions.DuplicateTeamException;

import java.io.IOException;

public interface TimeOperations {
    void registrarTime() throws DuplicateTeamException;
    void pesquisarTime();
    void listarTimes();
    void salvarDados();
    void removerTime();
    void atualizarTime() throws IOException;
}
