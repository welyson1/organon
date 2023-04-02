
# Casos de teste de Caixa preta

Abaixo os casos de teste


## Caso de Teste 1: Criação de Tarefa
Objetivo: Verificar se o sistema permite ao usuário com perfil de gestor criar uma tarefa com todos os campos preenchidos corretamente.

### Passos
1. Abrir o sistema e fazer login com um usuário com perfil de gestor.
2. Clicar no botão "Criar Tarefa".
3. Preencher os campos com os seguintes valores:
4. Nome da Tarefa: "Implementação do Login".
5. Responsável: "Diego".
6. Seção: "Fazendo".
7. Importância:  "Baixa"
8. Projeto: "Sistema de Gerenciamento de Tarefas".
9. Data de Início: "2023-03-15".
10. Data de Término: "2023-03-20".
11. Descrição: "Implementar o sistema de login com autenticação de usuários".
12. Clicar no botão "Salvar".

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

## Caso de Teste 3: Delegação de Tarefa
Objetivo: Verificar se o sistema permite ao usuário com perfil de gestor delegar tarefas aos desenvolvedores.

### Passos:
1. Abrir o sistema e fazer login com um usuário com perfil de gestor.
2. Nomear uma tarefa.
3. Selecionar o responsável desejado.
4. Selecionar uma sessão.
5. Selecionar importância.
6. Selecionar um projeto.
7. Selecionar data de início 02/04
8. Selecionar data de término 03/04
9. Adicionar descrição.
10. Clicar no botão "criar".

### Entradas:
- Responsável: "diego".

### Resultados Esperados:
- Tarefa criada com suceso!

### Resultados Obtidos:
- Tarefa criada com sucesso!

## Caso de Teste 4: Edição de Tarefa pelo Gestor
Objetivo: Testar a capacidade do sistema de permitir ao usuário com perfil de gestor editar nome da tarefa, responsável, sessão, projeto, data de início, data de término e descrição das tarefas.

### Passos:
1. Acessar o sistema com perfil de gestor.
2. Selecionar uma sessão que contém uma tarefa existente.
3. Selecionar uma tarefa existente.
4. Editar o nome, responsável, sessão, projeto, data de início, data de término e descrição da tarefa.
5. Salvar as alterações.
6. Verificar se as informações foram atualizadas corretamente.

### Entradas:
- Nome da tarefa: "Desenvolvimento de funcionalidade X"
- Responsável: "Diego"
- Seção: "Fazendo"
- Importância:  "Baixa"
- Projeto: "Projeto A"
- Data de início: "10/04/2023"
- Data de término: "15/04/2023"
- Descrição: "Implementar funcionalidade X para o módulo Y"

### Resultados esperados:
- As informações da tarefa devem ser atualizadas corretamente após a edição.
- O sistema deve exibir uma mensagem informando que as informações foram salvas com sucesso.

### Resultados obtidos:
- As informações da tarefa foram atualizadas corretamente após a edição.
- O sistema exibiu uma mensagem informando que as informações foram salvas com sucesso.

## Caso de Teste 5: Edição de Tarefa pelo Desenvolvedor
Objetivo: Testar a capacidade do sistema de permitir ao usuário com perfil de desenvolvedor editar apenas a categoria sessão. Sendo o desenvolvedor impedido de editar: nome da tarefa, responsável, projeto, data de início, data de término e descrição das tarefas.

### Passos:
1. Acessar o sistema com perfil de desenvolvedor.
2. Selecionar uma tarefa existente.
3. Tentar editar o nome, responsável, sessão, projeto, data de início, data de término e descrição da tarefa(Não permitido).
4. Tentar editar o estado de sessão(permitido).
5. Salvar as alterações.
6. Verificar se apenas a sessão foi atualizada.

### Entradas:
- Sessão: "Feito"

### Resultados esperados:
O sistema deve exibir uma mensagem informando que o estado da tarefa foi editado com sucesso.

### Resultados obtidos:
O sistema exibiu uma mensagem informando que a tarefa foi editada com sucesso.


## Caso de Teste 6: Visualização de todas as tarefas
Objetivo: Testar se o sistema permite que o usuário com perfil de desenvolvedor ou gestor visualize todas as tarefas criadas em suas determinadas sessões.

### Passos:
1. Abra o software.
2. Faça login com um usuário com perfil de desenvolvedor ou gestor.
3. Clique na opção de visualização de todas as tarefas.
4. Verifique se todas as tarefas criadas em suas determinadas sessões são exibidas na tela.

### Entradas:
- Usuário com perfil de desenvolvedor ou gestor.

### Resultados esperados:
- Todas as tarefas criadas em suas suas determinadas sessões devem ser exibidas na tela.

### Resultados obtidos:
- Tarefas exibidas na tela.


## Caso de Teste 7: Exclusão de Tarefa
Objetivo: Testar se o sistema permite que o usuário com perfil de gestor exclua tarefas.

### Passos:
1. Abra o software.
2. Faça login com um usuário com perfil de gestor.
3. Selecione uma tarefa para excluir.
4. Clique na opção de exclusão de tarefas.
5. Verifique se a tarefa selecionada foi excluída com sucesso.

### Entradas:
- Usuário com perfil de gestor.
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


## Caso de Teste 9: Edição ou Exclusão de Projetos
Objetivo: Testar se o sistema permite que o usuário com perfil de gestor edite ou exclua projetos selecionando o projeto em uma lista de projetos.
Passos:
1.	Abra o software.
2.	Faça login com um usuário com perfil de gestor.
3.	Selecione a opção de edição ou exclusão de projetos.
4.	Selecione um projeto para editar ou excluir.
5.	Edite as informações do projeto ou exclua o projeto.
6.	Verifique se as alterações foram salvas com sucesso ou se o projeto foi excluído.
Entradas:
- Usuário com perfil de gestor. 
- Projeto selecionado para edição ou exclusão.

### Resultados esperados:
- As alterações no projeto devem ser salvas com sucesso ou o projeto deve ser excluído com sucesso.
### Resultados obtidos:
- As alterações no projeto foram salvas


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

