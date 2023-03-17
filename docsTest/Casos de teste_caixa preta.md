
# Casos de teste de Caixa preta

Abaixo os casos de teste


## Caso de Teste 1: Criação de Tarefa
Objetivo: Verificar se o sistema permite ao usuário com perfil de gestor criar uma tarefa com todos os campos preenchidos corretamente.

### Passos
1. Abrir o sistema e fazer login com um usuário com perfil de gestor.
2. Clicar no botão "Criar Tarefa".
3. Preencher os campos com os seguintes valores:
- Nome da Tarefa: "Implementação do Login".
- Responsável: "João da Silva".
- Seção: "Fazendo".
- Projeto: "Sistema de Gerenciamento de Tarefas".
- Data de Início: "2023-03-15".
- Data de Término: "2023-03-20".
- Descrição: "Implementar o sistema de login com autenticação de usuários".
- Clicar no botão "Salvar".

### Entradas
- Nome da Tarefa: "Implementação do Login".
- Responsável: "João da Silva".
- Seção: "Fazendo".
- Projeto: "Sistema de Gerenciamento de Tarefas".
- Data de Início: "2023-03-15".
- Data de Término: "2023-03-20".
- Descrição: "Implementar o sistema de login com autenticação de usuários".

### Resultados Esperados:
- A tarefa é criada com sucesso.
- Os campos são preenchidos corretamente.

### Resultados Obtidos:
```
Erro DAOjava.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`sql10603220`.`Tarefa`, CONSTRAINT `sessaoId` FOREIGN KEY (`sessaoId`) REFERENCES `Sessao` (`tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION)]
```

### Correção
Foi criado três linhas na tabela Sessao do banco de dados.