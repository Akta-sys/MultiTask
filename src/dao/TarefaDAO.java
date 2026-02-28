package dao;

import modelo.Tarefa;
import conexao.ConexaoDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    
    /**
     * Listar todas as tarefas de um usuário
     */
    public List<Tarefa> listarPorUsuario(int idUsuario) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE id_usuario = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Tarefa tarefa = construirTarefa(rs);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
        
        return tarefas;
    }
    
    /**
     * Listar tarefas por usuário e categoria
     */
    public List<Tarefa> listarPorUsuarioECategoria(int idUsuario, int idCategoria) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE id_usuario = ? AND id_categoria = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idCategoria);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Tarefa tarefa = construirTarefa(rs);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas por categoria: " + e.getMessage());
        }
        
        return tarefas;
    }
    
    /**
     * Listar tarefas por usuário e prioridade
     */
    public List<Tarefa> listarPorUsuarioEPrioridade(int idUsuario, String prioridade) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE id_usuario = ? AND prioridade = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setString(2, prioridade);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Tarefa tarefa = construirTarefa(rs);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas por prioridade: " + e.getMessage());
        }
        
        return tarefas;
    }
    
    /**
     * Listar tarefas por usuário e status
     */
    public List<Tarefa> listarPorUsuarioEStatus(int idUsuario, String status) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE id_usuario = ? AND status = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Tarefa tarefa = construirTarefa(rs);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas por status: " + e.getMessage());
        }
        
        return tarefas;
    }
    
    /**
     * Buscar tarefa por ID
     */
    public Tarefa buscarPorId(int id) {
        String sql = "SELECT * FROM tarefa WHERE id_tarefa = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return construirTarefa(rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar tarefa: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Inserir nova tarefa
     */
    public boolean inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (titulo, descricao, data_entrega, prioridade, status, id_usuario, id_categoria) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setDate(3, java.sql.Date.valueOf(tarefa.getDataEntrega()));
            stmt.setString(4, tarefa.getPrioridade());
            stmt.setString(5, tarefa.getStatus());
            stmt.setInt(6, tarefa.getIdUsuario());
            stmt.setInt(7, tarefa.getIdCategoria());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir tarefa: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Atualizar tarefa
     */
    public boolean atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET titulo = ?, descricao = ?, data_entrega = ?, " +
                     "prioridade = ?, status = ?, id_usuario = ?, id_categoria = ? WHERE id_tarefa = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setDate(3, java.sql.Date.valueOf(tarefa.getDataEntrega()));
            stmt.setString(4, tarefa.getPrioridade());
            stmt.setString(5, tarefa.getStatus());
            stmt.setInt(6, tarefa.getIdUsuario());
            stmt.setInt(7, tarefa.getIdCategoria());
            stmt.setInt(8, tarefa.getIdTarefa());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deletar tarefa
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM tarefa WHERE id_tarefa = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tarefa: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Contar tarefas por status
     */
    public int contarPorStatus(int idUsuario, String status) {
        String sql = "SELECT COUNT(*) as total FROM tarefa WHERE id_usuario = ? AND status = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao contar tarefas: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Método auxiliar para construir objeto Tarefa
     */
    private Tarefa construirTarefa(ResultSet rs) throws SQLException {
        Tarefa tarefa = new Tarefa();
        tarefa.setIdTarefa(rs.getInt("id_tarefa"));
        tarefa.setTitulo(rs.getString("titulo"));
        tarefa.setDescricao(rs.getString("descricao"));
        
        Date dataSql = rs.getDate("data_entrega");
        if (dataSql != null) {
            tarefa.setDataEntrega(dataSql.toLocalDate());
        }
        
        tarefa.setPrioridade(rs.getString("prioridade"));
        tarefa.setStatus(rs.getString("status"));
        tarefa.setIdUsuario(rs.getInt("id_usuario"));
        tarefa.setIdCategoria(rs.getInt("id_categoria"));
        
        return tarefa;
    }
}