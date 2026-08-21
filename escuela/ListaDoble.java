import java.util.Scanner;

// Lista doblemente enlazada (recorrido ida y vuelta + navegador)
public class ListaDoble {
    private NodoDoble inicio;
    private NodoDoble fin;
    private NodoDoble actual; // para el navegador carrusel

    private class NodoDoble {
        Curso curso;
        NodoDoble anterior;
        NodoDoble siguiente;

        NodoDoble(Curso curso) {
            this.curso = curso;
        }
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void agregarAlFinal(Curso curso) {
        NodoDoble nuevo = new NodoDoble(curso);
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
    }

    public void eliminar(String clave) {
        NodoDoble nodo = inicio;
        while (nodo != null) {
            if (nodo.curso.getClave().equalsIgnoreCase(clave)) {
                if (nodo.anterior != null) {
                    nodo.anterior.siguiente = nodo.siguiente;
                } else {
                    inicio = nodo.siguiente;
                }
                if (nodo.siguiente != null) {
                    nodo.siguiente.anterior = nodo.anterior;
                } else {
                    fin = nodo.anterior;
                }
                return;
            }
            nodo = nodo.siguiente;
        }
    }

    public void mostrarInicioFin() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.println("--- Cursos de inicio a fin ---");
        NodoDoble nodo = inicio;
        while (nodo != null) {
            System.out.println(nodo.curso);
            nodo = nodo.siguiente;
        }
    }

    public void mostrarFinInicio() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.println("--- Cursos de fin a inicio ---");
        NodoDoble nodo = fin;
        while (nodo != null) {
            System.out.println(nodo.curso);
            nodo = nodo.anterior;
        }
    }

    // Navegador carrusel
    public void navegador(Scanner scanner) {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        actual = inicio;
        int opcion;
        do {
            System.out.println("\n===== NAVEGADOR DE CURSOS =====");
            System.out.println("1. Ver curso actual");
            System.out.println("2. Siguiente curso");
            System.out.println("3. Curso anterior");
            System.out.println("4. Salir del navegador");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Curso actual: " + actual.curso);
                    break;
                case 2:
                    if (actual.siguiente != null) {
                        actual = actual.siguiente;
                        System.out.println("Avanzaste. Curso: " + actual.curso.getNombre());
                    } else {
                        System.out.println("Ya estás en el último curso.");
                    }
                    break;
                case 3:
                    if (actual.anterior != null) {
                        actual = actual.anterior;
                        System.out.println("Regresaste. Curso: " + actual.curso.getNombre());
                    } else {
                        System.out.println("Ya estás en el primer curso.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del navegador...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}
