package upv.poo.datos.usuarios;

import upv.poo.utils.TipoUsuario;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Usuarios {
    public class Usuario{
        private String clave;
        private String password;
        private TipoUsuario tipo;
        private int id_carrera;
        private String nombre;
        private String nivel;
        private String contrato;
        private String telefono;
        private int IMR;

        public Usuario(String clave, String password, TipoUsuario tipo, int id_carrera,String nombre, String nivel, String contrato, String telefono, int IMR) {
            this.clave = clave;
            this.password = password;
            this.tipo = tipo;
            this.id_carrera = id_carrera;
            this.nombre = nombre;
            this.nivel = nivel;
            this.contrato = contrato;
            this.telefono = telefono;
            this.IMR = IMR;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public TipoUsuario getTipo() {
            return tipo;
        }

        public void setTipo(TipoUsuario tipo) {
            this.tipo = tipo;
        }

        public int getId_carrera() {
            return id_carrera;
        }

        public void setId_carrera(int id_carrera) {
            this.id_carrera = id_carrera;
        }

        public String getNivel() {
            return nivel;
        }

        public void setNivel(String nivel) {
            this.nivel = nivel;
        }

        public String getContrato() {
            return contrato;
        }

        public void setContrato(String contrato) {
            this.contrato = contrato;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public int getIMR() {
            return IMR;
        }

        public void setIMR(int IMR) {
            this.IMR = IMR;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Usuario)) return false;
            Usuario usuario = (Usuario) o;
            return id_carrera == usuario.id_carrera &&
                    IMR == usuario.IMR &&
                    Objects.equals(clave, usuario.clave) &&
                    Objects.equals(password, usuario.password) &&
                    tipo == usuario.tipo &&
                    Objects.equals(nivel, usuario.nivel) &&
                    Objects.equals(contrato, usuario.contrato) &&
                    Objects.equals(telefono, usuario.telefono);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clave, password, tipo, id_carrera, nivel, contrato, telefono, IMR);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Usuario.class.getSimpleName() + "[", "]")
                    .add("clave='" + clave + "'")
                    .add("password='" + password + "'")
                    .add("tipo=" + tipo)
                    .add("id_carrera=" + id_carrera)
                    .add("nombre='" + nombre + "'")
                    .add("nivel='" + nivel + "'")
                    .add("contrato='" + contrato + "'")
                    .add("telefono='" + telefono + "'")
                    .add("IMR=" + IMR)
                    .toString();
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    private ArrayList<Usuario> usuarios;

    public Usuarios() {
        this.usuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuarios)) return false;
        Usuarios usuarios1 = (Usuarios) o;
        return Objects.equals(usuarios, usuarios1.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarios);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Usuarios.class.getSimpleName() + "[", "]")
                .add("usuarios=" + usuarios)
                .toString();
    }

    public void addUsuario(String clave, String password, TipoUsuario tipo,
                            int idCarrera, String user, String nivel, String contrato,
                            String telefono, int IMR){
        this.usuarios.add(new Usuario(clave, password, tipo, idCarrera,user, nivel,
                contrato, telefono, IMR));
    }
}
