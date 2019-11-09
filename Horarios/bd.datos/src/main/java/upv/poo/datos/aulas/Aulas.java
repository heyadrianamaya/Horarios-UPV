package upv.poo.datos.aulas;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Aulas {
    public static class Aula {
        String id;
        String nombre;
        String tipo;
        int capacidad;

        public Aula(String id, String nombre, String tipo, int capacidad) {
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
            this.capacidad = capacidad;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Aula.class.getSimpleName() + "[", "]")
                    .add("id='" + id + "'")
                    .add("nombre='" + nombre + "'")
                    .add("tipo='" + tipo + "'")
                    .add("capacidad=" + capacidad)
                    .toString();
        }
    }
    private ArrayList<Aula> aulas;

    public Aulas() {
        aulas = new ArrayList<>();
    }

    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(ArrayList<Aula> aulas) {
        this.aulas = aulas;
    }

    public void anadirAula(String id, String nombre, String tipo, int capacidad){
        this.aulas.add(new Aula(id, nombre, tipo, capacidad));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Aulas.class.getSimpleName() + "[", "]")
                .add("aulas=" + aulas)
                .toString();
    }
}
