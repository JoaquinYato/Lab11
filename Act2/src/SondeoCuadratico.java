public class SondeoCuadratico implements Sondeo {
    public int siguienteIndice(int index, int key, int size) {
        return (key + index * index) % size;
    }
}