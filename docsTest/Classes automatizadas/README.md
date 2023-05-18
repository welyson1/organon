
# Fluxo de controle de dados

Os fluxogramas abaixo exemplificam as entradas, ações e saídas para realizar os teste com jUnit.

![Criar tarefa](https://raw.githubusercontent.com/welyson1/organon/main/docsTest/Classes%20automatizadas/Criar%20tarefa.png)

### Código

Teste: Criar tarefa.

```java

public class TarefaDAOTest {
  TarefaDAO tarefaDAO = new TarefaDAO();

  @Test
  public void testCriar_Tarefa() throws Exception {
    Tarefa result = tarefaDAO.criar_Tarefa(new Tarefa("João Silva",
            "Implementar a funcionalidade de login com validação de usuários existentes no banco de dados",
            1,
            LocalDate.of(2023, Month.MAY, 1),
            LocalDate.of(2023, Month.MAY, 15),
            1,
            2,
            2,
            1));
    assertEquals(new Tarefa("João Silva",
                    "Implementar a funcionalidade de login com validação de usuários existentes no banco de dados",
                    1,
                    LocalDate.of(2023, Month.MAY, 1),
                    LocalDate.of(2023, Month.MAY, 15),
                    1,
                    2,
                    2,
                    1),
            result);
  }

  @Test
  public void testCriar_TarefaSeVazio() throws Exception {
    Tarefa result = tarefaDAO.criar_Tarefa(new Tarefa("",
            "Implementar a funcionalidade de login com validação de usuários existentes no banco de dados",
            1,
            LocalDate.of(2023, Month.MAY, 1),
            LocalDate.of(2023, Month.MAY, 15),
            1,
            2,
            2,
            1));

    Assert.assertThrows(Exception.class, () -> tarefaDAO.criar_Tarefa(result));
  }
}
```

### Log

O teste "testCriar_Tarefa" foi executado e teve êxito  ao ser comparado com o resultado esperado.
O teste "testCriar_TarerfasSeVazio" ao deixar uma informação em branco, foi direcinado para uma exceção como esperado.



![Excluir tarefa](https://raw.githubusercontent.com/welyson1/organon/main/docsTest/Classes%20automatizadas/Excluir%20tarefa.png)

### Código

Teste: Excluir tarefa.

```java
package br.com.organon.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestExcluir_Tarefa {
  TarefaDAO tarefaDAO = new TarefaDAO();

  @Test
  public void testDeleta() {
    Tarefa result = tarefaDAO.deleta(new Tarefa("nome",
            "descricao",
            0,
            LocalDate.of(2023, Month.MAY, 18),
            LocalDate.of(2023, Month.MAY, 18),
            0,
            1,
            0,
            0));
    Assert.assertEquals(new Tarefa("nome",
                    "descricao",
                    0,
                    LocalDate.of(2023, Month.MAY, 18),
                    LocalDate.of(2023, Month.MAY, 18),
                    0,
                    1,
                    0,
                    0),
            result);
  }

  @Test
  public void testDeletaSeNulo() {
    Tarefa result = tarefaDAO.deleta(new Tarefa("nome",
            "descricao",
            0,
            LocalDate.of(2023, Month.MAY, 18),
            LocalDate.of(2023, Month.MAY, 18),
            0,
            (Integer) null,
            0,
            0));
    Assert.assertNull(result);
  }
}
```

### Log

No teste "testDeleta" ao ser executado é passado um objeto "tarefa" retornando a tarefa que deletou.
Já o "testDeletaSeNulo" acaba fazendo o oposto, onde não há um retorno, pois o mesmo não deleta nada.



![Fazer Login](https://raw.githubusercontent.com/welyson1/organon/main/docsTest/Classes%20automatizadas/Login.png)

### Código

Teste: Fazer login.

```java
package br.com.organon.controller;

import br.com.organon.model.Desenvolvedor;
import br.com.organon.model.EmpregadoDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LoginControllerTest {
  @Mock
  EmpregadoDAO empDAO;
  @InjectMocks
  LoginController loginController;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void testValidateLogin() {
    when(empDAO.Login("welyson@email.com","asda32@#")).thenReturn(new Desenvolvedor());

    int result = loginController.validateLogin();
    Assert.assertEquals(1, result);
  }

  @Test
  public void testValidateLoginNull() {
    when(empDAO.Login("welyson@email.com", "")).thenReturn(null);

    int result = loginController.validateLogin();
    Assert.assertEquals(0, result);
  }
}
```

### Log

No "testValidateLogin" é passado um e-mail e senha como parâmetros,  quando o método empDão.login é invocado pelo método que está sendo testado ele retorna um objeto Desenvolvedor, fazendo com que o login seja efetuado com sucesso, retornando 1.

No "testValidateLoginNull" é passado apenas o e-mail, deixando o campo senha vazia. Quando o método empDão.login é invocado pelo método que está sendo testado ele retorna Null, fazendo com que o login não seja efetuado, retornando 0.
