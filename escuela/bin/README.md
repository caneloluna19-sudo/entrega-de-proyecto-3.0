# Sistema de Gestión de Cursos UTC 3.0

Proyecto integrador de Estructura de Datos (Parcial 3) para la UTC.
Consola en Java con POO: listas, pila, recursividad, árbol binario de búsqueda, grafo, ordenamientos y búsquedas.

## Cómo ejecutar

En la carpeta del proyecto:

```
javac *.java
java Main
```

También funciona `java SistemaCursos` (redirige al mismo menú).

## Clases

| Archivo | Función |
| --- | --- |
| `Main.java` | Menú principal |
| `Curso.java` | Entidad del curso (`idCurso`, clave, nombre, docente, cupo) |
| `ListaSimple.java` / `ListaDoble.java` | Listas del parcial 2 |
| `NodoArbolCurso.java` / `ArbolCursos.java` | Árbol binario de búsqueda por `idCurso` |
| `GrafoCursos.java` | Grafo dirigido ponderado (matriz de adyacencia) |
| `Ordenamientos.java` | Bubble directo/inverso, inserción y selección |
| `Busquedas.java` | Secuencial y binaria |
| `HistorialAcciones.java` | Pila de acciones y guardado a archivo |

## Menú mínimo del parcial 3

1. Agregar curso  
2. Mostrar cursos  
3. Eliminar curso  
4. Inscribir estudiante  
5. Dar de baja estudiante  
6. Insertar cursos en árbol binario  
7. Buscar curso en árbol binario  
8. Recorrido inorden  
9. Crear relación (grafo)  
10. Mostrar matriz de adyacencia  
11–14. Ordenamientos  
15. Búsqueda secuencial  
16. Búsqueda binaria (ordena por ID antes de buscar)  
17. Historial  
27. Salir  

## Funcionalidad extra (sección 12)

- Comparar número de pasos de búsqueda secuencial vs binaria (opción 18).
- Mostrar cursos con lugares disponibles (opción 19).
- Guardar historial en `historial_acciones.txt` (opción 20).

## Datos de prueba (opción 21)

Carga 5 cursos (IDs 10, 20, 35, 50, 80) y 4 relaciones de prerrequisito para el video de demostración.

## Validaciones

- No se permiten `idCurso` ni `clave` duplicados.
- No se inscribe si el curso está lleno.
- No se da de baja si no hay inscritos.
- Mensaje claro si una búsqueda no encuentra resultados o si no hay cursos.
