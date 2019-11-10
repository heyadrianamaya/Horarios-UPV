package upv.poo.basededatos;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    @Test
    void testConnection() {
        assertDoesNotThrow(()->{
            ConnectionConfiguration dataSource = ConnectionConfiguration.getInstance();

        });
    }
}