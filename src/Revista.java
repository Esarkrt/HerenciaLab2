
public class Revista extends MaterialBibliografico {

    private int numeroEdicion;

    public Revista(String codigo, String titulo, int anioPublicacion,
            int numeroEdicion, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
    }

    public int getNumeroEdicion() {
        return 0;
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
