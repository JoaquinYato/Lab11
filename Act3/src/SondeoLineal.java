public class SondeoLineal implements Sondeo {
    public int siguienteIndice(int index, int key, int size) {
        return (key + index) % size;
    }
}