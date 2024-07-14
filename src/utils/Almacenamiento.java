package utils;

import clases.Libro;
import clases.Prestamo;
import clases.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author victortinoco
 */
public class Almacenamiento {

    private static Almacenamiento instancia;

    private final Map<Long, Libro> librosMap = new HashMap<>();
    private final Map<Long, Prestamo> prestamosMap = new HashMap<>();
    private final Map<String, Usuario> usuarioMap = new HashMap<>();

    public Almacenamiento() {
        // Intentamos llenar los datos de los libros
        try {
            List<String[]> datosLibros = ExcelUtils.leerExcel(Constantes.NOMBRE_EXCEL_LIBROS);

            for (String[] item : datosLibros) {
                var librosInst = new Libro(item);

                librosMap.put(librosInst.getId(), librosInst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Intentamos llenar los datos de los usuarios
        try {
            List<String[]> datosUsuario = ExcelUtils.leerExcel(Constantes.NOMBRE_EXCEL_USUARIOS);

            for (String[] item : datosUsuario) {
                var usuarosInst = new Usuario(item);

                usuarioMap.put(usuarosInst.getNumeroDocumento(), usuarosInst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dentro de este try catch intentaremos llenar los datos del prestamop
        try {
            List<String[]> datosPrestamo = ExcelUtils.leerExcel(Constantes.NOMBRE_EXCEL_PRESTAMOS);

            for (String[] item : datosPrestamo) {
                var prestamoInst = new Prestamo(item);
                prestamoInst.setLibro(librosMap.get(prestamoInst.getIdLibro()));
                prestamoInst.setUsuario(getUsuarioPorDNI(prestamoInst.getNumeroDocumentoUsuario()));

                prestamosMap.put(prestamoInst.getId(), prestamoInst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Almacenamiento instance() {
        if (instancia == null) {
            instancia = new Almacenamiento();
        }
        return instancia;
    }

    // Funciones

    public List<Libro> getLibros() {
        return new ArrayList<>(librosMap.values());
    }

    public Libro getLibroPorId(Long id) {
        if (id == null) {
            return null;
        }

        return librosMap.get(id);
    }

    /**
     * Esta función registra un nuevo libroexiste
     * 
     * @param item Datos del prestamo
     */
    public void registraLibro(Libro item) {
        item.setId(System.currentTimeMillis());
        librosMap.put(item.getId(), item);

        guardarLibrosEnExcel();
    }

    public List<Prestamo> getPrestamos() {

        return new ArrayList<>(prestamosMap.values());
    }

    public Prestamo getPrestamoPorId(Long id) {
        if (id == null) {
            return null;
        }

        return prestamosMap.get(id);
    }

    /**
     * Esta función registra un nuevo prestamo y tambien un usuario si este no
     * existe
     * 
     * @param item Datos del prestamo
     */
    public void registrarPrestamo(Prestamo item) {
        item.setId(System.currentTimeMillis());
        prestamosMap.put(item.getId(), item);

        if (!usuarioMap.containsKey(item.getNumeroDocumentoUsuario().strip())) {
            usuarioMap.put(item.getNumeroDocumentoUsuario().strip(), item.getUsuario());
            guardarUsuariosEnExcel();
        }

        guardarPrestamosEnExcel();
    }

    public void registrarDevolucionPrestamo(Long id, String observacion) {
        var prestamoExistente = getPrestamoPorId(id);

        prestamoExistente.setObservacionDevolucion(observacion);
        prestamoExistente.setEstado(Constantes.ESTADO_PRESTAMO_DEVUELTO);
        prestamoExistente.setFechaDevolucion(LocalDateTime.now());

        prestamosMap.replace(id, prestamoExistente);

        guardarPrestamosEnExcel();
    }

    public Usuario getUsuarioPorDNI(String id) {
        if (id == null) {
            return null;
        }

        return usuarioMap.get(id);
    }

    private void guardarLibrosEnExcel() {
        // List<String[]> items = prestamosMap.values().stream().map((item) ->
        // item.toExcelArray()).toList();
        List<String[]> items = new ArrayList<>();

        for (Libro item : librosMap.values()) {
            items.add(item.toExcelArray());
        }

        try {
            ExcelUtils.crearExcel(Constantes.NOMBRE_EXCEL_LIBROS, items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardarPrestamosEnExcel() {
        List<String[]> items = new ArrayList<>();

        for (Prestamo item : prestamosMap.values()) {
            items.add(item.toExcelArray());
        }

        try {
            ExcelUtils.crearExcel(Constantes.NOMBRE_EXCEL_PRESTAMOS, items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardarUsuariosEnExcel() {
        List<String[]> items = new ArrayList<>();

        for (Usuario item : usuarioMap.values()) {
            items.add(item.toExcelArray());
        }

        try {
            ExcelUtils.crearExcel(Constantes.NOMBRE_EXCEL_USUARIOS, items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
