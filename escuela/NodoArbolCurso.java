// Nodo del árbol binario de búsqueda. Guarda un Curso y sus hijos.
public class NodoArbolCurso {
    private Curso curso;
    private NodoArbolCurso izquierdo;
    private NodoArbolCurso derecho;

    public NodoArbolCurso(Curso curso) {
        this.curso = curso;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Curso getCurso() { return curso; }
    public NodoArbolCurso getIzquierdo() { return izquierdo; }
    public NodoArbolCurso getDerecho() { return derecho; }

    public void setIzquierdo(NodoArbolCurso izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoArbolCurso derecho) {
        this.derecho = derecho;
    }
}
