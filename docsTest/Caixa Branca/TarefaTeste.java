import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TarefaTest {
    private TarefaDAO tarefaDAO;

    @Before
    public void setUp() {
        // Inicialize qualquer configuração necessária para os testes
        tarefaDAO = new TarefaDAO();
    }

    @After
    public void tearDown() {
        // Realize qualquer limpeza necessária após os testes
        tarefaDAO = null;
    }

    @Test
    public void testCriarTarefaFluxoPrincipal() {
        // Cenário
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa 1");
        tarefa.setImportancia(2);
        tarefa.setDescricao("Descrição da tarefa");
        // Preencha os outros campos da tarefa

        // Ação
        Tarefa tarefaCriada = tarefaDAO.criar_Tarefa(tarefa);

        // Verificação
        Assert.assertNotNull(tarefaCriada);
        Assert.assertNotNull(tarefaCriada.getId());
    }

    @Test(expected = SQLException.class)
    public void testCriarTarefaSQLException() throws SQLException {
        // Cenário
        Tarefa tarefa = new Tarefa();
        // Preencha os campos da tarefa

        // Simule um erro no banco de dados
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(Conexao.conexao()).thenReturn(conn);
        Mockito.doThrow(new SQLException()).when(conn).prepareStatement(Mockito.anyString());

        // Ação
        tarefaDAO.criar_Tarefa(tarefa);
    }

    @Test(expected = Exception.class)
    public void testCriarTarefaResourceCloseError() throws SQLException {
        // Cenário
        Tarefa tarefa = new Tarefa();
        // Preencha os campos da tarefa

        // Simule um erro ao fechar o PreparedStatement
        Connection conn = Mockito.mock(Connection.class);
        PreparedStatement pst = Mockito.mock(PreparedStatement.class);
        Mockito
