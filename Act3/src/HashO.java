import java.util.LinkedList;

public class HashO<T> {
    LinkedList<Register<T>>[] table;
    int size;

    public HashO(int size){
        this.size=size;
        this.table=new LinkedList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
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

        table[index].add(reg);
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
                    System.out.print("(" + reg.getKey() + ":" + reg.getName() + ")");
                    first = false;
                }
                System.out.println("]");
            }
        }
        System.out.println();
    }
}
