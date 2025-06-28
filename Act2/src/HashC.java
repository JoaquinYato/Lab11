public class HashC<T> {
    public static class Element<T> {
        Register<T> register;
        int isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = 0;
        }
    }

    private Element[] table;
    private int size;
    private Sondeo estrategia;

    public HashC(int size, Sondeo estrategia) {
        this.size = siguientePrimo(size);
        this.table = (Element<T>[]) new Element[this.size];
        this.estrategia = estrategia;

        for (int i = 0; i < this.size; i++) {
            table[i] = new Element<T>();
        }
    }

    public Element<T>[] getTable() {
        return table;
    }

    public int getSize() {
        return size;
    }

    private int hash(int key) {
        return key % size;
    }

    private static int siguientePrimo(int n) {
        while (!esPrimo(n)) n++;
        return n;
    }

    private static boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public void insert(Register<T> reg) {
        int key = reg.getKey();
        int indexBase = hash(key);

        for (int index = 0; index < size; index++) {
            int pos = estrategia.siguienteIndice(index, indexBase, size);
            if (table[pos].isAvailable == 0 || table[pos].isAvailable == -1) {
                table[pos].register = reg;
                table[pos].isAvailable = 1;
                return;
            }
        }
        System.out.println("Tabla llena. No se pudo insertar.");
    }

    public Register<T> search(int key) {
        int indexBase = hash(key);
        for (int index = 0; index < size; index++) {
            int pos = estrategia.siguienteIndice(index, indexBase, size);
            if (table[pos].isAvailable == 0) break;
            if (table[pos].isAvailable == 1 && table[pos].register.getKey() == key) {
                return table[pos].register;
            }
        }
        return null;
    }

    public void delete(int key) {
        int indexBase = hash(key);
        for (int index = 0; index < size; index++) {
            int pos = estrategia.siguienteIndice(index, indexBase, size);
            if (table[pos].isAvailable == 0) break;
            if (table[pos].isAvailable == 1 && table[pos].register.getKey() == key) {
                table[pos].isAvailable = -1;
                table[pos].register = null;
                return;
            }
        }
    }

    public void printTable() {
        System.out.println("Índice | Disponible | Clave | Datos");
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%6d | ", i));
            if (table[i].register == null) {
                System.out.println("   SI   |   -   |   -   ");
            } else {
                String disponible = table[i].isAvailable == 0 ? "SI" : "NO";
                System.out.println(String.format("   %s   | %5d | %s",
                        disponible,
                        table[i].register.getKey(),
                        table[i].register.toString()));
            }
        }
    }
}