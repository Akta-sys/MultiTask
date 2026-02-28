package ui;

import dao.UsuarioDAO;
import javax.swing.*;
import java.awt.*;
import util.Cores;
import util.Fonts;
import javax.swing.JPanel;
import modelo.Usuario;

public class TelaLogin extends JPanel {
    private AplicacaoPrincipal aplicacao;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JCheckBox checkLembrar;
    
    public TelaLogin(AplicacaoPrincipal aplicacao) {
        this.aplicacao = aplicacao;
        
        setLayout(null);
        setBackground(Cores.FUNDO_SECUNDARIO);
        
        // Painel central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(null);
        painelCentral.setBackground(Cores.FUNDO_SECUNDARIO);
        painelCentral.setBounds(400, 150, 600, 500);
        
        // Título
        JLabel labelTitulo = new JLabel("MULTITASK");
        labelTitulo.setFont(Fonts.TITULO);
        labelTitulo.setForeground(Cores.PRIMARIA);
        labelTitulo.setBounds(180, 20, 250, 40);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(labelTitulo);
        
        // Subtítulo
        JLabel labelSubtitulo = new JLabel("Sistema de Gerenciamento de Tarefas");
        labelSubtitulo.setFont(Fonts.TEXTO_PEQUENO);
        labelSubtitulo.setForeground(Cores.TEXTO_SECUNDARIO);
        labelSubtitulo.setBounds(120, 65, 360, 20);
        labelSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(labelSubtitulo);
        
        // ===== EMAIL =====
        JLabel labelEmail = new JLabel("Email ou Usuário *");
        labelEmail.setFont(Fonts.TEXTO_NORMAL);
        labelEmail.setForeground(Cores.TEXTO_PRIMARIO);
        labelEmail.setBounds(30, 110, 200, 20);
        painelCentral.add(labelEmail);
        
        campoEmail = new JTextField();
        campoEmail.setFont(Fonts.TEXTO_NORMAL);
        campoEmail.setBounds(30, 135, 540, 40);
        campoEmail.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        campoEmail.setBackground(Cores.FUNDO_SECUNDARIO);
        painelCentral.add(campoEmail);
        
        // ===== SENHA =====
        JLabel labelSenha = new JLabel("Senha *");
        labelSenha.setFont(Fonts.TEXTO_NORMAL);
        labelSenha.setForeground(Cores.TEXTO_PRIMARIO);
        labelSenha.setBounds(30, 190, 200, 20);
        painelCentral.add(labelSenha);
        
        campoSenha = new JPasswordField();
        campoSenha.setFont(Fonts.TEXTO_NORMAL);
        campoSenha.setBounds(30, 215, 540, 40);
        campoSenha.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        campoSenha.setBackground(Cores.FUNDO_SECUNDARIO);
        painelCentral.add(campoSenha);
        
        // ===== CHECKBOX LEMBRAR =====
        checkLembrar = new JCheckBox("Lembrar-me");
        checkLembrar.setFont(Fonts.TEXTO_PEQUENO);
        checkLembrar.setForeground(Cores.TEXTO_PRIMARIO);
        checkLembrar.setBackground(Cores.FUNDO_SECUNDARIO);
        checkLembrar.setBounds(30, 270, 150, 20);
        painelCentral.add(checkLembrar);
        
        // ===== BOTÃO ENTRAR =====
        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setFont(Fonts.BOTAO);
        btnEntrar.setForeground(Cores.FUNDO_SECUNDARIO);
        btnEntrar.setBackground(Cores.PRIMARIA);
        btnEntrar.setBounds(30, 310, 540, 40);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder());
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> fazerLogin());
        painelCentral.add(btnEntrar);
        
        // ===== LINK CADASTRO =====
        JLabel labelCadastro = new JLabel("Não tem conta? Cadastre-se aqui");
        labelCadastro.setFont(Fonts.TEXTO_PEQUENO);
        labelCadastro.setForeground(Cores.PRIMARIA);
        labelCadastro.setBounds(30, 370, 300, 20);
        labelCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelCentral.add(labelCadastro);
        
        add(painelCentral);
    }
    
private void fazerLogin() {
    String email = campoEmail.getText();
    String senha = new String(campoSenha.getPassword());
    
    // Validação simples
    if (email.isEmpty() || senha.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, preencha todos os campos!", 
            "Aviso", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Buscar usuário no banco de dados
    UsuarioDAO dao = new UsuarioDAO();
    Usuario usuario = dao.buscarPorEmailESenha(email, senha);
    
    if (usuario == null) {
        JOptionPane.showMessageDialog(this, 
            "Email ou senha incorretos!", 
            "Erro de Login", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Login bem-sucedido
    JOptionPane.showMessageDialog(this, 
        "Bem-vindo, " + usuario.getNome() + "!", 
        "Sucesso", 
        JOptionPane.INFORMATION_MESSAGE);
    
    // Armazenar usuário logado na aplicação
    AplicacaoPrincipal app = (AplicacaoPrincipal) SwingUtilities.getWindowAncestor(this);
    app.setUsuarioLogado(usuario);
    
    // Navega para o Dashboard
    aplicacao.mostrarTelaDashboard();
    }
}