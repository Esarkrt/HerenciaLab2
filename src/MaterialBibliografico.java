
public class MaterialBibliografico {

    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private String rutaImagen;

    public MaterialBibliografico(String codigo, String titulo,
            int anioPublicacion, String rutaImagen) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getPlazoPrestamo() {
        return 0;
    }

    public double getMultaDiaria() {
        return 0.0;
    }
    public String getTipoMaterial() {
        return "Estándar";
    }

    public double calcularMulta(int diasRetraso) {
        return diasRetraso*getMultaDiaria();
    }
}
