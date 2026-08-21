// Árbol binario de búsqueda ordenado por idCurso.
public class ArbolCursos {
    private NodoArbolCurso raiz;

    public boolean estaVacio() {
        return raiz == null;
    }

    public void vaciar() {
        raiz = null;
    }

    public void insertar(Curso curso) {
        raiz = insertarRec(raiz, curso);
    }

    private NodoArbolCurso insertarRec(NodoArbolCurso nodo, Curso curso) {
        if (nodo == null) {
            return new NodoArbolCurso(curso);
        }
        if (curso.getIdCurso() < nodo.getCurso().getIdCurso()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), curso));
        } else if (curso.getIdCurso() > nodo.getCurso().getIdCurso()) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), curso));
        }
        // Si el id ya existe, no se inserta de nuevo.
        return nodo;
    }

    public Curso buscar(int idCurso) {
        return buscarRec(raiz, idCurso);
    }

    private Curso buscarRec(NodoArbolCurso nodo, int idCurso) {
        if (nodo == null) {
            return null;
        }
        int actual = nodo.getCurso().getIdCurso();
        if (idCurso == actual) {
            return nodo.getCurso();
        }
        if (idCurso < actual) {
            return buscarRec(nodo.getIzquierdo(), idCurso);
        }
        return buscarRec(nodo.getDerecho(), idCurso);
    }

    // Recorrido inorden: cursos de menor a mayor idCurso.
    public void mostrarInorden() {
        if (estaVacio()) {
            System.out.println("El árbol está vacío. Primero inserte los cursos.");
            return;
        }
        System.out.println("\n--- Recorrido INORDEN (ordenado por ID) ---");
        inordenRec(raiz);
    }

    private void inordenRec(NodoArbolCurso nodo) {
        if (nodo != null) {
            inordenRec(nodo.getIzquierdo());
            System.out.println(nodo.getCurso());
            inordenRec(nodo.getDerecho());
        }
    }
}
