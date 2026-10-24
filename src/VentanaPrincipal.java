
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
    private DefaultTableModel modeloTablaMateriales;
    private JComboBox<String> cmbTipo;
    private SimpleDateFormat dateFormat;
    private JLabel lblDatoEspecifico;
    private JLabel lblImagen;
    private DefaultTableModel modeloTablaPrestamos;
    private JTable tablaPrestamos;
    private JTable tablaMateriales;
    private DefaultTableModel modelo;
    private JTabbedPane tabbedPane;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnPrestar;
    private JButton btnDevolver;
    private JButton btnLimpiar;
    private JButton btnExamImagen;
    private JTextField txtBusquedaCodigo;
    private JTextArea txtResultadoBusqueda;
   
    
    public VentanaPrincipal() {
        biblioteca=new Biblioteca();
        dateFormat=new SimpleDateFormat();
        setTitle("Sistema de Gestion de Biblioteca");
        setSize(420,420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        modeloTablaMateriales = new DefaultTableModel(new String[]{"Código", "Título", "Tipo", "Año", "Disponible", "Detalle Específico", "Imagen"}, 0);
        tablaMateriales = new JTable(modeloTablaMateriales);

        modeloTablaPrestamos = new DefaultTableModel(new String[]{"Código", "Título", "Tipo", "Plazo (días)", "Multa/Día", "Disponible"}, 0);
        tablaPrestamos = new JTable(modeloTablaPrestamos);
        
        tabbedPane=new JTabbedPane();
        tabbedPane.addTab("Registro", crearPanelRegistro());
        tabbedPane.addTab("Inventario y Búsqueda", crearPaneldeBusqueda());
        tabbedPane.addTab("Préstamos", crearPanelPrestamos());
        tabbedPane.addTab("Devoluciones", crearPanelDevoluciones());
        
        add(tabbedPane);
        actualizarTabla();
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
                        material = new Libro(codigo, titulo, anio,datoEsp,imagen);
                    } else if ("Revista".equals(tipo)) {
                        int numEdicion = Integer.parseInt(datoEsp);
                        material = new Revista(codigo, titulo, anio, numEdicion , imagen );
                    } else {
                        material = new Tesis(codigo, titulo, anio,datoEsp ,imagen );
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
    private JPanel crearPaneldeBusqueda(){
        JPanel panel=new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda por Código"));
        txtBusquedaCodigo = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar Material");
        panelBusqueda.add(new JLabel("Código:"));
        panelBusqueda.add(txtBusquedaCodigo);
        panelBusqueda.add(btnBuscar);
        txtResultadoBusqueda = new JTextArea(4, 45);
        txtResultadoBusqueda.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(txtResultadoBusqueda);
        
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelBusqueda, BorderLayout.NORTH);
        panelNorte.add(scrollResultado, BorderLayout.CENTER);

        panel.add(panelNorte, BorderLayout.NORTH);

        modeloTablaMateriales = new DefaultTableModel(new String[]{"Código", "Título", "Tipo", "Año", "Disponible", "Detalle Específico", "Imagen"}, 0);
        tablaMateriales = new JTable(modeloTablaMateriales);
        JScrollPane scrollTabla = new JScrollPane(tablaMateriales);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Inventario General de Materiales"));

        panel.add(scrollTabla, BorderLayout.CENTER);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtBusquedaCodigo.getText().trim();
                if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Ingrese un código para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                MaterialBibliografico encontrado = biblioteca.buscarPorCodigo(codigo);
                if (encontrado != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Material encontrado mediante búsqueda recursiva\n");
                    sb.append("Código: ").append(encontrado.getCodigo()).append(" | Título: ").append(encontrado.getTitulo()).append("\n");
                    sb.append("Tipo: ").append(encontrado.getTipoMaterial()).append(" | Año: ").append(encontrado.getAnioPublicacion()).append("\n");
                    sb.append("Disponible: ").append(encontrado.isDisponible() ? "Sí" : "No").append("\n");
                    sb.append("Plazo de Préstamo: ").append(encontrado.getPlazoPrestamo()).append(" días | Multa Diaria: $").append(encontrado.getMultaDiaria());
                    txtResultadoBusqueda.setText(sb.toString());
                } else {
                    txtResultadoBusqueda.setText("El código '" + codigo + "' no se encuentra registrado en el sistema.");
                }
            }
        });

        return panel;
    }
    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelAccion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelAccion.setBorder(BorderFactory.createTitledBorder("Registrar Préstamo"));
        JButton btnPrestar = new JButton("Prestar Material Seleccionado");
        panelAccion.add(new JLabel("Seleccione un material disponible en la tabla y presione el botón:"));
        panelAccion.add(btnPrestar);

        panel.add(panelAccion, BorderLayout.NORTH);

        JTable tablaPrestamoMateriales = new JTable(modeloTablaPrestamos);
        JScrollPane scroll = new JScrollPane(tablaPrestamoMateriales);
        scroll.setBorder(BorderFactory.createTitledBorder("Materiales Registrados"));

        panel.add(scroll, BorderLayout.CENTER);

        btnPrestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPrestamoMateriales.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Seleccione un material de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String codigo = (String) modeloTablaPrestamos.getValueAt(filaSeleccionada, 0);
                try {
                    Prestamo p = biblioteca.prestarMaterial(codigo);
                    String fechaPrevistaStr = dateFormat.format(p.getFechaPrevistaDevolucion().getTime());
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, 
                        "Préstamo registrado exitosamente\n" +
                        "Material: " + p.getMaterial().getTitulo() + "\n" +
                        "Plazo: " + p.getMaterial().getPlazoPrestamo() + " días\n" +
                        "Fecha Prevista de Devolución: " + fechaPrevistaStr, 
                        "Préstamo Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }
    private JPanel crearPanelDevoluciones() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JPanel panelAccion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    panelAccion.setBorder(BorderFactory.createTitledBorder("Registrar Devolución"));
    btnDevolver = new JButton("Devolver Material Seleccionado");
    panelAccion.add(new JLabel("Seleccione el material a devolver y presione el botón:"));
    panelAccion.add(btnDevolver);
    panel.add(panelAccion, BorderLayout.NORTH);

    JScrollPane scroll = new JScrollPane(tablaPrestamos);
    scroll.setBorder(BorderFactory.createTitledBorder("Estado de Materiales"));
    panel.add(scroll, BorderLayout.CENTER);

    btnDevolver.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = tablaPrestamos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Seleccione un material de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String codigo = (String) modeloTablaPrestamos.getValueAt(filaSeleccionada, 0);
            try {
                double multa = biblioteca.devolverMaterial(codigo);
                String mensaje = "Devolución registrada exitosamente.";
                if (multa > 0) {
                    mensaje += "\nSe aplicó una multa por retraso de: $" + String.format("%.2f", multa);
                }
                JOptionPane.showMessageDialog(VentanaPrincipal.this, mensaje, "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    return panel;
}
    

    private void configurarEventos() {
    }

    private void limpiarRegistro(){
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAnio.setText("");
        txtDatoEspecifico.setText("");
        txtImagen.setText("");
        cmbTipo.setSelectedIndex(0);
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
        if (modeloTablaMateriales != null) {
            modeloTablaMateriales.setRowCount(0);
            List<MaterialBibliografico> lista = biblioteca.getMateriales();
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    MaterialBibliografico m = lista.get(i);
                    String detalle = "";
                    if (m instanceof Libro) detalle = "Autor: " + ((Libro) m).getAutor();
                    else if (m instanceof Revista) detalle = "Edición: " + ((Revista) m).getNumeroEdicion();
                    else if (m instanceof Tesis) detalle = "Tesista: " + ((Tesis) m).getTesista();

                    modeloTablaMateriales.addRow(new Object[]{
                        m.getCodigo(), m.getTitulo(), m.getTipoMaterial(), m.getAnioPublicacion(),
                        m.isDisponible() ? "Sí" : "No", detalle, m.getRutaImagen()
                    });
                }
            }
        }
        if (modeloTablaPrestamos != null) {
            modeloTablaPrestamos.setRowCount(0);
            List<MaterialBibliografico> lista = biblioteca.getMateriales();
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    MaterialBibliografico m = lista.get(i);
                    modeloTablaPrestamos.addRow(new Object[]{
                        m.getCodigo(), m.getTitulo(), m.getTipoMaterial(),
                        m.getPlazoPrestamo(), "$" + String.format("%.2f", m.getMultaDiaria()),
                        m.isDisponible() ? "Sí" : "No"
                    });
                }
            }
        }
    }
    

    private void limpiarCampos() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    
    }
}
