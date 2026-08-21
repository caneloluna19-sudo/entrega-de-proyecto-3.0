// Clase base del sistema: representa un curso de la UTC.
public class Curso {
    private int idCurso;
    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int numeroInscritos;

    public Curso(int idCurso, String clave, String nombre, String docente, int cupoMaximo) {
        this.idCurso = idCurso;
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.numeroInscritos = 0;
    }

    public int getIdCurso() { return idCurso; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public String getDocente() { return docente; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getNumeroInscritos() { return numeroInscritos; }

    public void setNumeroInscritos(int numeroInscritos) {
        this.numeroInscritos = numeroInscritos;
    }

    public int lugaresDisponibles() {
        return cupoMaximo - numeroInscritos;
    }

    @Override
    public String toString() {
        return "ID: " + idCurso
                + " | Clave: " + clave
                + " | Curso: " + nombre
                + " | Docente: " + docente
                + " | Alumnos: " + numeroInscritos + "/" + cupoMaximo;
    }
}
