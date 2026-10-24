
public class MaterialBibliografico {

    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private String rutaImagen;

    public MaterialBibliografico(String codigo, String titulo,
            int anioPublicacion, String rutaImagen) {
    }

    public String getCodigo() {
        return null;
    }

    public String getTitulo() {
        return null;
    }

    public int getAnioPublicacion() {
        return 0;
    }

    public boolean isDisponible() {
        return false;
    }

    public String getRutaImagen() {
        return null;
    }

    public void setDisponible(boolean disponible) {
    }

    public int getPlazoPrestamo() {
        return 0;
    }

    public double getMultaDiaria() {
        return 0.0;
    }

    public double calcularMulta(int diasRetraso) {
        return 0.0;
    }
}
