package clases;

/**
 *
 * @author victortinoco
 */

public class Libro {
    Long id;
    String nombre;
    String autor;
    String descripcion;
    
    public Libro() {
    }

    public Libro(String[] datos) {
        id = Long.valueOf(datos[0]);
        nombre = datos[1];
        autor = datos[2];
        descripcion = datos[3];
    }

    public Libro(Long id, String nombre, String autor, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
    }
    
    public void asignarDatos(String nombre, String autor, String descripcion) {
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }
    public String getIdStr() {
        return id + "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Libro{" + "nombre=" + nombre + ", autor=" + autor + ", descripcion=" + descripcion + '}';
    }
    
    
}
