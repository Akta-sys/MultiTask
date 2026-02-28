package multitask;

import modelo.Usuario;
import modelo.Tarefa;
import modelo.Categoria;
import modelo.Relatorio;
import conexao.ConexaoDB;
import java.time.LocalDate;
import java.sql.*;
import ui.AplicacaoPrincipal;

public class MultiTask {
    
    public static void main(String[] args) {
                // Inicia a aplicação com interface gráfica
        new AplicacaoPrincipal();
        
        System.out.println("=== SISTEMA MULTITASK - ETAPA 1 ===\n");
        
        // Teste 1: Conexão com banco de dados
        testarConexao();
        
        // Teste 2: Protótipo de funcionalidade - Criar e exibir tarefa
        prototipoCriarTarefa();
        
        // Teste 3: Protótipo de funcionalidade - Criar e exibir relatório
        prototipoRelatorio();
        
    }
    
    /**
     * Protótipo 1: Teste de Conexão com Banco de Dados
     */
    public static void testarConexao() {
        System.out.println("--- TESTE DE CONEXÃO ---");
        Connection conn = ConexaoDB.conectar();
        
        if (conn != null) {
            System.out.println("✓ Conexão estabelecida com sucesso!\n");
            ConexaoDB.desconectar(conn);
        } else {
            System.out.println("✗ Falha ao conectar ao banco de dados!\n");
        }
    }
    
    /**
     * Protótipo 2: Criar e Exibir Tarefa
     */
    public static void prototipoCriarTarefa() {
        System.out.println("--- PROTÓTIPO: GERENCIAR TAREFA ---");
        
        // Criar uma tarefa de exemplo
        Tarefa tarefa = new Tarefa(
            1,
            "Implementar Login",
            "Criar sistema de autenticação de usuários",
            LocalDate.of(2026, 3, 15),
            "ALTA",
            "EM PROGRESSO",
            1,
            1
        );
        
        System.out.println("Tarefa criada:");
        System.out.println(tarefa);
        System.out.println();
        
        // Simular atualização de status
        System.out.println("Atualizando status da tarefa...");
        tarefa.setStatus("CONCLUÍDA");
        System.out.println("✓ Status atualizado para: " + tarefa.getStatus());
        System.out.println(tarefa);
        System.out.println();
    }
    
    /**
     * Protótipo 3: Gerar Relatório
     */
    public static void prototipoRelatorio() {
        System.out.println("--- PROTÓTIPO: GERAR RELATÓRIO ---");
        
        // Criar um usuário de exemplo
        Usuario usuario = new Usuario(
            1,
            "João Silva",
            "joao@email.com",
            "senha123",
            "COMUM"
        );
        
        System.out.println("Usuário: " + usuario.getNome());
        
        // Criar um relatório de exemplo
        Relatorio relatorio = new Relatorio(
            1,
            LocalDate.now(),
            5,
            usuario.getIdUsuario()
        );
        
        System.out.println("\nRelatório gerado em: " + relatorio.getDataGeracao());
        System.out.println("Tarefas concluídas: " + relatorio.getTarefasConcluidas());
        System.out.println(relatorio);
        System.out.println();
        
        // Protótipo de listagem de categorias
        prototipoCategorias();
    }
    
    /**
     * Protótipo 4: Gerenciar Categorias
     */
    public static void prototipoCategorias() {
        System.out.println("--- PROTÓTIPO: CATEGORIAS ---");
        
        // Criar categorias de exemplo
        Categoria[] listaCategorias = {
            new Categoria(1, "Trabalho"),
            new Categoria(2, "Pessoal"),
            new Categoria(3, "Estudos"),
            new Categoria(4, "Saúde")
        };
    
        System.out.println("Categorias disponíveis:");
        for (Categoria categoria : listaCategorias) {
            System.out.println("  • " + categoria.getNome());
        }
        System.out.println();
    }
}