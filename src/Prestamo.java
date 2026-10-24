
import java.util.Calendar;

public class Prestamo {

    private MaterialBibliografico material;
    private Calendar fechaPrestamo;
    private Calendar fechaPrevistaDevolucion;
    private Calendar fechaDevolucion;
    private boolean devuelto;
    private double multa;

    public Prestamo(MaterialBibliografico material) {
    }

    public MaterialBibliografico getMaterial() {
        return null;
    }

    public Calendar getFechaPrestamo() {
        return null;
    }

    public Calendar getFechaPrevistaDevolucion() {
        return null;
    }

    public Calendar getFechaDevolucion() {
        return null;
    }

    public boolean isDevuelto() {
        return false;
    }

    public double getMulta() {
        return 0.0;
    }

    public int calcularDiasRetraso(Calendar fechaReal) {
        return 0;
    }

    public double registrarDevolucion() {
        return 0.0;
    }
}
