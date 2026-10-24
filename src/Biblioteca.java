
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<MaterialBibliografico> materiales;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca() {
    }

    public ArrayList<MaterialBibliografico> getMateriales() {
        return null;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return null;
    }

    public boolean registrarMaterial(MaterialBibliografico material) {
        return false;
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
