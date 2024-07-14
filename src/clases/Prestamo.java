package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import utils.AppUtils;
import utils.Constantes;

/**
 *
 * @author victortinoco
 */

@Getter
@Setter
public class Prestamo {
    private Long id;
    private Long idLibro;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevolucion;
    private String numeroDocumentoUsuario;
    private String observacionDevolucion;
    private String estado; // En prestamo - Devuelto

    // Datos aparte
    private Libro libro;
    private Usuario usuario;

    public Prestamo() {
    }

    public Prestamo(String[] datos) {
        id = Long.valueOf(datos[0]);
        idLibro = Long.valueOf(datos[1]);

        fechaPrestamo = AppUtils.convertirStringADateTime(datos[2]);
        fechaDevolucion = AppUtils.convertirStringADateTime(datos[3]);
        numeroDocumentoUsuario = datos[4];
        observacionDevolucion = datos[5];
        estado = datos[6];
    }

    public String getFechaPrestamoStr() {
        return fechaPrestamo != null ? fechaPrestamo.format(DateTimeFormatter.ofPattern(Constantes.DATETIME_FORMAT))
                : "";
    }

    public String getFechaDevolucionStr() {
        return fechaDevolucion != null ? fechaDevolucion.format(DateTimeFormatter.ofPattern(Constantes.DATETIME_FORMAT))
                : "";
    }

    public String[] toExcelArray() {
        return new String[] { id + "", idLibro + "", getFechaPrestamoStr(), getFechaDevolucionStr(),
                numeroDocumentoUsuario, observacionDevolucion, estado };
    }
}
