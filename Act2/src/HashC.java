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
        table= new Element[size];
        this.size = size;

        for(int i = 0; i < size; i++){
            table[i] = new Element();
        }
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
