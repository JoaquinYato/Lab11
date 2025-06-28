public class SondeoDobleHash implements Sondeo {
    private int R; // número primo menor que size

    public SondeoDobleHash(int size) {
        this.R = getPrimoMenor(size);
    }

    private int getPrimoMenor(int n) {
        for (int i = n - 1; i >= 2; i--) {
            if (esPrimo(i)) return i;
        }
        return 1;
    }

    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public int siguienteIndice(int index, int key, int size) {
        int hash2 = R - (key % R);
        return (key + index * hash2) % size;
    }
}