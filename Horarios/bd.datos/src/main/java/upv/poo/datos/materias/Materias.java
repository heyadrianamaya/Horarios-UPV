package upv.poo.datos.materias;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Materias {
    public static class Materia{
        private String claveMateria;
        private String clavePlan;
        private String nombre;
        private int creditos;
        private int cuatrimestre;
        private int posicion;
        private String tipoMateria;
        private int numHoras;

        public Materia(String claveMateria, String clavePlan, String nombre, int creditos, int cuatrimestre, int posicion, String tipoMateria, int numHoras) {
            this.claveMateria = claveMateria;
            this.clavePlan = clavePlan;
            this.nombre = nombre;
            this.creditos = creditos;
            this.cuatrimestre = cuatrimestre;
            this.posicion = posicion;
            this.tipoMateria = tipoMateria;
            this.numHoras = numHoras;
        }

        public String getClaveMateria() {
            return claveMateria;
        }

        public void setClaveMateria(String claveMateria) {
            this.claveMateria = claveMateria;
        }

        public String getClavePlan() {
            return clavePlan;
        }

        public void setClavePlan(String clavePlan) {
            this.clavePlan = clavePlan;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCreditos() {
            return creditos;
        }

        public void setCreditos(int creditos) {
            this.creditos = creditos;
        }

        public int getCuatrimestre() {
            return cuatrimestre;
        }

        public void setCuatrimestre(int cuatrimestre) {
            this.cuatrimestre = cuatrimestre;
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }

        public String getTipoMateria() {
            return tipoMateria;
        }

        public void setTipoMateria(String tipoMateria) {
            this.tipoMateria = tipoMateria;
        }

        public int getNumHoras() {
            return numHoras;
        }

        public void setNumHoras(int numHoras) {
            this.numHoras = numHoras;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Materia)) return false;
            Materia materia = (Materia) o;
            return creditos == materia.creditos &&
                    cuatrimestre == materia.cuatrimestre &&
                    posicion == materia.posicion &&
                    numHoras == materia.numHoras &&
                    Objects.equals(claveMateria, materia.claveMateria) &&
                    Objects.equals(clavePlan, materia.clavePlan) &&
                    Objects.equals(nombre, materia.nombre) &&
                    Objects.equals(tipoMateria, materia.tipoMateria);
        }

        @Override
        public int hashCode() {
            return Objects.hash(claveMateria, clavePlan, nombre, creditos, cuatrimestre, posicion, tipoMateria, numHoras);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Materia.class.getSimpleName() + "[", "]")
                    .add("claveMateria='" + claveMateria + "'")
                    .add("clavePlan='" + clavePlan + "'")
                    .add("nombre='" + nombre + "'")
                    .add("creditos=" + creditos)
                    .add("cuatrimestre=" + cuatrimestre)
                    .add("posicion=" + posicion)
                    .add("tipoMateria='" + tipoMateria + "'")
                    .add("numHoras=" + numHoras)
                    .toString();
        }
    }
    private ArrayList<Materia> materias;

    public Materias() {
        this.materias  = new ArrayList<>();
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Materias.class.getSimpleName() + "[", "]")
                .add("materias=" + materias)
                .toString();
    }
    public void addMateria(String claveMateria, String clavePlan, String nombreMateria, int creditos, int cuatrimestre, int posicion, String tipoMateria, int numHoras){
        this.materias.add(new Materia(claveMateria, clavePlan, nombreMateria, creditos, cuatrimestre, posicion, tipoMateria, numHoras));
    }
}
