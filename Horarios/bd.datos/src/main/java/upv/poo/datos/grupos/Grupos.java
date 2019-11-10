package upv.poo.datos.grupos;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Grupos {
    public static class Grupo{
        private String clave;
        private int cuatrimestre;
        private String clavePlan;

        public Grupo(String clave, int cuatrimestre, String clavePlan) {
            this.clave = clave;
            this.cuatrimestre = cuatrimestre;
            this.clavePlan = clavePlan;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public int getCuatrimestre() {
            return cuatrimestre;
        }

        public void setCuatrimestre(int cuatrimestre) {
            this.cuatrimestre = cuatrimestre;
        }

        public String getClavePlan() {
            return clavePlan;
        }

        public void setClavePlan(String clavePlan) {
            this.clavePlan = clavePlan;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Grupo)) return false;
            Grupo grupo = (Grupo) o;
            return cuatrimestre == grupo.cuatrimestre &&
                    Objects.equals(clave, grupo.clave) &&
                    Objects.equals(clavePlan, grupo.clavePlan);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clave, cuatrimestre, clavePlan);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Grupo.class.getSimpleName() + "[", "]")
                    .add("clave='" + clave + "'")
                    .add("cuatrimestre=" + cuatrimestre)
                    .add("clavePlan='" + clavePlan + "'")
                    .toString();
        }
    }
    private ArrayList<Grupo> grupos;

    public Grupos() {
        this.grupos = new ArrayList<>();
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Grupos.class.getSimpleName() + "[", "]")
                .add("grupos=" + grupos)
                .toString();
    }

    public void addGrupo(String clave, int cuatrimestre, String clavePlan){
        this.grupos.add(new Grupo(clave,cuatrimestre,clavePlan));
    }
}
