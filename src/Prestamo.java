
import java.util.Calendar;

public class Prestamo {

    private MaterialBibliografico material;
    private Calendar fechaPrestamo;
    private Calendar fechaPrevistaDevolucion;
    private Calendar fechaDevolucion;
    private boolean devuelto;
    private double multa;

    public Prestamo(MaterialBibliografico material) {
        this.material = material;
        fechaPrestamo = Calendar.getInstance();
        
        fechaPrevistaDevolucion = (Calendar) fechaPrestamo.clone();
        fechaPrevistaDevolucion.add(Calendar.DAY_OF_YEAR, material.getPlazoPrestamo());
        fechaDevolucion = null;
        devuelto = false;
        multa = 0.0;
    }

    public MaterialBibliografico getMaterial() {
        return material;
    }

    public Calendar getFechaPrestamo() {
        return (Calendar) fechaPrestamo.clone();
    }

    public Calendar getFechaPrevistaDevolucion() {
        return (Calendar) fechaPrevistaDevolucion.clone();
    }

    public Calendar getFechaDevolucion() {
        if(fechaDevolucion == null){
            return null;
        }
        return (Calendar) fechaDevolucion.clone();
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public double getMulta() {
        return multa;
    }

    public int calcularDiasRetraso(Calendar fechaReal) {
        Calendar limite=(Calendar) fechaPrevistaDevolucion.clone();
        Calendar real = (Calendar) fechaReal.clone();
        
        limite.set(Calendar.HOUR_OF_DAY,0);
        limite.set(Calendar.MINUTE,0);
        limite.set(Calendar.SECOND,0);
        limite.set(Calendar.MILLISECOND, 0);
        
        real.set(Calendar.HOUR_OF_DAY,0);
        real.set(Calendar.MINUTE,0);
        real.set(Calendar.SECOND,0);
        real.set(Calendar.MILLISECOND, 0);
        
        long diferencia = real.getTimeInMillis()-limite.getTimeInMillis();
        if(diferencia<=0){
        return 0;
        }
        return (int) Math.ceil(diferencia /(1000*60*60*24));
    }

    public double registrarDevolucion() {
        return 0.0;
    }
}
