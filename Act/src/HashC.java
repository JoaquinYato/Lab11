public class HashC {
    private stacj class Element{
        Register register;
        boolean isAvaliable;

        public Element(){
            this.register=null;
            this.isAvaliable=null;
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

        while(!table[index].isAvaliable){
            index = hash(index+1);

            if(indexpOri == index){
                System.out.println("Tabla llena");
                return;
            }
        }

        table[index].register = reg;
        table[index].isAvaliable = false
    }

    public Register search(int key){
        int index = hash(key);
        int indexpOri = index;

        while (table[index].register != null){
            if(table[index].register.getKey() == key && !table[index].isAvaliable){
                return table[index].register;
            }
            index=hash(index+1);

            if (indexpOri == index){
                break;
            }
        }
        return null
    }

    public void delete(int key){
        int index = hash(key);
        int indexpOri = index;

        while (table[index].register != null){
            if(table[index].register.getKey() == key && !table[index].isAvaliable){
                table[index].isAvaliable = true;
                return;
            }

            index = hash(index+1);

            if(indexpOri == index){
                break;
            }
        }
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
