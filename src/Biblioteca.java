
import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {

    private ArrayList<MaterialBibliografico> materiales;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca() {
    materiales = new ArrayList<>();
    prestamos = new ArrayList<>();
    }

    public ArrayList<MaterialBibliografico> getMateriales() {
        return new ArrayList<>(materiales);
    }

    public ArrayList<Prestamo> getPrestamos() {
        return new ArrayList<>(prestamos);
    }

    public boolean registrarMaterial(MaterialBibliografico material) {
          if (material == null) {
        throw new IllegalArgumentException("El material es obligatorio.");
    }
    if (material.getCodigo() == null|| material.getCodigo().trim().isEmpty()|| material.getTitulo() == null|| material.getTitulo().trim().isEmpty()) {
        throw new IllegalArgumentException("El codigo y el titulo son obligatorios"
        );
    }
    int anioActual = Calendar.getInstance().get(Calendar.YEAR);
    if (material.getAnioPublicacion() < 1|| material.getAnioPublicacion() > anioActual) {
        throw new IllegalArgumentException("El año de publicación no es válido.");
    }

    if (buscarPorCodigo(material.getCodigo()) != null) {
        return false;
    }
    materiales.add(material);
        return true;
    }

    public MaterialBibliografico buscarPorCodigo(String codigo) {
        return null;
    }

    private MaterialBibliografico buscarRecursivo(
            String codigo, int indice) {
        return null;
    }

    public Prestamo buscarPrestamoActivo(String codigo) {
        return null;
    }

    public Prestamo prestarMaterial(String codigo) {
        return null;
    }

    public double devolverMaterial(String codigo) {
        return 0.0;
    }
}
