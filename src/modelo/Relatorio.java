package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Relatorio implements Serializable {
    private int idRelatorio;
    private LocalDate dataGeracao;
    private int tarefasConcluidas;
    private int idUsuario;
    
    // Construtores
    public Relatorio() {}
    
    public Relatorio(int idRelatorio, LocalDate dataGeracao, int tarefasConcluidas, int idUsuario) {
        this.idRelatorio = idRelatorio;
        this.dataGeracao = dataGeracao;
        this.tarefasConcluidas = tarefasConcluidas;
        this.idUsuario = idUsuario;
    }
    
    // Getters e Setters
    public int getIdRelatorio() {
        return idRelatorio;
    }
    
    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }
    
    public LocalDate getDataGeracao() {
        return dataGeracao;
    }
    
    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
    
    public int getTarefasConcluidas() {
        return tarefasConcluidas;
    }
    
    public void setTarefasConcluidas(int tarefasConcluidas) {
        this.tarefasConcluidas = tarefasConcluidas;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Relatorio{" +
                "idRelatorio=" + idRelatorio +
                ", dataGeracao=" + dataGeracao +
                ", tarefasConcluidas=" + tarefasConcluidas +
                ", idUsuario=" + idUsuario +
                '}';
    }
}