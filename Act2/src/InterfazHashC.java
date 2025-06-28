import javax.swing.*;
import java.awt.*;

public class InterfazHashC extends JFrame {
    private HashC<String> hash;
    private JTextArea salida;

    public InterfazHashC(int tamano) {
        this.hash = new HashC<>(tamano, new SondeoLineal());
        JOptionPane.showMessageDialog(this, "Tamaño ajustado al primo más cercano: " + hash.getSize());

        setTitle("Tabla Hash con Sondeo Lineal");
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
                hash.insert(new Register<>(clave, valor.trim()));
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
                    JOptionPane.showMessageDialog(this, "Encontrado: (" + r.getKey() + ":" + r.getName() + ")");
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
        StringBuilder sb = new StringBuilder();
        sb.append("Estado actual de la tabla hash:\n");
        sb.append("Índice | Disponible | Clave | Datos\n");
        sb.append("-------|------------|-------|----------------\n");

        for (int i = 0; i < hash.getSize(); i++) {
            sb.append(String.format("%6d | ", i));
            HashC.Element<String> elem = hash.getTable()[i];

            if (elem.register == null) {
                sb.append("    SI     |   -   |   -\n");
            } else {
                String disponible = elem.isAvailable == 0 || elem.isAvailable == -1? "SI" : "NO";
                sb.append(String.format("    %s     | %5d | %s\n",
                        disponible,
                        elem.register.getKey(),
                        elem.register.getName()));
            }
        }

        salida.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int tamano = pedirTamanoTabla();
            if (tamano >= 1) {
                new InterfazHashC(tamano);
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
