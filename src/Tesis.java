
public class Tesis extends MaterialBibliografico {

    private String tesista;

    public Tesis(String codigo, String titulo, int anioPublicacion,
            String tesista, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
    }

    public String getTesista() {
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
