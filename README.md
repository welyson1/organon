![](https://github.com/welyson1/organon/blob/main/Imagens/Icone/Nome.png?raw=true)

------------
### Funcionalidades

- Criação de tarefas;
- Bando de dados;
- Edição de tarefas.
- Tipos de usuário (Gestor 0, Desenvolvedor 1)
------------

### Protótipo
![](https://raw.githubusercontent.com/welyson1/organon/main/Imagens/Prot%C3%B3tipo/TelaBoard.png)

----

### Diagramas UML
Diagrama de classes:

---
### Instruções

- Passo 1 Crie um banco de dados no supabase.
- Passo 2 Clone esse projeto.
- Passo 3 Abra no seu editor de codigo (NetBens funciona bem).
- Passo 4 Crie o banco de dados no supabase usando o SQL Editor com a query abaixo
```SQL
CREATE TABLE IF NOT EXISTS Empregado (
  id INT NOT NULL,
  email VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  nome VARCHAR(45) NOT NULL,
  tipo INT NOT NULL,
  PRIMARY KEY (id));

  CREATE TABLE IF NOT EXISTS Sessao (
  tipo INT NOT NULL,
  nome VARCHAR(45) NULL,
  listTarefas TEXT NULL,
  PRIMARY KEY (tipo));

INSERT INTO Sessao(tipo, nome, listtarefas) VALUES (1,'Fazer', NULL);
INSERT INTO Sessao(tipo, nome, listtarefas) VALUES (2,'Fazendo', NULL);
INSERT INTO Sessao(tipo, nome, listtarefas) VALUES (3,'Feito', NULL);

CREATE TABLE IF NOT EXISTS Projeto (
  id INT NOT NULL,
  nome VARCHAR(45) NOT NULL,
  linguagem VARCHAR(45) NULL,
  descricao TEXT NOT NULL,
  devs TEXT NULL,
  repositorioGit VARCHAR(100) NULL,
  modeloProcesso VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS Tarefa (
  id INT NOT NULL,
  nome VARCHAR(45) NOT NULL ,
  importancia INT NULL,
  descricao TEXT NULL,
  dataIn DATE NOT NULL,
  dataFin DATE NOT NULL,
  responsavelId INT NOT NULL,
  sessaoId INT NOT NULL,
  projetoId INT NOT NULL,
  PRIMARY KEY (id, responsavelId, projetoId),
  INDEX responsavelId_idx (responsavelId),
  INDEX sessaoId_idx (sessaoId),
  INDEX projetoId_idx (projetoId),
  CONSTRAINT responsavelId
    FOREIGN KEY (responsavelId)
    REFERENCES Empregado (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT sessaoId
    FOREIGN KEY (sessaoId)
    REFERENCES Sessao (tipo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT projetoId
    FOREIGN KEY (projetoId)
    REFERENCES Projeto (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
```
- Passo 5 Coloque as credenciais do banco de dados supabase na classe Conexao.java

----

### Capturas de tela

Interface geral:

![](https://raw.githubusercontent.com/welyson1/organon/main/Imagens/Prot%C3%B3tipo/TelaBoard.png)

----

### Teste de software

Teste e documentação em [/docsTest](https://github.com/welyson1/organon/tree/main/docsTest)

----

### Grupo

| Nome      | Função | Github |
| --------- | ----- | ----- |
| Adeline  | SQL Banco de dados | Link |
| Henrique     |   Java BackEnd | Link |
| Patrick      |   CSS FrontEnd | Link |
| Welyson      |   JavaFX FrontEnd/Teste | Link |
| Diege      |   Teste | Link |
| Leonardo      |   Teste | Link |

----
