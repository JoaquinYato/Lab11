public class HashC <T>{

    class Element<T>{
        Register<T> register;
        int isAvailable ;

        public Element(){
            this.register=null;
            this.isAvailable =0;
        }
    }

    Element[] table;
    int size;

    public HashC(int size){
        this.size = siguientePrimo(size);
        this.table= new Element[this.size];

        for(int i = 0; i < this.size; i++){
            table[i] = new Element();
        }
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

    private static int siguientePrimo(int n) {
        while (!esPrimo(n)) {
            n++;
        }
        return n;
    }

    private int hash(int key){
        return key % size;
    }

    public void insert(Register<T> reg){
        int key = reg.getKey();
        int index = hash(key);
        int indexpOri = index;

        do {
            if (table[index].isAvailable == 0 || table[index].isAvailable == -1) {
                table[index].register = reg;
                table[index].isAvailable = 1;
                return;
            }
            index = hash(index+1);
        } while (index != indexpOri);
    }

    public Register<T> search(int key){
        int index = hash(key);
        int indexpOri = index;

        do {
            if ((table[index].isAvailable == 1 || table[index].isAvailable == -1) && table[index].register.getKey() == key) {
                return table[index].register;
            }
            if(table[index].isAvailable == 0) break;
            index = hash(index+1);
        } while (index != indexpOri);

        return null;
    }

    public void delete(int key){
        int index = hash(key);
        int indexpOri = index;

        do {
            if (table[index].isAvailable==1 && table[index].register.getKey() == key) {
                table[index].isAvailable = -1;
                table[index].register = null;
                return;
            }
            index = hash(index+1);
        } while (index != indexpOri);
    }

    public void printTable(){
        System.out.println("Estado actual de la tabla hash:");
        System.out.println("Índice | Disponible | Clave | Datos");
        System.out.println("-------|------------|-------|-------");

        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%6d | ", i));

            if (table[i].register == null) {
                System.out.println("    SI     |   -   |   -   ");
            } else {
                String disponible = table[i].isAvailable == 0 ? "SI" : "NO";
                System.out.println(String.format("    %s     | %5d | %s",
                        disponible,
                        table[i].register.getKey(),
                        table[i].register.toString()));
            }
        }
        System.out.println();
    }
}
