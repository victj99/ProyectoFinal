package clases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author victortinoco
 */

@Getter
@Setter
@AllArgsConstructor
public class Usuario {
    private String numeroDocumento; // DNI - Carnet Extranjeria - RUC
    private String nombres;
    private String apellidos;

    public Usuario() {
    }

    public Usuario(String[] datos) {
        numeroDocumento = datos[0];
        nombres = datos[1];
        apellidos = datos[2];
    }

    public String[] toExcelArray() {
        return new String[] {numeroDocumento + "", nombres, apellidos};
    }
}
