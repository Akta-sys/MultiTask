-- Script de criação do banco de dados MultiTask

-- Criar banco de dados
CREATE DATABASE IF NOT EXISTS multitask;
USE multitask;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL,
    tipo VARCHAR(20)
);

-- Tabela de categorias
CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela de tarefas
CREATE TABLE IF NOT EXISTS tarefa (
    id_tarefa INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_entrega DATE,
    prioridade VARCHAR(20),
    status VARCHAR(20),
    id_usuario INT NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Tabela de relatórios
CREATE TABLE IF NOT EXISTS relatorio (
    id_relatorio INT PRIMARY KEY AUTO_INCREMENT,
    data_geracao DATE,
    tarefas_concluidas INT,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Inserir usuários de teste
INSERT INTO usuario (nome, email, senha, tipo) VALUES
('João Silva', 'joao@email.com', 'senha123', 'COMUM'),
('Maria Santos', 'maria@email.com', 'senha456', 'COMUM'),
('Admin User', 'admin@email.com', 'admin123', 'ADMIN');

-- Inserir categorias
INSERT INTO categoria (nome) VALUES
('Trabalho'),
('Pessoal'),
('Estudos'),
('Saúde');

-- Inserir tarefas
INSERT INTO tarefa (titulo, descricao, data_entrega, prioridade, status, id_usuario, id_categoria) VALUES
('Implementar Login', 'Criar sistema de autenticação', '2026-03-15', 'ALTA', 'PENDENTE', 1, 1),
('Criar Dashboard', 'Interface principal do sistema', '2026-03-20', 'ALTA', 'EM PROGRESSO', 1, 1),
('Testes unitários', 'Testar funcionalidades principais', '2026-03-25', 'MÉDIA', 'PENDENTE', 1, 1),
('Documentação', 'Documentar o projeto', '2026-04-01', 'BAIXA', 'CONCLUÍDA', 1, 1),
('Estudar Java', 'Aprender novos conceitos', '2026-03-30', 'ALTA', 'EM PROGRESSO', 2, 3),
('Exercício físico', 'Atividade de cardio', '2026-02-28', 'MÉDIA', 'PENDENTE', 2, 4);

-- Inserir relatórios
INSERT INTO relatorio (data_geracao, tarefas_concluidas, id_usuario) VALUES
('2026-02-27', 1, 1),
('2026-02-27', 0, 2);