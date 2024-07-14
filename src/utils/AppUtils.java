package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 *
 * @author victortinoco
 */
public class AppUtils {
    public static boolean isLocalDateBeforeOrEqual(LocalDate fecha1, LocalDate fecha2) {

        if (fecha1 == null || fecha2 == null) {
            return false;
        }

        return fecha1.isBefore(fecha2) || fecha1.isEqual(fecha2);
    }

    public static boolean isLocalDateAfterOrEqual(LocalDate fecha1, LocalDate fecha2) {

        if (fecha1 == null || fecha2 == null) {
            return false;
        }

        return fecha1.isAfter(fecha2) || fecha1.isEqual(fecha2);
    }

    // Creamos un objeto que se encargará de convertir los strings en objetos de fecha
    private static DateTimeFormatter DT_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy HH:mm[[:ss][.SSS]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    public static LocalDateTime convertirStringADateTime(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            return null;
        }
        
        return LocalDateTime.parse(fecha, DT_FORMATTER);
    }
}
