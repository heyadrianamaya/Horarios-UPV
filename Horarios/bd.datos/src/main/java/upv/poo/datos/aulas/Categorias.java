package upv.poo.datos.aulas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Categorias {
    public static class Categoria{
        private int id;
        private String nombre;
        private String descripcion;

        public Categoria(int id, String nombre, String descripcion) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Categoria)) return false;
            Categoria categoria = (Categoria) o;
            return id == categoria.id &&
                    Objects.equals(nombre, categoria.nombre) &&
                    Objects.equals(descripcion, categoria.descripcion);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, nombre, descripcion);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Categoria.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("nombre='" + nombre + "'")
                    .add("descripcion='" + descripcion + "'")
                    .toString();
        }
    }
    private ArrayList<Categoria> categorias;

    public Categorias() {
        this.categorias = new ArrayList<>();
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(int id, String nombre, String descripcion){
        this.categorias.add(new Categoria(id, nombre, descripcion));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Categorias.class.getSimpleName() + "[", "]")
                .add("categorias=" + categorias)
                .toString();
    }
}
