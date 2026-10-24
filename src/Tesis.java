
public class Tesis extends MaterialBibliografico {

    private String tesista;

    public Tesis(String codigo, String titulo, int anioPublicacion,
            String tesista, String rutaImagen) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.tesista=tesista;
    }

    public String getTesista() {
        return tesista;
    }

    @Override
    public int getPlazoPrestamo() {
        return 3;
    }

    @Override
    public double getMultaDiaria() {
        return 3.0;
    }
}
