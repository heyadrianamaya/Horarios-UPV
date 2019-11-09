package upv.poo.basededatos;

import org.junit.jupiter.api.Test;
import upv.poo.datos.aulas.Aulas;
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
//            System.out.println(aulas);
        });
    }
}