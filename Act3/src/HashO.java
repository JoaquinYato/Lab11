import java.util.LinkedList;

public class HashO {
    private LinkedList<Register>[] table;
    private int size;

    public HashO(int size){
        this.size=size;
        this.table=new LinkedList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<Register>();
        }
    }

    private int hash(int key){
        return key%size;
    }

    private void insert(Register reg){
        int index = hash(reg.getKey());

        for(Register r : table[index]){
            if(r.getKey()==reg.getKey()){
                table[index].remove(r);
                table[index].add(reg);
                return;
            }
        }

        table[index].add(reg);
    }

    public Register search(int key){
        int index = hash(key);
        for(Register r : table[index]){
            if(r.getKey()==key){
                return r;
            }
        }
        return null;
    }

    public void delete(int key){
        int index = hash(key);

        for(Register r : table[index]){
            if(r.getKey()==key){
                table[index].remove(r);
                return;
            }
        }
    }

    public void printTable(){
        System.out.println("=== CONTENIDO DE LA TABLA HASH ===");
        System.out.println("Tamaño de la tabla: " + size);
        System.out.println("Índice | Elementos en la lista");
        System.out.println("-------|----------------------");

        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%6d | ", i));

            if (table[i].isEmpty()) {
                System.out.println("Lista vacía");
            } else {
                System.out.print("[");
                boolean first = true;
                for (Register reg : table[i]) {
                    if (!first) {
                        System.out.print(" -> ");
                    }
                    System.out.print("(" + reg.getKey() + ":" + reg.getData() + ")");
                    first = false;
                }
                System.out.println("]");
            }
        }
        System.out.println();
    }
}
