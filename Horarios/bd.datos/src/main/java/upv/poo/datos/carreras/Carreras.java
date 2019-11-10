package upv.poo.datos.carreras;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Carreras {
    public static class Carrera{
        private int id;
        private String nombre;

        public Carrera(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
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

        @Override
        public String toString() {
            return new StringJoiner(", ", Carrera.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("nombre='" + nombre + "'")
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Carrera)) return false;
            Carrera carrera = (Carrera) o;
            return id == carrera.id &&
                    Objects.equals(nombre, carrera.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, nombre);
        }
    }
    private ArrayList<Carrera> carreras;

    public Carreras() {
        this.carreras = new ArrayList<>();
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carreras)) return false;
        Carreras carreras1 = (Carreras) o;
        return Objects.equals(carreras, carreras1.carreras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carreras);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Carreras.class.getSimpleName() + "[", "]")
                .add("carreras=" + carreras)
                .toString();
    }

    public void addCarrera(int id, String nombre){
        this.carreras.add(new Carrera(id, nombre));
    }
}
