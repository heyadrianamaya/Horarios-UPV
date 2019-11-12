package upv.poo.datos.materias;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class MateriasAsignada{
    public static class MateriaAsignada extends Materias.Materia {
        private String claveGrupo;
        private int dia;
        private int hora;
        private String nombreProfesor;

            public MateriaAsignada(String claveMateria, String clavePlan, String nombre, int creditos, int cuatrimestre, String posicion, String tipoMateria, int numHoras, String claveGrupo,  int dia, int hora, String nombreProfesor) {
            super(claveMateria, clavePlan, nombre, creditos, cuatrimestre, posicion, tipoMateria, numHoras);
            this.claveGrupo = claveGrupo;
            this.dia = dia;
            this.hora = hora;
            this.nombreProfesor = nombreProfesor;
        }

        public String getClaveGrupo() {
            return claveGrupo;
        }

        public void setClaveGrupo(String claveGrupo) {
            this.claveGrupo = claveGrupo;
        }

        public int getDia() {
            return dia;
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public int getHora() {
            return hora;
        }

        public void setHora(int hora) {
            this.hora = hora;
        }

        public String getNombreProfesor() {
            return nombreProfesor;
        }

        public void setNombreProfesor(String nombreProfesor) {
            this.nombreProfesor = nombreProfesor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MateriaAsignada)) return false;
            if (!super.equals(o)) return false;
            MateriaAsignada that = (MateriaAsignada) o;
            return dia == that.dia &&
                    hora == that.hora &&
                    Objects.equals(claveGrupo, that.claveGrupo) &&
                    Objects.equals(nombreProfesor, that.nombreProfesor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), claveGrupo, dia, hora, nombreProfesor);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", MateriaAsignada.class.getSimpleName() + "[", "]")
                    .add("claveGrupo='" + claveGrupo + "'")
                    .add("dia=" + dia)
                    .add("hora=" + hora)
                    .add("nombreProfesor='" + nombreProfesor + "'")
                    .toString();
        }
    }

    private ArrayList<MateriaAsignada> materiaAsignadas;

    public MateriasAsignada() {
        this.materiaAsignadas = new ArrayList<>();
    }

    public ArrayList<MateriaAsignada> getMateriaAsignadas() {
        return materiaAsignadas;
    }

    public void setMateriaAsignadas(ArrayList<MateriaAsignada> materiaAsignadas) {
        this.materiaAsignadas = materiaAsignadas;
    }

    public void addMateriaAsignada(String claveMateria, String clavePlan, String nombre, int creditos, int cuatrimestre, String posicion, String tipoMateria, int numHoras, String claveGrupo,  int dia, int hora, String nombreProfesor){
        this.materiaAsignadas.add(new MateriaAsignada(claveMateria, clavePlan, nombre, creditos, cuatrimestre, posicion, tipoMateria, numHoras, claveGrupo, dia, hora, nombreProfesor));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MateriasAsignada.class.getSimpleName() + "[", "]")
                .add("materiaAsignadas=" + materiaAsignadas)
                .toString();
    }
}
