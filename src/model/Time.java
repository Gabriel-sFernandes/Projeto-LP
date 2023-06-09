package model;

public class Time {
    private String nome;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int jogos;
    private int pontos;

    public Time(String nome, int vitorias, int derrotas, int empates) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.jogos = vitorias + derrotas + empates;
        this.pontos = (vitorias * 3) + empates;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getJogos() {
        return jogos;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Time other = (Time) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[Nome=" + nome + ", Pontos=" + pontos + ", Vitorias=" + vitorias + ", Derrotas=" + derrotas
                + ", Empates=" + empates + ", Jogos=" + jogos + "]";
    }

    public void atualizarTime(int pontos, int jogos, int vitorias, int empates, int derrotas) {
        this.setPontos(pontos);
        this.setJogos(jogos);
        this.setVitorias(vitorias);
        this.setEmpates(empates);
        this.setDerrotas(derrotas);
    }
}
