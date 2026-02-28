package ui;

import dao.TarefaDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import util.Cores;
import util.Fonts;
import javax.swing.JPanel;
import modelo.Tarefa;
import modelo.Usuario;

public class TelaGerenciarTarefas extends JPanel {
    private AplicacaoPrincipal aplicacao;
    private JTable tabela;
    
    public TelaGerenciarTarefas(AplicacaoPrincipal aplicacao) {
        this.aplicacao = aplicacao;
        
        setLayout(null);
        setBackground(Cores.FUNDO_PRINCIPAL);
        
        // Header
        criarHeader();
        
        // Título
        JLabel titulo = new JLabel("◄ Tarefas");
        titulo.setFont(Fonts.TITULO);
        titulo.setForeground(Cores.TEXTO_PRIMARIO);
        titulo.setBounds(270, 80, 300, 40);
        add(titulo);
        
        // Botão nova tarefa
        JButton btnNova = new JButton("+ NOVA TAREFA");
        btnNova.setFont(Fonts.BOTAO);
        btnNova.setForeground(Cores.FUNDO_SECUNDARIO);
        btnNova.setBackground(Cores.PRIMARIA);
        btnNova.setBounds(1050, 85, 200, 35);
        btnNova.setBorder(BorderFactory.createEmptyBorder());
        btnNova.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNova.addActionListener(e -> aplicacao.mostrarTelaCriarTarefa());
        add(btnNova);
        
        // Filtros
        criarFiltros();
        
        // Tabela
        criarTabela();
    }
    
    private void criarHeader() {
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(Cores.FUNDO_SECUNDARIO);
        header.setBounds(0, 0, 1440, 60);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Cores.BORDA));
        
        JLabel logo = new JLabel("☰ MULTITASK");
        logo.setFont(Fonts.SUBTITULO);
        logo.setForeground(Cores.PRIMARIA);
        logo.setBounds(20, 15, 200, 30);
        header.add(logo);
        
        add(header);
    }
    
    private void criarFiltros() {
        JLabel labelFiltros = new JLabel("Filtros:");
        labelFiltros.setFont(Fonts.TEXTO_NORMAL);
        labelFiltros.setForeground(Cores.TEXTO_PRIMARIO);
        labelFiltros.setBounds(270, 140, 100, 20);
        add(labelFiltros);
        
        String[] opcoes = {"Todas", "Trabalho", "Pessoal", "Estudos"};
        JComboBox<String> comboCategorias = new JComboBox<>(opcoes);
        comboCategorias.setFont(Fonts.TEXTO_NORMAL);
        comboCategorias.setBounds(370, 135, 150, 30);
        add(comboCategorias);
        
        String[] prioridades = {"Todas", "ALTA", "MÉDIA", "BAIXA"};
        JComboBox<String> comboPrioridades = new JComboBox<>(prioridades);
        comboPrioridades.setFont(Fonts.TEXTO_NORMAL);
        comboPrioridades.setBounds(540, 135, 150, 30);
        add(comboPrioridades);
        
        String[] status = {"Todos", "Pendente", "Em Progresso", "Concluída"};
        JComboBox<String> comboStatus = new JComboBox<>(status);
        comboStatus.setFont(Fonts.TEXTO_NORMAL);
        comboStatus.setBounds(710, 135, 150, 30);
        add(comboStatus);
    }
    
private void criarTabela() {
    // Buscar tarefas do usuário logado no banco
    AplicacaoPrincipal app = (AplicacaoPrincipal) SwingUtilities.getWindowAncestor(this);
    Usuario usuarioLogado = app.getUsuarioLogado();
    
    TarefaDAO tarefaDAO = new TarefaDAO();
    java.util.List<Tarefa> tarefas = tarefaDAO.listarPorUsuario(usuarioLogado.getIdUsuario());
    
    // Converter para array para a tabela
    String[][] dados = new String[tarefas.size()][4];
    for (int i = 0; i < tarefas.size(); i++) {
        Tarefa t = tarefas.get(i);
        dados[i][0] = String.valueOf(t.getIdTarefa());
        dados[i][1] = t.getTitulo();
        dados[i][2] = t.getPrioridade();
        dados[i][3] = t.getStatus();
    }
    
    String[] colunas = {"ID", "Título", "Prioridade", "Status"};
    
    DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
    tabela = new JTable(modelo);
    tabela.setFont(Fonts.TEXTO_NORMAL);
    tabela.setRowHeight(30);
    tabela.setBackground(Cores.FUNDO_SECUNDARIO);
    tabela.setForeground(Cores.TEXTO_PRIMARIO);
    tabela.getTableHeader().setBackground(Cores.FUNDO_PRINCIPAL);
    tabela.getTableHeader().setForeground(Cores.TEXTO_PRIMARIO);
    tabela.getTableHeader().setFont(Fonts.BOTAO);
    
    JScrollPane scrollPane = new JScrollPane(tabela);
    scrollPane.setBounds(270, 185, 980, 400);
    scrollPane.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
    add(scrollPane);
    
    // Botões de ação
    JButton btnEditar = new JButton("Editar");
    btnEditar.setFont(Fonts.BOTAO);
    btnEditar.setForeground(Cores.FUNDO_SECUNDARIO);
    btnEditar.setBackground(Cores.PRIMARIA);
    btnEditar.setBounds(270, 610, 100, 35);
    btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnEditar.addActionListener(e -> aplicacao.mostrarTelaCriarTarefa());
    add(btnEditar);
    
    JButton btnDeletar = new JButton("Deletar");
    btnDeletar.setFont(Fonts.BOTAO);
    btnDeletar.setForeground(Cores.FUNDO_SECUNDARIO);
    btnDeletar.setBackground(Cores.ERRO);
    btnDeletar.setBounds(390, 610, 100, 35);
    btnDeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnDeletar.addActionListener(e -> {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            int idTarefa = Integer.parseInt(dados[linha][0]);
            TarefaDAO dao = new TarefaDAO();
            if (dao.deletar(idTarefa)) {
                JOptionPane.showMessageDialog(this, "Tarefa deletada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                aplicacao.mostrarTelaGerenciarTarefas();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    });
    add(btnDeletar);
    
    JButton btnVoltar = new JButton("← Voltar");
    btnVoltar.setFont(Fonts.BOTAO);
    btnVoltar.setForeground(Cores.FUNDO_SECUNDARIO);
    btnVoltar.setBackground(Cores.TEXTO_SECUNDARIO);
    btnVoltar.setBounds(1100, 610, 150, 35);
    btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnVoltar.addActionListener(e -> aplicacao.mostrarTelaDashboard());
    add(btnVoltar);
    }
}