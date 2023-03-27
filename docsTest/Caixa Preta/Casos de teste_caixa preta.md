
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

## Caso de Teste 2: Mudança de Seção da Tarefa
Objetivo: Verificar se o sistema permite ao usuário mudar a seção de uma tarefa.

### Passos:
1. Abrir o sistema e fazer login com um usuário com perfil de gestor ou desenvolvedor.
2. Selecionar uma tarefa existente.
3. Clicar no botão "Mudar Seção".
4. Selecionar a seção desejada.
5. Clicar no botão "Salvar".

### Entradas:
- Seção da Tarefa: "Fazendo".

### Resultados Esperados:
- A seção da tarefa é alterada com sucesso.

### Resultados Obtidos:
- A seção da tarefa é alterada com sucesso.

## Caso de Teste 7: Exclusão de Tarefa
Objetivo: Testar se o sistema permite que o usuário com perfil de gestor exclua tarefas.

### Passos:
1. Abra o software.
2. Faça login com um usuário com perfil de gestor.
3. Selecione uma tarefa para excluir.
4. Clique na opção de exclusão de tarefas.
5. Verifique se a tarefa selecionada foi excluída com sucesso.

### Entradas:
-Usuário com perfil de gestor.
- Tarefa selecionada para exclusão.
- Resultados esperados:
- A tarefa selecionada deve ser excluída com sucesso.

### Resultados obtidos:
- A tarefa selecionada foi excluída com sucesso.

## Caso de Teste 8: Alteração do Grau de Importância da Tarefa
Objetivo: Testar se o sistema permite que o usuário com perfil de gestor altere o grau de importância da tarefa ao editá-la.

### Passos:
1. Abra o software.
2. Faça login com um usuário com perfil de gestor.
3. Selecione uma tarefa para editar.
4. Edite o grau de importância da tarefa.
5. Salve as alterações.
6. Verifique se o grau de importância da tarefa foi alterado com sucesso.

### Entradas:
- Usuário com perfil de gestor.
- Tarefa selecionada para edição.
- Grau de importância da tarefa a ser alterado.

### Resultados esperados:
- O grau de importância da tarefa deve ser alterado com sucesso.

### Resultados obtidos:
- O grau de importância da tarefa foi alterado com sucesso.

## Caso de Teste 10: Login e Logout do Sistema

### Passos:
1. Acessar a tela de login do sistema.
2. Inserir um nome de usuário válido e uma senha válida.
3. Clicar no botão "Entrar" para fazer login no sistema.
4. Verificar se o usuário foi autenticado e redirecionado para a tela principal do sistema.
5. Clicar no botão "Sair" na barra de navegação.
6. Verificar se o usuário foi desconectado do sistema e redirecionado para a tela de login.

### Entradas:
- Nome de usuário válido.
- Senha válida.

### Resultados Esperados:
- Passo 1: A tela de login deve ser exibida corretamente.
- Passo 2: O nome de usuário e senha devem ser inseridos sem problemas.
- Passo 3: O sistema deve autenticar o usuário e redirecioná-lo para a tela principal do sistema.
- Passo 4: O usuário deve ser capaz de ver a tela principal do sistema sem problemas.
- Passo 5: O sistema deve desconectar o usuário e redirecioná-lo para a tela de login.
- Passo 6: A tela de login deve ser exibida corretamente após o logout.

### Resultados Obtidos:
- Passo 1: A tela de login foi exibida corretamente.
- Passo 2: O nome de usuário e senha foram inseridos sem problemas.
- Passo 3: O sistema autenticou o usuário e redirecionou-o para a tela principal do sistema.
- Passo 4: O usuário foi capaz de ver a tela principal do sistema sem problemas.
- Passo 5: O sistema desconectou o usuário e redirecionou-o para a tela de login.
- Passo 6: A tela de login foi exibida corretamente após o logout.

