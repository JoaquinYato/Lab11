public class HashC {
    private static class Element{
        Register register;
        boolean isAvailable ;

        public Element(){
            this.register=null;
            this.isAvailable =true;
        }
    }

    private Element[] table;
    private int size;

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

    public void insert(Register reg){
        int key = reg.getKey();
        int index = hash(key);
        int indexpOri = index;

        do {
            if (table[index].isAvailable) {
                table[index].register = reg;
                table[index].isAvailable = false;
                return;
            }
            index = hash(index+1);
        } while (index != indexpOri);
    }

    public Register search(int key){
        int index = hash(key);
        int indexpOri = index;

        do {
            if (table[index].register != null && table[index].register.getKey() == key && !table[index].isAvailable) {
                return table[index].register;
            }
            index = hash(index+1);
        } while (index != indexpOri && table[index].register != null);

        return null;
    }

    public void delete(int key){
        int index = hash(key);
        int indexpOri = index;

        do {
            if (table[index].register != null && table[index].register.getKey() == key && !table[index].isAvailable) {
                table[index].isAvailable = true;
                return;
            }
            index = hash(index+1);
        } while (index != indexpOri && table[index].register != null);
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
                String disponible = table[i].isAvailable ? "SI" : "NO";
                System.out.println(String.format("    %s     | %5d | %s",
                        disponible,
                        table[i].register.getKey(),
                        table[i].register.toString()));
            }
        }
        System.out.println();
    }
}
