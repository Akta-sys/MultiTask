package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Tarefa implements Serializable {
    private int idTarefa;
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private String prioridade; // BAIXA, MÉDIA, ALTA
    private String status; // PENDENTE, EM PROGRESSO, CONCLUÍDA
    private int idUsuario;
    private int idCategoria;
    
    // Construtores
    public Tarefa() {}
    
    public Tarefa(int idTarefa, String titulo, String descricao, LocalDate dataEntrega,
                  String prioridade, String status, int idUsuario, int idCategoria) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.prioridade = prioridade;
        this.status = status;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
    }
    
    // Getters e Setters
    public int getIdTarefa() {
        return idTarefa;
    }
    
    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    public String getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Tarefa{" +
                "idTarefa=" + idTarefa +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataEntrega=" + dataEntrega +
                ", prioridade='" + prioridade + '\'' +
                ", status='" + status + '\'' +
                ", idUsuario=" + idUsuario +
                ", idCategoria=" + idCategoria +
                '}';
    }
}