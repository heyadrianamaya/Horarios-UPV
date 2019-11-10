package upv.poo.datos.plandeestudios;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class PlanesDeEstudio {
    public static class PlanDeEstudio{
        private String clave;
        private String nombre;
        private String nivel;
        private int id_carrera;

        public PlanDeEstudio(String clave, String nombre, String nivel, int id_carrera) {
            this.clave = clave;
            this.nombre = nombre;
            this.nivel = nivel;
            this.id_carrera = id_carrera;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNivel() {
            return nivel;
        }

        public void setNivel(String nivel) {
            this.nivel = nivel;
        }

        public int getId_carrera() {
            return id_carrera;
        }

        public void setId_carrera(int id_carrera) {
            this.id_carrera = id_carrera;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PlanDeEstudio)) return false;
            PlanDeEstudio that = (PlanDeEstudio) o;
            return id_carrera == that.id_carrera &&
                    Objects.equals(clave, that.clave) &&
                    Objects.equals(nombre, that.nombre) &&
                    Objects.equals(nivel, that.nivel);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clave, nombre, nivel, id_carrera);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PlanDeEstudio.class.getSimpleName() + "[", "]")
                    .add("clave='" + clave + "'")
                    .add("nombre='" + nombre + "'")
                    .add("nivel='" + nivel + "'")
                    .add("id_carrera=" + id_carrera)
                    .toString();
        }
    }
    private ArrayList<PlanDeEstudio> planesDeEstudio;

    public PlanesDeEstudio() {
        this.planesDeEstudio = new ArrayList<>();
    }

    public ArrayList<PlanDeEstudio> getPlanesDeEstudio() {
        return planesDeEstudio;
    }

    public void setPlanesDeEstudio(ArrayList<PlanDeEstudio> planesDeEstudio) {
        this.planesDeEstudio = planesDeEstudio;
    }
    public void addPlan(String clave, String nombre, String nivel, int id_carrera){
        this.planesDeEstudio.add(new PlanDeEstudio(clave, nombre, nivel, id_carrera));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlanesDeEstudio.class.getSimpleName() + "[", "]")
                .add("planesDeEstudio=" + planesDeEstudio)
                .toString();
    }
}
