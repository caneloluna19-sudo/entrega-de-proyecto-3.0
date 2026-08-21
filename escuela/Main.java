import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ListaSimple listaSimple = new ListaSimple();
    private static ListaDoble listaDoble = new ListaDoble();
    private static ArbolCursos arbol = new ArbolCursos();
    private static GrafoCursos grafo = new GrafoCursos();
    private static HistorialAcciones historial = new HistorialAcciones();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean ordenadoPorId = false;

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE CURSOS UTC 3.0 =====");
            System.out.println("1.  Agregar curso");
            System.out.println("2.  Mostrar cursos");
            System.out.println("3.  Eliminar curso");
            System.out.println("4.  Inscribir estudiante");
            System.out.println("5.  Dar de baja estudiante");
            System.out.println("6.  Insertar cursos en árbol binario");
            System.out.println("7.  Buscar curso en árbol binario");
            System.out.println("8.  Mostrar recorrido inorden del árbol");
            System.out.println("9.  Crear relación entre cursos (grafo)");
            System.out.println("10. Mostrar grafo / matriz de adyacencia");
            System.out.println("11. Ordenar con Bubble Sort directo");
            System.out.println("12. Ordenar con Bubble Sort inverso");
            System.out.println("13. Ordenar con inserción directa");
            System.out.println("14. Ordenar con selección directa");
            System.out.println("15. Búsqueda secuencial");
            System.out.println("16. Búsqueda binaria");
            System.out.println("17. Mostrar historial de acciones");
            System.out.println("18. Extra: comparar pasos secuencial vs binaria");
            System.out.println("19. Extra: cursos con lugares disponibles");
            System.out.println("20. Extra: guardar historial en archivo");
            System.out.println("21. Cargar 5 cursos de prueba");
            System.out.println("22. Mostrar cursos de inicio a fin (lista doble)");
            System.out.println("23. Mostrar cursos de fin a inicio (lista doble)");
            System.out.println("24. Navegador de cursos");
            System.out.println("25. Contar cursos (recursivo)");
            System.out.println("26. Buscar curso (recursivo)");
            System.out.println("27. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1: agregarCurso(); break;
                case 2: listaSimple.mostrar(); break;
                case 3: eliminarCurso(); break;
                case 4: inscribirEstudiante(); break;
                case 5: darBajaEstudiante(); break;
                case 6: insertarEnArbol(); break;
                case 7: buscarEnArbol(); break;
                case 8: arbol.mostrarInorden(); break;
                case 9: crearRelacion(); break;
                case 10: grafo.mostrarMatriz(); break;
                case 11: ordenar("bubble-directo"); break;
                case 12: ordenar("bubble-inverso"); break;
                case 13: ordenar("insercion"); break;
                case 14: ordenar("seleccion"); break;
                case 15: busquedaSecuencial(); break;
                case 16: busquedaBinaria(); break;
                case 17: historial.mostrar(); break;
                case 18: compararBusquedas(); break;
                case 19: mostrarConCupo(); break;
                case 20:
                    historial.guardarEnArchivo("historial_acciones.txt");
                    historial.registrar("Se guardó el historial en archivo");
                    break;
                case 21: cargarPrueba(); break;
                case 22: listaDoble.mostrarInicioFin(); break;
                case 23: listaDoble.mostrarFinInicio(); break;
                case 24: listaDoble.navegador(scanner); break;
                case 25:
                    System.out.println("Total de cursos (recursivo): " + listaSimple.contarRecursivo());
                    break;
                case 26: buscarRecursivo(); break;
                case 27: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 27);
    }

    private static int leerEntero() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static void agregarCurso() {
        System.out.print("ID del curso (entero único): ");
        int id = leerEntero();
        if (listaSimple.buscarPorId(id) != null) {
            System.out.println("Error: Ya existe un curso con ese ID.");
            return;
        }

        System.out.print("Clave: ");
        String clave = scanner.nextLine().trim();
        if (clave.isEmpty()) {
            System.out.println("Error: La clave no puede estar vacía.");
            return;
        }
        if (listaSimple.buscar(clave) != null) {
            System.out.println("Error: Ya existe un curso con esa clave.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Docente: ");
        String docente = scanner.nextLine().trim();
        System.out.print("Cupo máximo: ");
        int cupo = leerEntero();
        if (cupo <= 0) {
            System.out.println("Error: El cupo debe ser mayor a 0.");
            return;
        }

        Curso curso = new Curso(id, clave, nombre, docente, cupo);
        listaSimple.agregar(curso);
        listaDoble.agregarAlFinal(curso);
        grafo.agregarVertice(curso);
        ordenadoPorId = false;
        historial.registrar("Se agregó el curso " + nombre + " (ID " + id + ")");
        System.out.println("Curso agregado correctamente.");
    }

    private static void eliminarCurso() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("Ingrese el ID del curso a eliminar: ");
        int id = leerEntero();
        Curso curso = listaSimple.buscarPorId(id);
        if (curso == null) {
            System.out.println("Error: El curso no existe.");
            return;
        }
        listaSimple.eliminar(curso.getClave());
        listaDoble.eliminar(curso.getClave());
        grafo.eliminarVertice(id);
        arbol.vaciar();
        ordenadoPorId = false;
        historial.registrar("Se eliminó el curso " + curso.getNombre());
        System.out.println("Curso eliminado del sistema. El árbol se vació; vuelva a insertar si lo necesita.");
    }

    private static void inscribirEstudiante() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("Ingrese el ID del curso: ");
        int id = leerEntero();
        Curso curso = listaSimple.buscarPorId(id);
        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }
        if (curso.getNumeroInscritos() >= curso.getCupoMaximo()) {
            System.out.println("Error: El curso ya está lleno.");
            return;
        }
        curso.setNumeroInscritos(curso.getNumeroInscritos() + 1);
        historial.registrar("Se inscribió un estudiante en " + curso.getNombre());
        System.out.println("Estudiante inscrito con éxito. Cupo: "
                + curso.getNumeroInscritos() + "/" + curso.getCupoMaximo());
    }

    private static void darBajaEstudiante() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("Ingrese el ID del curso: ");
        int id = leerEntero();
        Curso curso = listaSimple.buscarPorId(id);
        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }
        if (curso.getNumeroInscritos() == 0) {
            System.out.println("Error: No hay estudiantes inscritos en este curso.");
            return;
        }
        curso.setNumeroInscritos(curso.getNumeroInscritos() - 1);
        historial.registrar("Se dio de baja un estudiante en " + curso.getNombre());
        System.out.println("Baja realizada con éxito. Cupo: "
                + curso.getNumeroInscritos() + "/" + curso.getCupoMaximo());
    }

    private static void insertarEnArbol() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        arbol.vaciar();
        Curso[] cursos = listaSimple.toArray();
        for (int i = 0; i < cursos.length; i++) {
            arbol.insertar(cursos[i]);
        }
        historial.registrar("Se insertaron " + cursos.length + " cursos en el árbol binario");
        System.out.println("Cursos insertados en el árbol binario de búsqueda.");
    }

    private static void buscarEnArbol() {
        if (arbol.estaVacio()) {
            System.out.println("El árbol está vacío. Use la opción 6 primero.");
            return;
        }
        System.out.print("ID del curso a buscar en el árbol: ");
        int id = leerEntero();
        Curso curso = arbol.buscar(id);
        if (curso != null) {
            System.out.println("Curso encontrado en el árbol: " + curso);
            historial.registrar("Búsqueda en árbol: encontrado ID " + id);
        } else {
            System.out.println("La búsqueda no arrojó resultados. No existe un curso con ID " + id + " en el árbol.");
            historial.registrar("Búsqueda en árbol: no encontrado ID " + id);
        }
    }

    private static void crearRelacion() {
        if (listaSimple.size() < 2) {
            System.out.println("Se necesitan al menos 2 cursos para crear una relación.");
            return;
        }
        System.out.println("Relación dirigida: el origen es prerrequisito del destino.");
        System.out.print("ID del curso origen: ");
        int origen = leerEntero();
        System.out.print("ID del curso destino: ");
        int destino = leerEntero();
        System.out.print("Peso (dificultad, prioridad o créditos, entero > 0): ");
        int peso = leerEntero();
        if (peso <= 0) {
            System.out.println("Error: el peso debe ser mayor a 0.");
            return;
        }
        if (grafo.agregarArista(origen, destino, peso)) {
            historial.registrar("Relación en grafo: " + origen + " -> " + destino + " (peso " + peso + ")");
            System.out.println("Relación creada correctamente.");
        } else {
            System.out.println("No se pudo crear la relación. Verifique que ambos ID existan y sean distintos.");
        }
    }

    private static int pedirCriterio() {
        System.out.println("Criterio de ordenamiento:");
        System.out.println("1. idCurso");
        System.out.println("2. clave");
        System.out.println("3. nombre");
        System.out.println("4. cupo máximo");
        System.out.println("5. número de inscritos");
        System.out.print("Opción: ");
        int c = leerEntero();
        if (c < 1 || c > 5) {
            System.out.println("Criterio no válido. Se usará idCurso.");
            return Ordenamientos.POR_ID;
        }
        return c;
    }

    private static void ordenar(String metodo) {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        int criterio = pedirCriterio();
        Curso[] arr = Ordenamientos.copiar(listaSimple.toArray());
        String nombreMetodo;
        if (metodo.equals("bubble-directo")) {
            Ordenamientos.bubbleSortDirecto(arr, criterio);
            nombreMetodo = "Bubble Sort directo";
        } else if (metodo.equals("bubble-inverso")) {
            Ordenamientos.bubbleSortInverso(arr, criterio);
            nombreMetodo = "Bubble Sort inverso";
        } else if (metodo.equals("insercion")) {
            Ordenamientos.insercionDirecta(arr, criterio);
            nombreMetodo = "Inserción directa";
        } else {
            Ordenamientos.seleccionDirecta(arr, criterio);
            nombreMetodo = "Selección directa";
        }
        ordenadoPorId = criterio == Ordenamientos.POR_ID && !metodo.equals("bubble-inverso");
        System.out.println("\n--- " + nombreMetodo + " por " + Ordenamientos.nombreCriterio(criterio) + " ---");
        Ordenamientos.mostrar(arr);
        historial.registrar("Se ordenó con " + nombreMetodo + " por " + Ordenamientos.nombreCriterio(criterio));
    }

    private static void busquedaSecuencial() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("ID del curso a buscar: ");
        int id = leerEntero();
        Busquedas.Resultado r = Busquedas.secuencialPorId(listaSimple.toArray(), id);
        if (r.curso != null) {
            System.out.println("Curso encontrado (secuencial): " + r.curso);
            System.out.println("Comparaciones realizadas: " + r.pasos);
        } else {
            System.out.println("La búsqueda no arrojó resultados.");
            System.out.println("Comparaciones realizadas: " + r.pasos);
        }
        historial.registrar("Búsqueda secuencial de ID " + id);
    }

    private static void busquedaBinaria() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        Curso[] arr = Ordenamientos.copiar(listaSimple.toArray());
        Ordenamientos.bubbleSortDirecto(arr, Ordenamientos.POR_ID);
        ordenadoPorId = true;
        System.out.println("Los cursos se ordenaron por ID antes de la búsqueda binaria.");
        System.out.print("ID del curso a buscar: ");
        int id = leerEntero();
        Busquedas.Resultado r = Busquedas.binariaPorId(arr, id);
        if (r.curso != null) {
            System.out.println("Curso encontrado (binaria): " + r.curso);
            System.out.println("Comparaciones realizadas: " + r.pasos);
        } else {
            System.out.println("La búsqueda no arrojó resultados.");
            System.out.println("Comparaciones realizadas: " + r.pasos);
        }
        historial.registrar("Búsqueda binaria de ID " + id);
    }

    private static void compararBusquedas() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("ID del curso para comparar búsquedas: ");
        int id = leerEntero();
        Curso[] original = listaSimple.toArray();
        Busquedas.Resultado sec = Busquedas.secuencialPorId(original, id);
        Curso[] ordenado = Ordenamientos.copiar(original);
        Ordenamientos.bubbleSortDirecto(ordenado, Ordenamientos.POR_ID);
        Busquedas.Resultado bin = Busquedas.binariaPorId(ordenado, id);

        System.out.println("\n--- COMPARACIÓN DE BÚSQUEDAS (funcionalidad extra) ---");
        if (sec.curso == null) {
            System.out.println("El curso no existe. Aun así se cuentan los pasos:");
        } else {
            System.out.println("Curso: " + sec.curso.getNombre());
        }
        System.out.println("Secuencial: " + sec.pasos + " comparaciones");
        System.out.println("Binaria:    " + bin.pasos + " comparaciones");
        historial.registrar("Comparación de búsquedas para ID " + id);
    }

    private static void mostrarConCupo() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        Curso[] arr = listaSimple.toArray();
        boolean hay = false;
        System.out.println("\n--- CURSOS CON LUGARES DISPONIBLES ---");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].lugaresDisponibles() > 0) {
                hay = true;
                System.out.println(arr[i] + " | Disponibles: " + arr[i].lugaresDisponibles());
            }
        }
        if (!hay) {
            System.out.println("Todos los cursos están llenos.");
        }
    }

    private static void buscarRecursivo() {
        if (listaSimple.estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.print("Ingrese la clave a buscar: ");
        String clave = scanner.nextLine();
        Curso curso = listaSimple.buscarRecursivo(clave);
        if (curso != null) {
            System.out.println("Curso encontrado (recursivo): " + curso);
        } else {
            System.out.println("La búsqueda no arrojó resultados.");
        }
    }

    private static void cargarPrueba() {
        if (!listaSimple.estaVacia()) {
            System.out.println("Ya hay cursos. Los de prueba no se cargan para no duplicar IDs.");
            return;
        }
        Curso[] demo = {
            new Curso(50, "MAT101", "Cálculo I", "Ana López", 40),
            new Curso(20, "PRG201", "Programación I", "Luis Pérez", 35),
            new Curso(80, "EST301", "Estructura de Datos", "Marta Ruiz", 30),
            new Curso(10, "ALG110", "Álgebra", "Carlos Díaz", 45),
            new Curso(35, "BD401", "Bases de Datos", "Sofía Hernández", 25)
        };
        demo[0].setNumeroInscritos(38);
        demo[1].setNumeroInscritos(20);
        demo[2].setNumeroInscritos(30);
        demo[3].setNumeroInscritos(10);
        demo[4].setNumeroInscritos(5);
        for (int i = 0; i < demo.length; i++) {
            listaSimple.agregar(demo[i]);
            listaDoble.agregarAlFinal(demo[i]);
            grafo.agregarVertice(demo[i]);
        }
        grafo.agregarArista(10, 50, 2);
        grafo.agregarArista(20, 80, 3);
        grafo.agregarArista(50, 80, 4);
        grafo.agregarArista(80, 35, 3);
        insertarEnArbol();
        historial.registrar("Se cargaron 5 cursos de prueba y 4 relaciones en el grafo");
        System.out.println("Se cargaron 5 cursos, 4 relaciones del grafo y el árbol.");
        System.out.println("IDs: 10, 20, 35, 50, 80. Prerrequisitos: ALG110->MAT101, PRG201->EST301, MAT101->EST301, EST301->BD401.");
    }
}
