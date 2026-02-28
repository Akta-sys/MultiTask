package ui;

import javax.swing.*;
import modelo.Usuario;

public class AplicacaoPrincipal extends JFrame {
    private JPanel painelPrincipal;
    private Usuario usuarioLogado;
    
    public AplicacaoPrincipal() {
        // Configurações da janela
        setTitle("MultiTask - Sistema de Gerenciamento de Tarefas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Mostra a tela de login primeiro
        mostrarTelaLogin();
        
        setVisible(true);
    }
    
    // Getters e Setter para usuário logado
    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
    
    public void setUsuarioLogado(Usuario usuario){
        this.usuarioLogado = usuario;
    }
    
    // Método para trocar de tela
    public void mostrarTela(JPanel tela) {
        if (painelPrincipal != null) {
            remove(painelPrincipal);
        }
        painelPrincipal = tela;
        add(painelPrincipal);
        revalidate();
        repaint();
    }
    
    public void mostrarTelaLogin() {
        TelaLogin telaLogin = new TelaLogin(this);
        mostrarTela(telaLogin);
    }
    
    public void mostrarTelaDashboard() {
        TelaDashboard telaDashboard = new TelaDashboard(this);
        mostrarTela(telaDashboard);
    }
    
    public void mostrarTelaGerenciarTarefas() {
        TelaGerenciarTarefas telaGerenciar = new TelaGerenciarTarefas(this);
        mostrarTela(telaGerenciar);
    }
    
    public void mostrarTelaCriarTarefa() {
        TelaCriarTarefa telaCriar = new TelaCriarTarefa(this);
        mostrarTela(telaCriar);
    }
    
    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplicacaoPrincipal();
        });
    }
}