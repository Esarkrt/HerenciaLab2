
public class Libro extends MaterialBibliografico {

    private String autor;

    public Libro(String codigo, String titulo, int anioPublicacion,
            String autor, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public int getPlazoPrestamo() {
        return 14;
    }

    @Override
    public double getMultaDiaria() {
        return 1.50;
    }
    @Override
    public String getTipoMaterial() {
        return "Libro";
    }
}
