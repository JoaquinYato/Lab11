import javax.swing.*;
import java.awt.*;

public class InterfazHashO extends JFrame {
    private HashO hash;
    private JTextArea salida;

    public InterfazHashO(int tamano) {
        this.hash = new HashO(tamano);

        setTitle("Tabla Hash con Encadenamiento");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        salida = new JTextArea();
        salida.setEditable(false);
        salida.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(salida), BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(e -> insertar());

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscar());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminar());

        botones.add(btnInsertar);
        botones.add(btnBuscar);
        botones.add(btnEliminar);
        add(botones, BorderLayout.SOUTH);

        actualizarVista();
        setVisible(true);
    }

    private void insertar() {
        try {
            String claveStr = JOptionPane.showInputDialog(this, "Ingrese la clave (entero):");
            String valor = JOptionPane.showInputDialog(this, "Ingrese el dato:");
            if (claveStr != null && valor != null) {
                int clave = Integer.parseInt(claveStr.trim());
                hash.insert(new Register(clave, valor.trim()));
                actualizarVista();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Clave inválida.");
        }
    }

    private void buscar() {
        try {
            String claveStr = JOptionPane.showInputDialog(this, "Ingrese la clave a buscar:");
            if (claveStr != null) {
                int clave = Integer.parseInt(claveStr.trim());
                Register r = hash.search(clave);
                if (r != null) {
                    JOptionPane.showMessageDialog(this, "Encontrado: (" + r.getKey() + ":" + r.getData() + ")");
                } else {
                    JOptionPane.showMessageDialog(this, "Clave no encontrada.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Clave inválida.");
        }
    }

    private void eliminar() {
        try {
            String claveStr = JOptionPane.showInputDialog(this, "Ingrese la clave a eliminar:");
            if (claveStr != null) {
                int clave = Integer.parseInt(claveStr.trim());
                hash.delete(clave);
                actualizarVista();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Clave inválida.");
        }
    }

    private void actualizarVista() {
        // Capturar la salida del método printTable() en el área de texto
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTENIDO DE LA TABLA HASH ===\n");
        sb.append("Tamaño de la tabla: ").append(hash.size).append("\n");
        sb.append("Índice | Elementos en la lista\n");
        sb.append("-------|----------------------\n");

        for (int i = 0; i < hash.size; i++) {
            sb.append(String.format("%6d | ", i));
            if (hash.table[i].isEmpty()) {
                sb.append("Lista vacía\n");
            } else {
                sb.append("[");
                boolean primero = true;
                for (Register r : hash.table[i]) {
                    if (!primero) sb.append(" -> ");
                    sb.append("(").append(r.getKey()).append(":").append(r.getData()).append(")");
                    primero = false;
                }
                sb.append("]\n");
            }
        }
        salida.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int tamano = pedirTamanoTabla();
            if (tamano >= 1) {
                new InterfazHashO(tamano);
            }
        });
    }

    private static int pedirTamanoTabla() {
        JTextField campo = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Ingrese el tamaño de la tabla hash (mínimo 1):"));
        panel.add(campo);

        int tamano = -1;
        while (tamano < 1) {
            int opcion = JOptionPane.showConfirmDialog(null, panel, "Tamaño de la tabla hash",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (opcion != JOptionPane.OK_OPTION) {
                System.exit(0);
            }

            try {
                tamano = Integer.parseInt(campo.getText().trim());
                if (tamano < 1) {
                    JOptionPane.showMessageDialog(null, "Debe ser al menos 1.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Número inválido.");
            }
        }
        return tamano;
    }
}
