package upv.poo.datos.aulas;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;
public class Equipos{
    public static class Equipo {
        private int id;
        private String nombre;
        private String descripcion;
        private int idCategoria;

        public Equipo(int id, String nombre, String descripcion, int idCategoria) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.idCategoria = idCategoria;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Equipo)) return false;
            Equipo equipo = (Equipo) o;
            return id == equipo.id &&
                    idCategoria == equipo.idCategoria &&
                    Objects.equals(nombre, equipo.nombre) &&
                    Objects.equals(descripcion, equipo.descripcion);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, nombre, descripcion, idCategoria);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Equipo.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("nombre='" + nombre + "'")
                    .add("descripcion='" + descripcion + "'")
                    .add("idCategoria=" + idCategoria)
                    .toString();
        }
    }

    public ArrayList<Equipo> equipos;

    public Equipos() {
        this.equipos = new ArrayList<>();
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void anadirEquipo(int id, String nombre, String descripcion, int idCategoria){
        this.equipos.add(new Equipo(id, nombre, descripcion, idCategoria));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipos)) return false;
        Equipos equipos1 = (Equipos) o;
        return Objects.equals(equipos, equipos1.equipos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipos);
    }
}
