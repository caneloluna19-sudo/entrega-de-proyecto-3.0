import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

// Historial con pila (parcial 1) y opción de guardarlo en un archivo de texto.
public class HistorialAcciones {
    private Stack<String> pila = new Stack<>();

    public void registrar(String accion) {
        pila.push(accion);
    }

    public boolean estaVacio() {
        return pila.isEmpty();
    }

    public void mostrar() {
        if (estaVacio()) {
            System.out.println("El historial está vacío.");
            return;
        }
        System.out.println("\n--- HISTORIAL DE ACCIONES ---");
        @SuppressWarnings("unchecked")
        Stack<String> temp = (Stack<String>) pila.clone();
        while (!temp.isEmpty()) {
            System.out.println("- " + temp.pop());
        }
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            @SuppressWarnings("unchecked")
            Stack<String> temp = (Stack<String>) pila.clone();
            Stack<String> orden = new Stack<>();
            while (!temp.isEmpty()) {
                orden.push(temp.pop());
            }
            while (!orden.isEmpty()) {
                fw.write(orden.pop() + System.lineSeparator());
            }
            System.out.println("Historial guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial: " + e.getMessage());
        }
    }
}
