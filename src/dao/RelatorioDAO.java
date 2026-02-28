package dao;

import modelo.Relatorio;
import conexao.ConexaoDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    
    /**
     * Listar relatórios de um usuário
     */
    public List<Relatorio> listarPorUsuario(int idUsuario) {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM relatorio WHERE id_usuario = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                relatorio.setDataGeracao(rs.getDate("data_geracao").toLocalDate());
                relatorio.setTarefasConcluidas(rs.getInt("tarefas_concluidas"));
                relatorio.setIdUsuario(rs.getInt("id_usuario"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar relatórios: " + e.getMessage());
        }
        
        return relatorios;
    }
    
    /**
     * Inserir novo relatório
     */
    public boolean inserir(Relatorio relatorio) {
        String sql = "INSERT INTO relatorio (data_geracao, tarefas_concluidas, id_usuario) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, java.sql.Date.valueOf(relatorio.getDataGeracao()));
            stmt.setInt(2, relatorio.getTarefasConcluidas());
            stmt.setInt(3, relatorio.getIdUsuario());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir relatório: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deletar relatório
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM relatorio WHERE id_relatorio = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar relatório: " + e.getMessage());
            return false;
        }
    }
}