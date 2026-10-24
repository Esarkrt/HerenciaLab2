
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAnio;
    private JTextField txtDatoEspecifico;
    private JTextField txtImagen;

    private JComboBox<String> cmbTipo;
    private SimpleDateFormat dateFormat;
    private JLabel lblDatoEspecifico;
    private JLabel lblImagen;

    private JTable tablaMateriales;
    private DefaultTableModel modelo;
    private JTabbedPane tabbedPane;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnPrestar;
    private JButton btnDevolver;
    private JButton btnLimpiar;
    private JButton btnExamImagen;

    public VentanaPrincipal() {
        biblioteca=new Biblioteca();
        dateFormat=new SimpleDateFormat();
        setTitle("Sistema de Gestion de Biblioteca");
        setSize(420,420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane=new JTabbedPane();
        tabbedPane.addTab("Registro", crearPanelRegistro());
    }

    private JPanel crearPanelRegistro() {
        JPanel panelRegistro=new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cmbTipo = new JComboBox<>(new String[]{"Libro", "Revista", "Tesis"});
        txtCodigo = new JTextField(20);
        txtTitulo = new JTextField(20);
        txtAnio = new JTextField(20);
        txtDatoEspecifico = new JTextField(20);
        txtImagen = new JTextField(15);
        btnExamImagen = new JButton("Examinar...");

        JLabel lblDatoEspecifico = new JLabel("Nombre del Autor:");
        cmbTipo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) cmbTipo.getSelectedItem();
                if ("Libro".equals(tipo)) {
                    lblDatoEspecifico.setText("Nombre del Autor:");
                } else if ("Revista".equals(tipo)) {
                    lblDatoEspecifico.setText("Número de Edición:");
                } else if ("Tesis".equals(tipo)) {
                    lblDatoEspecifico.setText("Autor Académico / Tesista:");
                }
            }
        });
    }

    private void configurarEventos() {
    }

    private void actualizarCampoEspecifico() {
    }

    private void registrarMaterial() {
    }

    private void buscarMaterial() {
    }

    private void prestarMaterial() {
    }

    private void devolverMaterial() {
    }

    private void actualizarTabla() {
    }

    private void limpiarCampos() {
    }

    public static void main(String[] args) {
    }
}
