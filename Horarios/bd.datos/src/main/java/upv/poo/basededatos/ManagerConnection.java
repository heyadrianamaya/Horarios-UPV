package upv.poo.basededatos;

import upv.poo.datos.aulas.Aulas;
import upv.poo.datos.aulas.Categorias;
import upv.poo.datos.aulas.Equipos;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.grupos.Grupos;
import upv.poo.datos.login.Login;
import upv.poo.datos.materias.Materias;
import upv.poo.datos.materias.MateriasAsignada;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;
import upv.poo.datos.usuarios.Disponibilidad;
import upv.poo.datos.usuarios.Usuarios;
import upv.poo.utils.TipoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    /*
        SELECTS
     */
    //LOGIN
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
    //AULAS
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
    public Categorias getCategorias() throws SQLException {
        ResultSet resultSet = executeQuery("call getCategorias()");
        Categorias categorias = new Categorias();
        while(resultSet.next()){
            categorias.addCategoria(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        }
        return categorias;
    }
    //CARRERAS
    public Carreras getCarreras() throws SQLException{
        ResultSet resultSet = executeQuery("call getCarreras()");
        Carreras carreras = new Carreras();
        while (resultSet.next()){
            carreras.addCarrera(resultSet.getInt(1),
                    resultSet.getString(2));
        }
        return carreras;
    }
    //PLAN DE ESTUDIOS
    public PlanesDeEstudio getPlanesEstudio() throws SQLException{
        ResultSet resultSet = executeQuery("call getPlanes()");
        PlanesDeEstudio planesDeEstudio = new PlanesDeEstudio();
        while (resultSet.next()){
            planesDeEstudio.addPlan(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),
                    resultSet.getInt(4));
        }
        return planesDeEstudio;
    }
    public PlanesDeEstudio getPlanesEstudio(Usuarios.Usuario usuario) throws SQLException{
        PreparedStatement resultSet = preparedStatement("call getPlanesWithIdCarrera(?)");
        resultSet.setInt(1, usuario.getId_carrera());
        ResultSet resultSet1 = resultSet.executeQuery();
        PlanesDeEstudio planesDeEstudio = new PlanesDeEstudio();
        while (resultSet1.next()){
            planesDeEstudio.addPlan(resultSet1.getString(1),
                    resultSet1.getString(2),resultSet1.getString(3),
                    resultSet1.getInt(4));
        }
        return planesDeEstudio;
    }
    //GRUPOS
    public Grupos getGrupos(PlanesDeEstudio.PlanDeEstudio planDeEstudio) throws SQLException{
        PreparedStatement pre = preparedStatement("call getGrupos(?)");
        pre.setString(1,planDeEstudio.getClave());
        ResultSet resultSet = pre.executeQuery();
        Grupos grupos = new Grupos();
        while (resultSet.next()){
            grupos.addGrupo(resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3));
        }
        return grupos;
    }
    public Grupos getGrupos(Login login) throws SQLException {
        PreparedStatement pre = preparedStatement("call getGruposWithIdCarrera(?)");
        pre.setInt(1,login.getIdCarrera());
        ResultSet resultSet = pre.executeQuery();
        Grupos grupos = new Grupos();
        while (resultSet.next()){
            grupos.addGrupo(resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3));
        }
        return grupos;
    }
    //USUARIOS
    public Usuarios getUsuarios(Carreras.Carrera carrera) throws SQLException {
        PreparedStatement pre = preparedStatement("call getUsersProfesor(?)");
        pre.setInt(1,carrera.getId());
        return getUsuarios(pre);
    }
    public Usuarios getUsuarios(Materias.Materia materia) throws SQLException {
        PreparedStatement pre = preparedStatement("call getProfesoresAsignados(?,?)");
        pre.setString(1,materia.getClaveMateria());
        pre.setString(2, materia.getClavePlan());
        return getUsuarios(pre);
    }

    private Usuarios getUsuarios(PreparedStatement pre) throws SQLException {
        ResultSet resultSet = pre.executeQuery();
        Usuarios usuarios = new Usuarios();
        while (resultSet.next()){
            TipoUsuario tipoUsuario = TipoUsuario.SIN_AUTO;
            if (resultSet.getString(8).equals("ADMI")) tipoUsuario = TipoUsuario.ADMIN;
            else if(resultSet.getString(8).equals("DIRE")) tipoUsuario = TipoUsuario.DIRECTOR;
            else if(resultSet.getString(8).equals("PROF")) tipoUsuario = TipoUsuario.PROFESOR;
            usuarios.addUsuario(resultSet.getString(1),
                    resultSet.getString(2),
                    tipoUsuario,
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(9),
                    resultSet.getString(7),
                    resultSet.getString(5),
                    resultSet.getInt(6));
        }
        return usuarios;
    }

    //MATERIAS
    public Materias getMaterias(PlanesDeEstudio.PlanDeEstudio planDeEstudio) throws SQLException {
        PreparedStatement pre = preparedStatement("call getMaterias(?)");
        pre.setString(1,planDeEstudio.getClave());
        return getMaterias(pre);
    }
    public MateriasAsignada getMaterias(Grupos.Grupo grupo, boolean esMatutino) throws SQLException {
        PreparedStatement pre;
        if (esMatutino)
            pre = preparedStatement("call getMateriasAsignadas(?,1)");
        else
            pre = preparedStatement("call getMateriasAsignadas(?,0)");
        pre.setString(1,grupo.getClave());
        ResultSet resultSet = pre.executeQuery();
        MateriasAsignada materiasAsignada = new MateriasAsignada();
        while (resultSet.next()){
            materiasAsignada.addMateriaAsignada(
                    resultSet.getString(2),
                    resultSet.getString(11),
                    resultSet.getString(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getString(10),
                    resultSet.getString(12),
                    resultSet.getInt(13),
                    resultSet.getString(1),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return materiasAsignada;
    }
    public Materias getMaterias(Grupos.Grupo grupo) throws SQLException {
        PreparedStatement pre = preparedStatement("call getMateriasWithGrupo(?,?)");
        pre.setString(1,grupo.getClave());
        pre.setInt(2,grupo.getCuatrimestre());
        return getMaterias(pre);
    }
    private Materias getMaterias(PreparedStatement pre) throws SQLException {
        ResultSet resultSet = pre.executeQuery();
        Materias materias = new Materias();
        while (resultSet.next()){

            materias.addMateria(
                resultSet.getString(1),
                resultSet.getString(6),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(7),
                resultSet.getInt(8)
            );
        }
        return materias;
    }
    //DISPONIBILIDAD
    public Disponibilidad getDisponibilidad(Usuarios.Usuario usuario, boolean esMatutino) throws SQLException {
        PreparedStatement pre;
        if (esMatutino){
            pre  = preparedStatement("CALL getDisponibilidad(?,1)");
        }else{
            pre  = preparedStatement("CALL getDisponibilidad(?,2)");
        }
        pre.setString(1,usuario.getClave());
        ResultSet resultSet = pre.executeQuery();
        Disponibilidad disponibilidad = new Disponibilidad();
        while (resultSet.next()){
            disponibilidad.addEspacio(resultSet.getInt(1), resultSet.getInt(2));
        }
        return disponibilidad;
    }
    /*
        UPDATES
     */
    //AULAS
    public void updateAula(Aulas.Aula aula) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call updateAula(?,?,?,?)");
        preparedStatement.setString(1,aula.getId());
        preparedStatement.setString(2,aula.getNombre());
        preparedStatement.setString(3,aula.getTipo());
        preparedStatement.setInt(4,aula.getCapacidad());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("No UPDATE data", preparedStatement.toString());
        }
    }
    public void updateAulaEquipo(int id_equipo, String id_aula, int cantidad) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call updateAulaEquipo(?,?,?)");
        preparedStatement.setInt(1,id_equipo);
        preparedStatement.setString(2,id_aula);
        preparedStatement.setInt(3,cantidad);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("No UPDATE data", preparedStatement.toString());
        }
    }
    //CARRERAS
    public void updateCarrera(Carreras.Carrera carrera) throws SQLException{
        PreparedStatement preparedStatement = preparedStatement("call updateCarrera(?,?)");
        preparedStatement.setInt(1,carrera.getId());
        preparedStatement.setString(2,carrera.getNombre());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("No UPDATE data", preparedStatement.toString());
        }
    }
    //PLAN DE ESTUDIOS
    public void updatePlanEstudio(PlanesDeEstudio.PlanDeEstudio planDeEstudio) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call updatePlan(?,?,?,?)");
        preparedStatement.setString(1,planDeEstudio.getClave());
        preparedStatement.setString(2,planDeEstudio.getNombre());
        preparedStatement.setString(3,planDeEstudio.getNivel());
        preparedStatement.setInt(4,planDeEstudio.getId_carrera());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("No UPDATE data", preparedStatement.toString());
        }
    }
    //USUARIOS
    public void updateUsuario(Usuarios.Usuario usuario) throws SQLException{
        PreparedStatement preparedStatement = preparedStatement("call updateUser(?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,usuario.getClave());
        preparedStatement.setString(2,usuario.getPassword());
        if (usuario.getTipo() == TipoUsuario.ADMIN) preparedStatement.setString(3,"ADMI");
        else if(usuario.getTipo() == TipoUsuario.DIRECTOR) preparedStatement.setString(3,"DIRE");
        else if(usuario.getTipo() == TipoUsuario.PROFESOR) preparedStatement.setString(3,"PROF");
        else
            preparedStatement.setString(3,"NONE");
        preparedStatement.setInt(4,usuario.getId_carrera());
        preparedStatement.setString(5,usuario.getNombre());
        preparedStatement.setString(6,usuario.getNivel());
        preparedStatement.setString(7,usuario.getContrato());
        preparedStatement.setString(8,usuario.getTelefono());
        preparedStatement.setInt(9,usuario.getIMR());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("No UPDATE data", preparedStatement.toString());
        }
    }
    /*
        INSERT
    */
    //AULAS
    public void insertCategoria(String nombre, String descripcion) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call addCategoria(?,?)");
        preparedStatement.setString(1,nombre);
        preparedStatement.setString(2,descripcion);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", preparedStatement.toString());
        }
    }
    public void insertAula(String id, String nombre, String tipo, int capacidad) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call addAula(?,?,?,?)");
        insertIntStringStringString(id, nombre, tipo, capacidad, preparedStatement);
    }
    public void insertAulaEquipo(int id_equipo, String id_aula, int cantidad) throws SQLException{
        PreparedStatement preparedStatement = preparedStatement("CALL addAulaEquipo(?,?,?)");
        preparedStatement.setInt(1,id_equipo);
        preparedStatement.setString(2,id_aula);
        preparedStatement.setInt(3,cantidad);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected != 1){
            throw new NotExecuteQuerySQLException("NO INSERT Data", preparedStatement.toString());
        }
    }
    public void insertEquipo(String nombre, String descripcion, int id_categoria) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call addEquipo(?,?,?)");
        preparedStatement.setString(1,nombre);
        preparedStatement.setString(2,descripcion);
        preparedStatement.setInt(3,id_categoria);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", preparedStatement.toString());
        }
    }
    //CARRERAS
    public void insertCarrera(int id, String nombre) throws SQLException{
        PreparedStatement preparedStatement = preparedStatement("call addCarrera(?,?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,nombre);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", preparedStatement.toString());
        }
    }
    //PLAN DE ESTUDIOS
    public void insertPlanEstudio(String clave, String nombre, String nivel, int id_carrera) throws SQLException {
        PreparedStatement pre = preparedStatement("call addPlan(?,?,?,?)");
        insertIntStringStringString(clave, nombre, nivel, id_carrera, pre);
    }
    public void insertGrupos(String clave, int cuatrimestre, PlanesDeEstudio.PlanDeEstudio planDeEstudio) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call addGrupo(?,?,?)");
        preparedStatement.setString(1,clave);
        preparedStatement.setInt(2,cuatrimestre);
        preparedStatement.setString(3,planDeEstudio.getClave());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", preparedStatement.toString());
        }
    }
    //USER
    public void insertUsuario(String clave, String password, TipoUsuario tipo,
                           int idCarrera, String user, String nivel, String contrato,
                           String telefono, int IMR) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement("call addUser(?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,clave);
        preparedStatement.setString(2,password);
        if (tipo == TipoUsuario.ADMIN) preparedStatement.setString(3,"ADMI");
        else if(tipo == TipoUsuario.DIRECTOR) preparedStatement.setString(3,"DIRE");
        else if(tipo == TipoUsuario.PROFESOR) preparedStatement.setString(3,"PROF");
        else
            preparedStatement.setString(3,"NONE");
        preparedStatement.setInt(4,idCarrera);
        preparedStatement.setString(5,user);
        preparedStatement.setString(6,nivel);
        preparedStatement.setString(7,contrato);
        preparedStatement.setString(8,telefono);
        preparedStatement.setInt(9,IMR);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", preparedStatement.toString());
        }
    }

    /*
        DELETE
     */
    public void deleteUsuario(String clave) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(" call deleteUserProfesor(?)");
        preparedStatement.setString(1,clave);
        preparedStatement.execute();
    }
    public void deleteCarrera(int idCarrera) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(" call deleteCarrera(?)");
        preparedStatement.setInt(1,idCarrera);
        preparedStatement.execute();
    }
    /*
        OTRAS FUNCIONES
     */
    public int[] getHorasSemanalesMaximas(Usuarios.Usuario usuario) throws SQLException {
        PreparedStatement pre = preparedStatement("call getHorasSemanalesMaximas(?)");
        pre.setString(1,usuario.getClave());
        return getIntsDias(pre);
    }

    private int[] getIntsDias(PreparedStatement pre) throws SQLException {
        int[] horas = new int[5];
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()){
            horas[0] = resultSet.getInt(1);
            horas[1] = resultSet.getInt(2);
            horas[2] = resultSet.getInt(3);
            horas[3] = resultSet.getInt(4);
            horas[4] = resultSet.getInt(5);
        }
        return horas;
    }

    public int[] getHorasSemanalesTomadas(Materias.Materia materia, Usuarios.Usuario usuario) throws SQLException {
        PreparedStatement pre = preparedStatement("call getHorasSemanalesTomadas(?,?)");
        pre.setString(1,materia.getClaveMateria());
        pre.setString(2, usuario.getNombre());
        return getIntsDias(pre);
    }

    private void insertIntStringStringString(String str, String str2, String str3, int int1, PreparedStatement pre) throws SQLException {
        pre.setString(1,str);
        pre.setString(2,str2);
        pre.setString(3,str3);
        pre.setInt(4,int1);
        int rowAffected = pre.executeUpdate();
        if (rowAffected!=1){
            throw new NotExecuteQuerySQLException("No INSERT data", pre.toString());
        }
    }
}
