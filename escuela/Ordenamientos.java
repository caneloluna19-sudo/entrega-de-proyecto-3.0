// Métodos de ordenamiento implementados a mano (sin Arrays.sort).
public class Ordenamientos {
    public static final int POR_ID = 1;
    public static final int POR_CLAVE = 2;
    public static final int POR_NOMBRE = 3;
    public static final int POR_CUPO = 4;
    public static final int POR_INSCRITOS = 5;

    public static Curso[] copiar(Curso[] origen) {
        Curso[] copia = new Curso[origen.length];
        for (int i = 0; i < origen.length; i++) {
            copia[i] = origen[i];
        }
        return copia;
    }

    public static int comparar(Curso a, Curso b, int criterio) {
        switch (criterio) {
            case POR_CLAVE:
                return a.getClave().compareToIgnoreCase(b.getClave());
            case POR_NOMBRE:
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            case POR_CUPO:
                return a.getCupoMaximo() - b.getCupoMaximo();
            case POR_INSCRITOS:
                return a.getNumeroInscritos() - b.getNumeroInscritos();
            case POR_ID:
            default:
                return a.getIdCurso() - b.getIdCurso();
        }
    }

    public static String nombreCriterio(int criterio) {
        switch (criterio) {
            case POR_CLAVE: return "clave";
            case POR_NOMBRE: return "nombre";
            case POR_CUPO: return "cupo máximo";
            case POR_INSCRITOS: return "número de inscritos";
            default: return "idCurso";
        }
    }

    // Bubble Sort directo (ascendente).
    public static void bubbleSortDirecto(Curso[] arr, int criterio) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(arr[j], arr[j + 1], criterio) > 0) {
                    intercambiar(arr, j, j + 1);
                }
            }
        }
    }

    // Bubble Sort inverso (descendente).
    public static void bubbleSortInverso(Curso[] arr, int criterio) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(arr[j], arr[j + 1], criterio) < 0) {
                    intercambiar(arr, j, j + 1);
                }
            }
        }
    }

    // Inserción directa.
    public static void insercionDirecta(Curso[] arr, int criterio) {
        for (int i = 1; i < arr.length; i++) {
            Curso actual = arr[i];
            int j = i - 1;
            while (j >= 0 && comparar(arr[j], actual, criterio) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = actual;
        }
    }

    // Selección directa.
    public static void seleccionDirecta(Curso[] arr, int criterio) {
        for (int i = 0; i < arr.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparar(arr[j], arr[menor], criterio) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                intercambiar(arr, i, menor);
            }
        }
    }

    private static void intercambiar(Curso[] arr, int i, int j) {
        Curso temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void mostrar(Curso[] arr) {
        if (arr.length == 0) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }
}
