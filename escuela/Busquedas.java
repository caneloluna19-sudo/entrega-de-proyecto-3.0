// Búsqueda secuencial y binaria. La binaria exige un arreglo ya ordenado.
public class Busquedas {
    public static class Resultado {
        public Curso curso;
        public int pasos;
        public int posicion; // -1 si no se encontró

        public Resultado(Curso curso, int pasos, int posicion) {
            this.curso = curso;
            this.pasos = pasos;
            this.posicion = posicion;
        }
    }

    public static Resultado secuencialPorId(Curso[] arr, int id) {
        int pasos = 0;
        for (int i = 0; i < arr.length; i++) {
            pasos++;
            if (arr[i].getIdCurso() == id) {
                return new Resultado(arr[i], pasos, i);
            }
        }
        return new Resultado(null, pasos, -1);
    }

    public static Resultado secuencialPorClave(Curso[] arr, String clave) {
        int pasos = 0;
        for (int i = 0; i < arr.length; i++) {
            pasos++;
            if (arr[i].getClave().equalsIgnoreCase(clave)) {
                return new Resultado(arr[i], pasos, i);
            }
        }
        return new Resultado(null, pasos, -1);
    }

    // El arreglo DEBE estar ordenado por idCurso de forma ascendente.
    public static Resultado binariaPorId(Curso[] arr, int id) {
        int pasos = 0;
        int izq = 0;
        int der = arr.length - 1;
        while (izq <= der) {
            pasos++;
            int medio = (izq + der) / 2;
            int actual = arr[medio].getIdCurso();
            if (actual == id) {
                return new Resultado(arr[medio], pasos, medio);
            }
            if (id < actual) {
                der = medio - 1;
            } else {
                izq = medio + 1;
            }
        }
        return new Resultado(null, pasos, -1);
    }
}
