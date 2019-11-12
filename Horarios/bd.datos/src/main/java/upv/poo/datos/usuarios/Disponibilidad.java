package upv.poo.datos.usuarios;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Disponibilidad {
    public class Espacio{
        private int dia;
        private int hora;

        public Espacio(int dia, int hora) {
            this.dia = dia;
            this.hora = hora;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Espacio)) return false;
            Espacio espacio = (Espacio) o;
            return dia == espacio.dia &&
                    hora == espacio.hora;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dia, hora);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Espacio.class.getSimpleName() + "[", "]")
                    .add("dia=" + dia)
                    .add("hora=" + hora)
                    .toString();
        }
    }
    ArrayList<Espacio> espacios;

    public Disponibilidad() {
        this.espacios = new ArrayList<>();
    }

    public ArrayList<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(ArrayList<Espacio> espacios) {
        this.espacios = espacios;
    }
    public void addEspacio(int dia, int hora){
        this.espacios.add(new Espacio(dia, hora));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Disponibilidad.class.getSimpleName() + "[", "]")
                .add("espacios=" + espacios)
                .toString();
    }
}
