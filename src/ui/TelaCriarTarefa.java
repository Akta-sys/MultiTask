package ui;

import dao.TarefaDAO;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import modelo.Tarefa;
import util.Cores;
import util.Fonts;

public class TelaCriarTarefa extends JPanel {
    private AplicacaoPrincipal aplicacao;
    private JTextField campoTitulo;
    private JTextArea campoDescricao;
    private JTextField campoData;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboPrioridade;
    private JComboBox<String> comboStatus;
    
    public TelaCriarTarefa(AplicacaoPrincipal aplicacao) {
        this.aplicacao = aplicacao;
        
        setLayout(null);
        setBackground(Cores.FUNDO_PRINCIPAL);
        
        // Header
        criarHeader();
        
        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Cores.FUNDO_SECUNDARIO);
        painel.setBounds(300, 100, 800, 600);
        painel.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        
        // Título
        JLabel labelTitulo = new JLabel("◄ Nova Tarefa");
        labelTitulo.setFont(Fonts.TITULO);
        labelTitulo.setForeground(Cores.TEXTO_PRIMARIO);
        labelTitulo.setBounds(20, 20, 300, 30);
        painel.add(labelTitulo);
        
        // Campo Título
        criarCampo(painel, "Título *", 20, 70);
        campoTitulo = new JTextField();
        campoTitulo.setFont(Fonts.TEXTO_NORMAL);
        campoTitulo.setBounds(20, 95, 760, 40);
        campoTitulo.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        painel.add(campoTitulo);
        
        // Campo Descrição
        criarCampo(painel, "Descrição", 20, 150);
        campoDescricao = new JTextArea();
        campoDescricao.setFont(Fonts.TEXTO_NORMAL);
        campoDescricao.setBounds(20, 175, 760, 80);
        campoDescricao.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        painel.add(campoDescricao);
        
        // Campos em linha
        int yLinha = 275;
        
        // Categoria
        criarCampo(painel, "Categoria *", 20, yLinha);
        comboCategoria = criarCombo(painel, new String[]{"Selecione", "Trabalho", "Pessoal", "Estudos"}, 
                                   20, yLinha + 25, 180, 30);
        
        // Prioridade
        criarCampo(painel, "Prioridade *", 220, yLinha);
        comboPrioridade = criarCombo(painel, new String[]{"Selecione", "ALTA", "MÉDIA", "BAIXA"}, 
                                    220, yLinha + 25, 180, 30);
        
        // Data Entrega
        criarCampo(painel, "Data de Entrega *", 420, yLinha);
        campoData = criarCampoTexto(painel, "DD/MM/YYYY", 420, yLinha + 25, 180, 30);
        
        // Status
        criarCampo(painel, "Status *", 620, yLinha);
        comboStatus = criarCombo(painel, new String[]{"Pendente", "Em Progresso", "Concluída"}, 
                                620, yLinha + 25, 160, 30);
        
        // Botões
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(Fonts.BOTAO);
        btnCancelar.setForeground(Cores.FUNDO_SECUNDARIO);
        btnCancelar.setBackground(Cores.TEXTO_SECUNDARIO);
        btnCancelar.setBounds(320, 520, 150, 40);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder());
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> aplicacao.mostrarTelaGerenciarTarefas());
        painel.add(btnCancelar);
        
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setFont(Fonts.BOTAO);
        btnSalvar.setForeground(Cores.FUNDO_SECUNDARIO);
        btnSalvar.setBackground(Cores.PRIMARIA);
        btnSalvar.setBounds(530, 520, 150, 40);
        btnSalvar.setBorder(BorderFactory.createEmptyBorder());
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(e -> salvarTarefa());
        painel.add(btnSalvar);
        
        add(painel);
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
    
    private void criarCampo(JPanel painel, String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(Fonts.TEXTO_NORMAL);
        lbl.setForeground(Cores.TEXTO_PRIMARIO);
        lbl.setBounds(x, y, 200, 20);
        painel.add(lbl);
    }
    
    private JTextField criarCampoTexto(JPanel painel, String placeholder, int x, int y, int width, int height) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(Fonts.TEXTO_NORMAL);
        campo.setBounds(x, y, width, height);
        campo.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));
        painel.add(campo);
        return campo;
    }
    
    private JComboBox<String> criarCombo(JPanel painel, String[] opcoes, int x, int y, int width, int height) {
        JComboBox<String> combo = new JComboBox<>(opcoes);
        combo.setFont(Fonts.TEXTO_NORMAL);
        combo.setBounds(x, y, width, height);
        painel.add(combo);
        return combo;
    }
    
    private void salvarTarefa() {
        if (campoTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o título!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Obter dados do formulário
            String titulo = campoTitulo.getText();
            String descricao = campoDescricao.getText();
            
            // Converter data (formato DD/MM/YYYY para LocalDate)
            String[] partes = campoData.getText().split("/");
            LocalDate dataEntrega = LocalDate.of(
                Integer.parseInt(partes[2]),
                Integer.parseInt(partes[1]),
                Integer.parseInt(partes[0])
            );
            
            String prioridade = (String) comboPrioridade.getSelectedItem();
            String status = (String) comboStatus.getSelectedItem();
            
            // Obter ID da categoria e usuário
            int idCategoria = comboCategoria.getSelectedIndex(); // Simplificado
            AplicacaoPrincipal app = (AplicacaoPrincipal) aplicacao;
            int idUsuario = app.getUsuarioLogado().getIdUsuario();
            
            // Criar objeto Tarefa
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataEntrega(dataEntrega);
        tarefa.setPrioridade(prioridade);
        tarefa.setStatus(status);
        tarefa.setIdUsuario(idUsuario);
        tarefa.setIdCategoria(idCategoria);
        
        // Salvar no banco
        TarefaDAO dao = new TarefaDAO();
        boolean sucesso = dao.inserir(tarefa);
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Tarefa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            aplicacao.mostrarTelaGerenciarTarefas();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar tarefa!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }
}