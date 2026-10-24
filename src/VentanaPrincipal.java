
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
        
            
        btnExamImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(VentanaPrincipal.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();
                    txtImagen.setText(archivo.getAbsolutePath());
                }
            }
        });
        
        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(new JLabel("Tipo de Material:"), gbc);
        gbc.gridx = 1; panelRegistro.add(cmbTipo, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(new JLabel("Código Único:"), gbc);
        gbc.gridx = 1; panelRegistro.add(txtCodigo, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; panelRegistro.add(txtTitulo, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(new JLabel("Año de Publicación:"), gbc);
        gbc.gridx = 1; panelRegistro.add(txtAnio, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(lblDatoEspecifico, gbc);
        gbc.gridx = 1; panelRegistro.add(txtDatoEspecifico, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; panelRegistro.add(new JLabel("Imagen (Ruta):"), gbc);
        JPanel panelImg = new JPanel(new BorderLayout(5, 0));
        panelImg.add(txtImagen, BorderLayout.CENTER);
        panelImg.add(btnExamImagen, BorderLayout.EAST);
        gbc.gridx = 1; panelRegistro.add(panelImg, gbc);
        row++;
        JButton btnGuardar = new JButton("Registrar Material");
        gbc.gridx = 1; gbc.gridy = row; panelRegistro.add(btnGuardar, gbc);
        btnGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                    String tipo = (String) cmbTipo.getSelectedItem();
                    String codigo = txtCodigo.getText().trim();
                    String titulo = txtTitulo.getText().trim();
                    String anioStr = txtAnio.getText().trim();
                    String datoEsp = txtDatoEspecifico.getText().trim();
                    String imagen = txtImagen.getText().trim();

                    if (codigo.isEmpty() || titulo.isEmpty() || anioStr.isEmpty() || datoEsp.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Rellene todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int anio;
                    try {
                        anio = Integer.parseInt(anioStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "El año de publicación debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                    if (anio < 1000 || anio > anioActual) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Año de publicación inválido (1000 - " + anioActual + ").", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    MaterialBibliografico material;
                    if ("Libro".equals(tipo)) {
                        material = new Libro(codigo, titulo, anio, imagen, datoEsp);
                    } else if ("Revista".equals(tipo)) {
                        int numEdicion = Integer.parseInt(datoEsp);
                        material = new Revista(codigo, titulo, anio, imagen, numEdicion);
                    } else {
                        material = new Tesis(codigo, titulo, anio, imagen, datoEsp);
                    }

                    biblioteca.registrarMaterial(material);
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Material registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarRegistro();
                    actualizarTabla();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panelRegistro;
    }

    private void configurarEventos() {
    }

    private void limpiarRegistro(){
        
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
