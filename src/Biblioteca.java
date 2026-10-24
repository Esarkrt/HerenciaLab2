
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
        if (codigo == null || codigo.trim().isEmpty()) {
        return null;
   }
        return buscarRecursivo(codigo.trim(), 0);
    }

    private MaterialBibliografico buscarRecursivo( String codigo, int indice) {
        if (indice >= materiales.size()) {
            return null;
        }
        MaterialBibliografico material = materiales.get(indice);

        
        if (material.getCodigo().equalsIgnoreCase(codigo)) {
            return material; 
        }
        
        
        return buscarRecursivo(codigo, indice + 1);
    }

    public Prestamo buscarPrestamoActivo(String codigo) {
         for (int i = prestamos.size() - 1; i >= 0; i--) {
        Prestamo prestamo = prestamos.get(i);
        if (prestamo.getMaterial().getCodigo().equalsIgnoreCase(codigo)&& !prestamo.isDevuelto()) {
            return prestamo;
        }
    }
        return null;
    }

    public Prestamo prestarMaterial(String codigo) {
        MaterialBibliografico material = buscarPorCodigo(codigo);

    if (material == null) {
        throw new IllegalArgumentException("El material no existe");
    }
    if (!material.isDisponible()) {
        throw new IllegalStateException( "El material no está disponible");
    }
    Prestamo prestamo = new Prestamo(material);

    material.setDisponible(false);prestamos.add(prestamo);
        return prestamo;
    }

    public double devolverMaterial(String codigo) {
        Prestamo prestamo = buscarPrestamoActivo(codigo);

    if (prestamo == null) {
        throw new IllegalStateException("No hay un prestamo activo para este material");
    }
        return  prestamo.registrarDevolucion();
    }
}
