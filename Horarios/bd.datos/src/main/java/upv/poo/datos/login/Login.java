package upv.poo.datos.login;

import upv.poo.utils.TipoUsuario;

import java.sql.Connection;
import java.util.Objects;
import java.util.StringJoiner;

public class Login {
    private String usuario;
    private TipoUsuario tipoUsuario;
    private int idCarrera;

    public Login(String usuario, TipoUsuario tipoUsuario, int idCarrera) {
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.idCarrera = idCarrera;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Login.class.getSimpleName() + "[", "]")
                .add("usuario='" + usuario + "'")
                .add("tipoUsuario=" + tipoUsuario)
                .add("idCarrera=" + idCarrera)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;
        Login login = (Login) o;
        return idCarrera == login.idCarrera &&
                usuario.equals(login.usuario) &&
                tipoUsuario == login.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, tipoUsuario, idCarrera);
    }
}
