    public class Register<T> {
        private int key;
        private T name;

        public Register(int key, T name){
            this.key=key;
            this.name=name;
        }

        public int getKey(){return key;}

        public void  setName(T name){this.name=name;}

        public T getName() {return name;}

        public String toString(){
            return "(" + key + ", " +name+ ")";
        }
    }
