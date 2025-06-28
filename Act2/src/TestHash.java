public class TestHash {
    public static void main(String[] args) {
        // Usa SondeoLineal, por ejemplo
        HashC<String> hash = new HashC<>(13, new SondeoLineal());

        Register<String>[] registros = new Register[]{
                new Register<>(34, "Juan"),
                new Register<>(3, "Ana"),
                new Register<>(7, "Luis"),
                new Register<>(30, "Sara"),
                new Register<>(11, "Eva"),
                new Register<>(8, "Pablo"),
                new Register<>(7, "Mario"),   // Clave duplicada
                new Register<>(23, "Laura"),
                new Register<>(41, "Carlos"),
                new Register<>(16, "Pedro"),
                new Register<>(34, "Lucía")   // Clave duplicada
        };

        for (Register<String> r : registros) {
            hash.insert(r);
        }

        System.out.println("Tabla luego de inserciones:");
        hash.printTable();

        System.out.println("Eliminando la clave 30...");
        hash.delete(30);
        hash.printTable();

        System.out.println("Buscando la clave 23...");
        Register<String> r = hash.search(23);
        if (r != null) {
            System.out.println("Encontrado: " + r);
        } else {
            System.out.println("No se encontró la clave 23");
        }
    }
}
