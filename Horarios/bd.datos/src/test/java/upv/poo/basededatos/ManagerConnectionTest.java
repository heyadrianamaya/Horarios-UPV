package upv.poo.basededatos;

import org.junit.jupiter.api.Test;
import upv.poo.datos.aulas.Aulas;
import upv.poo.datos.aulas.Categorias;
import upv.poo.datos.aulas.Equipos;
import upv.poo.datos.login.Login;
import upv.poo.utils.TipoUsuario;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ManagerConnectionTest {

    @Test
    void testLogin() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            assertEquals(new Login("admin", TipoUsuario.ADMIN, 1),
                    managerConnection.getLogin("admin",
                            "*4ACFE3202A5FF5CF467898FC58AAB1D615029441"));
        });
    }

    @Test
    void testQuery() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.executeQuery("select 1 from dual");
        });
    }

    @Test
    void testGetAulas() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            Aulas aulas = managerConnection.getAulas();
            assertNotNull(aulas.getAulas());
        });
    }

    @Test
    void testGetEquipos() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            Equipos equipos = managerConnection.getEquipos();
            assertNotNull(equipos.getEquipos());
        });
    }
    @Test
    void testGetCategorias() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            Categorias categorias = managerConnection.getCategorias();
            assertNotNull(categorias.getCategorias());
        });
    }

    @Test
    void testUpdateAula() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            Aulas.Aula aula = new Aulas.Aula("A100","Aula Ejemplo","Lab",10);
            managerConnection.updateAula(aula);
        });
    }

    @Test
    void testInsertCategoria() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.insertCategoria("Categoria 1", "Ninguna");
        });
    }
    @Test
    void testInsertAula() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.insertAula("AL","A20","Aula",20);
        });
    }
    @Test
    void testInsertAulaEquipo() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.insertAulaEquipo(2,"AL",20);
        });
    }
    @Test
    void testUpdateAulaEquipo() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.updateAulaEquipo(2,"AL",30);
        });
    }
    @Test
    void testInsertEquipo() {
        assertDoesNotThrow(()->{
            ManagerConnection managerConnection = ManagerConnection.getInstance();
            managerConnection.insertEquipo("NOSE","AL",4);
        });
    }
}