
public class Libro extends MaterialBibliografico {

    private String autor;

    public Libro(String codigo, String titulo, int anioPublicacion,
            String autor, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
    }

    public String getAutor() {
        return null;
    }

    @Override
    public int getPlazoPrestamo() {
        return 0;
    }

    @Override
    public double getMultaDiaria() {
        return 0.0;
    }
}
