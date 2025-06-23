public class Main {
    public static void main(String[] args) {
        HashO tabla = new HashO(5); // tamaño pequeño para forzar colisiones

        // Insertar registros
        tabla.insert(new Register(1, "A"));
        tabla.insert(new Register(6, "B"));  // colisión con clave 1 (1 % 5 == 1, 6 % 5 == 1)
        tabla.insert(new Register(11, "C")); // colisión con clave 1 también

        // Reemplazo de un registro con la misma clave
        tabla.insert(new Register(6, "B_modificado"));

        // Mostrar la tabla después de insertar
        tabla.printTable();

        // Buscar una clave
        Register buscado = tabla.search(6);
        if (buscado != null) {
            System.out.println("Encontrado: (" + buscado.getKey() + ":" + buscado.getName() + ")");
        } else {
            System.out.println("No se encontró la clave 6");
        }

        // Eliminar un elemento
        tabla.delete(6);

        // Mostrar la tabla después de eliminar
        tabla.printTable();
    }
}
