package ui;

import exceptions.DuplicateTeamException;

public interface TimeOperations {
    void registrarTime() throws DuplicateTeamException;
    void searchTeam();
    void listarTimes();
    void salvarDados();
    void removerTime();
}
