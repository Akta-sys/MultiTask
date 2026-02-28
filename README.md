# MultiTask - Sistema de Gerenciamento de Tarefas

## 📋 Status do Projeto
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

## 🎯 Objetivo do Software

O **MultiTask** é um sistema desktop de gerenciamento de tarefas desenvolvido em **Java** com interface gráfica intuitiva e integração com banco de dados MySQL. O objetivo é facilitar a organização e acompanhamento de tarefas pessoais, profissionais e acadêmicas, permitindo que os usuários gerenciem suas atividades de forma eficiente.

## 🛠️ Tecnologias Utilizadas

- **Java 8+** - Linguagem de programação
- **Swing** - Framework para interface gráfica (GUI)
- **MySQL** - Banco de dados relacional
- **MySQL Connector/J** - Driver JDBC para conexão com MySQL
- **NetBeans** - IDE de desenvolvimento
- **Git/GitHub** - Controle de versão

## 👥 Time de Desenvolvedores

- **Desenvolvedor:** [Seu Nome Completo]
- **Data de Início:** Fevereiro de 2026
- **Status Atual:** Etapa 5 - Versionamento com GitHub

## 📱 Funcionalidades do Sistema

### ✅ Implementadas

#### 1. **Autenticação de Usuários**
- Login com email e senha
- Validação de credenciais no banco de dados
- Sessão de usuário mantida durante a navegação

#### 2. **Dashboard**
- Visualização de resumo das tarefas
- 3 Cards informativos:
  - Tarefas Pendentes
  - Tarefas em Andamento
  - Tarefas Concluídas
- Gráfico de produtividade (últimos 7 dias)
- Lista de próximas tarefas (prioridade alta)

#### 3. **Gerenciar Tarefas**
- Visualizar todas as tarefas do usuário em tabela
- Filtrar por:
  - Categoria
  - Prioridade (ALTA, MÉDIA, BAIXA)
  - Status (Pendente, Em Progresso, Concluída)
- Editar tarefas
- Deletar tarefas

#### 4. **Criar/Editar Tarefas**
- Formulário com campos:
  - Título (obrigatório)
  - Descrição
  - Categoria
  - Prioridade
  - Data de Entrega
  - Status
- Validações de campos
- Salvar no banco de dados

#### 5. **Banco de Dados**
- 4 tabelas principais:
  - `usuario` - Dados dos usuários
  - `categoria` - Categorias de tarefas
  - `tarefa` - Tarefas do sistema
  - `relatorio` - Relatórios de produtividade

### 🔄 Camadas de Acesso a Dados (DAO)

- **UsuarioDAO** - CRUD de usuários
- **TarefaDAO** - CRUD de tarefas com filtros avançados
- **CategoriaDAO** - CRUD de categorias
- **RelatorioDAO** - CRUD de relatórios

---

## 📂 Estrutura do Projeto

---

## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior instalado
- MySQL Server rodando
- NetBeans IDE (recomendado)

### Passos para Executar

1. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/MultiTask.git
cd MultiTask