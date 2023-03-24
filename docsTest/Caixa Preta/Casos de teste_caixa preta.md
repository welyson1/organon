
# Casos de teste de Caixa preta

Abaixo os casos de teste


## Caso de Teste 1: Criação de Tarefa
Objetivo: Verificar se o sistema permite ao usuário com perfil de gestor criar uma tarefa com todos os campos preenchidos corretamente.

### Passos
1. Abrir o sistema e fazer login com um usuário com perfil de gestor.
2. Clicar no botão "Criar Tarefa".
3. Preencher os campos com os seguintes valores:
- Nome da Tarefa: "Implementação do Login".
- Responsável: "Diego".
- Seção: "Fazendo".
- Importância:  "Baixa"
- Projeto: "Sistema de Gerenciamento de Tarefas".
- Data de Início: "2023-03-15".
- Data de Término: "2023-03-20".
- Descrição: "Implementar o sistema de login com autenticação de usuários".
- Clicar no botão "Salvar".

### Entradas
- Nome da Tarefa: "Implementação do Login".
- Responsável: "Diego".
- Seção: "Fazendo".
- Importância:  "Baixa"
- Projeto: "Sistema de Gerenciamento de Tarefas".
- Data de Início: "2023-03-15".
- Data de Término: "2023-03-20".
- Descrição: "Implementar o sistema de login com autenticação de usuários".

### Resultados Esperados:
- A tarefa é criada com sucesso.
- Os campos são preenchidos corretamente.

### Resultados Obtidos:
Video: [Caso de Teste 1 - Criação de Tarefa.mp4](https://github.com/welyson1/organon/blob/main/docsTest/Caixa%20Preta/Videos/Caso%20de%20Teste%201%20-%20Cria%C3%A7%C3%A3o%20de%20Tarefa.mp4)
```
Erro DAOjava.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`sql10603220`.`Tarefa`, CONSTRAINT `sessaoId` FOREIGN KEY (`sessaoId`) REFERENCES `Sessao` (`tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION)]
```

### Correção
Foram criadas três linhas na tabela Sessao do banco de dados. Seguindo a query abaixo
```SQL
INSERT INTO Sessao(tipo, nome) VALUES (1,"Fazer");
INSERT INTO Sessao(tipo, nome) VALUES (2,"Fazendo");
INSERT INTO Sessao(tipo, nome) VALUES (3,"Feito");
```

### Resultados Obtidos após correção:
- A tarefa é criada com sucesso.
- Os campos são preenchidos corretamente.
