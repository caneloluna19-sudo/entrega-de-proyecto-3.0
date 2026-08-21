// Grafo dirigido y ponderado: cada curso es un vértice.
// La arista A -> B significa que A es prerrequisito de B.
// El peso puede representar dificultad, prioridad o créditos.
public class GrafoCursos {
    private static final int MAX = 50;
    private Curso[] vertices;
    private int[][] matriz;
    private int n;

    public GrafoCursos() {
        vertices = new Curso[MAX];
        matriz = new int[MAX][MAX];
        n = 0;
    }

    public int getCantidadVertices() {
        return n;
    }

    public int indiceDe(int idCurso) {
        for (int i = 0; i < n; i++) {
            if (vertices[i].getIdCurso() == idCurso) {
                return i;
            }
        }
        return -1;
    }

    public void agregarVertice(Curso curso) {
        if (n >= MAX) {
            System.out.println("No se pueden agregar más vértices al grafo.");
            return;
        }
        if (indiceDe(curso.getIdCurso()) != -1) {
            return;
        }
        vertices[n] = curso;
        for (int i = 0; i <= n; i++) {
            matriz[n][i] = 0;
            matriz[i][n] = 0;
        }
        n++;
    }

    public void eliminarVertice(int idCurso) {
        int pos = indiceDe(idCurso);
        if (pos == -1) {
            return;
        }
        for (int i = pos; i < n - 1; i++) {
            vertices[i] = vertices[i + 1];
            for (int j = 0; j < n; j++) {
                matriz[i][j] = matriz[i + 1][j];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = pos; j < n - 1; j++) {
                matriz[i][j] = matriz[i][j + 1];
            }
        }
        n--;
    }

    public boolean agregarArista(int idOrigen, int idDestino, int peso) {
        int i = indiceDe(idOrigen);
        int j = indiceDe(idDestino);
        if (i == -1 || j == -1) {
            return false;
        }
        if (i == j) {
            return false;
        }
        matriz[i][j] = peso;
        return true;
    }

    public void mostrarMatriz() {
        if (n == 0) {
            System.out.println("No hay cursos registrados para mostrar el grafo.");
            return;
        }
        System.out.println("\n--- MATRIZ DE ADYACENCIA (peso 0 = sin relación) ---");
        System.out.print("        ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%8s", vertices[j].getClave());
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%8s", vertices[i].getClave());
            for (int j = 0; j < n; j++) {
                System.out.printf("%8d", matriz[i][j]);
            }
            System.out.println();
        }
        System.out.println("\nRelaciones (origen -> destino, peso):");
        boolean hay = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] != 0) {
                    hay = true;
                    System.out.println("  " + vertices[i].getClave()
                            + " -> " + vertices[j].getClave()
                            + "  (peso " + matriz[i][j] + ")");
                }
            }
        }
        if (!hay) {
            System.out.println("  No hay relaciones registradas.");
        }
    }
}
