public class HashO<T> {
    public ListLinked<Register<T>>[] table;
    public int size;

    public HashO(int size){
        this.size=siguientePrimo(size);
        this.table=new ListLinked[this.size];

        for (int i = 0; i < this.size; i++) {
            table[i] = new ListLinked<>();
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
        return key%size;
    }

    public void insert(Register<T> reg){
        int index = hash(reg.getKey());

        for(int i = 0; i < table[index].size(); i++){
            if(table[index].get(i).getKey()==reg.getKey()){
                table[index].set(i, reg);
                return;
            }
        }

        table[index].agregar(reg);
    }

    public Register<T> search(int key){
        int index = hash(key);
        for(int  i = 0; i < table[index].size(); i++){
            if(table[index].get(i).getKey()==key){
                return table[index].get(i);
            }
        }
        return null;
    }

    public void delete(int key){
        int index = hash(key);

        for(int i = 0; i < table[index].size(); i++){
            if(table[index].get(i).getKey()==key){
                table[index].remove(i);
                return;
            }
        }
    }

    public void printTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTENIDO DE LA TABLA HASH ===\n");
        sb.append("Tamaño de la tabla: ").append(size).append("\n");
        sb.append("Índice | Elementos en la lista\n");
        sb.append("-------|----------------------\n");

        for (int i = 0; i < size; i++) {
            sb.append(String.format("%6d | ", i));
            ListLinked<Register<T>> lista = table[i];

            if (lista.isEmpty()) {
                sb.append("Lista vacía\n");
            } else {
                sb.append("[");
                for (int j = 0; j < lista.size(); j++) {
                    if (j > 0) sb.append(" -> ");
                    Register<T> reg = lista.get(j);
                    sb.append("(").append(reg.getKey()).append(":").append(reg.getName()).append(")");
                }
                sb.append("]\n");
            }
        }

        System.out.print(sb.toString());
    }
}
