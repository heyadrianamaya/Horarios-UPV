package upv.poo.basededatos;

import upv.poo.datos.aulas.Aulas;
import upv.poo.datos.aulas.Equipos;
import upv.poo.datos.login.Login;
import upv.poo.utils.TipoUsuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerConnection {
    private static ManagerConnection instance;
    private static Connection connection;
    private ManagerConnection() throws SQLException, ClassNotFoundException {
        connection = ConnectionConfiguration.getInstance().getConnection();
    }
    public static ManagerConnection getInstance() throws SQLException, ClassNotFoundException {
        instance = new ManagerConnection();
        return instance;
    }
    public ResultSet executeQuery(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
    public Login getLogin(String user, String password) throws SQLException {
        ResultSet resultSet = executeQuery("call logearse('"+user+"', '"+password+"');");
        if(resultSet.next()){
            TipoUsuario tipoUsuario = TipoUsuario.SIN_AUTO;
            if (resultSet.getString(3).equals("ADMI")) tipoUsuario = TipoUsuario.ADMIN;
            else if(resultSet.getString(3).equals("DIRE")) tipoUsuario = TipoUsuario.DIRECTOR;
            else if(resultSet.getString(3).equals("PROF")) tipoUsuario = TipoUsuario.PROFESOR;
            return new Login(resultSet.getString(1), tipoUsuario,resultSet.getInt(4));
        }
        return null;
    }
    public Aulas getAulas() throws SQLException {
        ResultSet resultSet = executeQuery("call getAulas()");
        Aulas aulas = new Aulas();
        while (resultSet.next()){
            aulas.anadirAula(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),
                    resultSet.getInt(4));
        }
        return aulas;
    }
    public Equipos getEquipos() throws SQLException {
        ResultSet resultSet = executeQuery("call getEquipos()");
        Equipos equipos = new Equipos();
        while (resultSet.next()){
            equipos.anadirEquipo(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
        }
        return equipos;
    }
}
