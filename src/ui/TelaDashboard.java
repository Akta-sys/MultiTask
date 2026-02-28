package ui;

import javax.swing.*;
import java.awt.*;
import util.Cores;
import util.Fonts;
import javax.swing.JPanel;

public class TelaDashboard extends JPanel {
    private AplicacaoPrincipal aplicacao;
    
    public TelaDashboard(AplicacaoPrincipal aplicacao) {
        this.aplicacao = aplicacao;
        
        setLayout(null);
        setBackground(Cores.FUNDO_PRINCIPAL);
        
        // Header
        criarHeader();
        
        // Sidebar
        criarSidebar();
        
        // Conteúdo principal
        criarConteudoPrincipal();
    }
    
    private void criarHeader() {
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(Cores.FUNDO_SECUNDARIO);
        header.setBounds(0, 0, 1440, 60);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Cores.BORDA));
        
        // Logo
        JLabel logoLabel = new JLabel("☰");
        logoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        logoLabel.setBounds(20, 20, 30, 20);
        header.add(logoLabel);
        
        // Título
        JLabel titulo = new JLabel("MULTITASK");
        titulo.setFont(Fonts.SUBTITULO);
        titulo.setForeground(Cores.PRIMARIA);
        titulo.setBounds(60, 15, 200, 30);
        header.add(titulo);
        
        // Buscar
        JTextField searchField = new JTextField("🔍 Buscar tarefas...");
        searchField.setFont(Fonts.TEXTO_NORMAL);
        searchField.setBounds(500, 12, 300, 35);
        searchField.setBackground(Cores.FUNDO_PRINCIPAL);
        searchField.setForeground(Cores.TEXTO_SECUNDARIO);
        searchField.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        header.add(searchField);
        
        // Notificação
        JLabel notif = new JLabel("🔔");
        notif.setFont(new Font("Arial", Font.PLAIN, 20));
        notif.setBounds(1250, 20, 20, 20);
        header.add(notif);
        
        // Perfil
        JLabel perfil = new JLabel("JS");
        perfil.setFont(Fonts.TEXTO_NORMAL);
        perfil.setForeground(Cores.FUNDO_SECUNDARIO);
        perfil.setBackground(Cores.PRIMARIA);
        perfil.setOpaque(true);
        perfil.setBounds(1310, 10, 40, 40);
        perfil.setHorizontalAlignment(SwingConstants.CENTER);
        perfil.setVerticalAlignment(SwingConstants.CENTER);
        header.add(perfil);
        
        // Fechar
        JButton btnFechar = new JButton("✕");
        btnFechar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnFechar.setBounds(1390, 20, 30, 20);
        btnFechar.setBorder(BorderFactory.createEmptyBorder());
        btnFechar.setBackground(Cores.FUNDO_SECUNDARIO);
        btnFechar.setForeground(Cores.TEXTO_SECUNDARIO);
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFechar.addActionListener(e -> System.exit(0));
        header.add(btnFechar);
        
        add(header);
    }
    
    private void criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(Cores.FUNDO_SECUNDARIO);
        sidebar.setBounds(0, 60, 250, 840);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Cores.BORDA));
        
        // Menu items
        String[] menuItems = {"◄ Dashboard", "📋 Tarefas", "📊 Relatórios", "⚙️ Configurações"};
        
        for (int i = 0; i < menuItems.length; i++) {
            int y = 80 + (i * 50);
            JLabel menuItem = new JLabel(menuItems[i]);
            menuItem.setFont(Fonts.TEXTO_NORMAL);
            menuItem.setForeground(i == 0 ? Cores.PRIMARIA : Cores.TEXTO_PRIMARIO);
            menuItem.setBounds(20, y, 200, 30);
            menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            int acao = i;
            menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (acao == 1) {
                        aplicacao.mostrarTelaGerenciarTarefas();
                    }
                }
            });
            
            sidebar.add(menuItem);
        }
        
        add(sidebar);
    }
    
    private void criarConteudoPrincipal() {
        // Título bem-vindo
        JLabel bemvindo = new JLabel("Bem-vindo, João!");
        bemvindo.setFont(Fonts.TITULO);
        bemvindo.setForeground(Cores.TEXTO_PRIMARIO);
        bemvindo.setBounds(1000, 80, 400, 40);
        add(bemvindo);
        
        // Cards de resumo
        criarCard(270, 130, "Tarefas Pendentes", "8", Cores.ALERTA);
        criarCard(570, 130, "Tarefas em Andamento", "3", Cores.PRIMARIA);
        criarCard(870, 130, "Tarefas Concluídas", "12", Cores.SECUNDARIA);
        
        // Card de próximas tarefas
        criarCardProximasTarefas();
        
        // Gráfico
        criarCardGrafico();
    }
    
    private void criarCard(int x, int y, String titulo, String numero, Color cor) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Cores.FUNDO_SECUNDARIO);
        card.setBounds(x, y, 280, 140);
        card.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        
        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(Fonts.TEXTO_PEQUENO);
        labelTitulo.setForeground(Cores.TEXTO_SECUNDARIO);
        labelTitulo.setBounds(15, 15, 250, 20);
        card.add(labelTitulo);
        
        JLabel labelNumero = new JLabel(numero);
        labelNumero.setFont(Fonts.NUMERO_GRANDE);
        labelNumero.setForeground(cor);
        labelNumero.setBounds(15, 45, 100, 50);
        card.add(labelNumero);
        
        // Barra de progresso
        JPanel barraFundo = new JPanel();
        barraFundo.setBackground(Cores.DESABILITADO);
        barraFundo.setBounds(15, 105, 250, 8);
        card.add(barraFundo);
        
        JPanel barraPreenchimento = new JPanel();
        barraPreenchimento.setBackground(cor);
        barraPreenchimento.setBounds(15, 105, (int)(250 * 0.4), 8);
        card.add(barraPreenchimento);
        
        add(card);
    }
    
    private void criarCardProximasTarefas() {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Cores.FUNDO_SECUNDARIO);
        card.setBounds(270, 310, 1000, 280);
        card.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        
        JLabel titulo = new JLabel("PRÓXIMAS TAREFAS (Prioridade Alta)");
        titulo.setFont(Fonts.SUBTITULO);
        titulo.setForeground(Cores.TEXTO_PRIMARIO);
        titulo.setBounds(15, 15, 300, 25);
        card.add(titulo);
        
        String[] tarefas = {
            "◆ Implementar Login",
            "◆ Criar Dashboard",
            "◆ Integrar Banco de Dados"
        };
        
        String[] status = {
            "ALTA | Até: 15/03",
            "ALTA | Até: 20/03",
            "ALTA | Até: 25/03"
        };
        
        for (int i = 0; i < tarefas.length; i++) {
            int y = 60 + (i * 60);
            
            JLabel labelTarefa = new JLabel(tarefas[i]);
            labelTarefa.setFont(Fonts.TEXTO_NORMAL);
            labelTarefa.setForeground(Cores.TEXTO_PRIMARIO);
            labelTarefa.setBounds(15, y, 400, 20);
            card.add(labelTarefa);
            
            JLabel labelStatus = new JLabel(status[i]);
            labelStatus.setFont(Fonts.TEXTO_PEQUENO);
            labelStatus.setForeground(Cores.TEXTO_SECUNDARIO);
            labelStatus.setBounds(800, y, 180, 20);
            card.add(labelStatus);
        }
        
        add(card);
    }
    
    private void criarCardGrafico() {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Cores.FUNDO_SECUNDARIO);
        card.setBounds(270, 630, 1000, 180);
        card.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        
        JLabel titulo = new JLabel("GRÁFICO DE PRODUTIVIDADE (Últimos 7 dias)");
        titulo.setFont(Fonts.SUBTITULO);
        titulo.setForeground(Cores.TEXTO_PRIMARIO);
        titulo.setBounds(15, 15, 400, 25);
        card.add(titulo);
        
        // Barras do gráfico
        int[] alturas = {40, 80, 50, 120, 70, 100, 90};
        String[] dias = {"Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom"};
        
        for (int i = 0; i < 7; i++) {
            int x = 300 + (i * 70);
            
            // Barra
            JPanel barra = new JPanel();
            barra.setBackground(Cores.PRIMARIA);
            barra.setBounds(x, 120 - alturas[i], 40, alturas[i]);
            card.add(barra);
            
            // Dia
            JLabel dia = new JLabel(dias[i]);
            dia.setFont(Fonts.TEXTO_PEQUENO);
            dia.setForeground(Cores.TEXTO_SECUNDARIO);
            dia.setBounds(x - 5, 130, 50, 20);
            dia.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(dia);
        }
        
        add(card);
    }
}