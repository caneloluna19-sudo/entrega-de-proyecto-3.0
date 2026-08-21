// Lista enlazada simple para guardar los cursos
public class ListaSimple {
    private NodoSimple cabeza;

    private class NodoSimple {
        Curso curso;
        NodoSimple siguiente;

        NodoSimple(Curso curso) {
            this.curso = curso;
            this.siguiente = null;
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregar(Curso curso) {
        NodoSimple nuevo = new NodoSimple(curso);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        NodoSimple actual = cabeza;
        while (actual != null) {
            System.out.println(actual.curso);
            actual = actual.siguiente;
        }
    }

    public Curso buscar(String clave) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                return actual.curso;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Curso buscarPorId(int idCurso) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.curso.getIdCurso() == idCurso) {
                return actual.curso;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public int size() {
        return contarRecursivo();
    }

    public Curso[] toArray() {
        int n = size();
        Curso[] arr = new Curso[n];
        NodoSimple actual = cabeza;
        int i = 0;
        while (actual != null) {
            arr[i++] = actual.curso;
            actual = actual.siguiente;
        }
        return arr;
    }

    public boolean eliminar(String clave) {
        if (estaVacia()) return false;

        if (cabeza.curso.getClave().equalsIgnoreCase(clave)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        NodoSimple actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.curso.getClave().equalsIgnoreCase(clave)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    // --- Métodos recursivos ---
    public int contarRecursivo() {
        return contarRecursivo(cabeza);
    }

    private int contarRecursivo(NodoSimple nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.siguiente);
    }

    public Curso buscarRecursivo(String clave) {
        return buscarRecursivo(cabeza, clave);
    }

    private Curso buscarRecursivo(NodoSimple nodo, String clave) {
        if (nodo == null) return null;
        if (nodo.curso.getClave().equalsIgnoreCase(clave)) return nodo.curso;
        return buscarRecursivo(nodo.siguiente, clave);
    }
}
