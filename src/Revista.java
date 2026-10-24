
public class Revista extends MaterialBibliografico {

    private int numeroEdicion;

    public Revista(String codigo, String titulo, int anioPublicacion,
            int numeroEdicion, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    @Override
    public int getPlazoPrestamo() {
        return 7;
    }

    @Override
    public double getMultaDiaria() {
        return 0.75;
    }
}
