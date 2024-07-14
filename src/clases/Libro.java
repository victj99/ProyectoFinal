/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author LENOVO
 */
public class Libro {

    private Long id;
    private String nombre;
    private String autor;
    private String descripcion;
    private String genero;
    private String publicacion;
    private String estado;

    public Libro(String[] datos) {
        id = Long.valueOf(datos[0]);
        nombre = datos[1];
        autor = datos[2];
        descripcion = datos[3];
        genero = datos[4];
        publicacion = datos[5];
        estado = datos[6];
    }

    public Libro(Long id, String nombre, String autor, String descripcion, String genero, String publicacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.genero = genero;
        this.publicacion = publicacion;
        this.estado = estado;
    }

    public Libro() {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String[] toExcelArray() {
        return new String[] { id + "", nombre, autor, descripcion, genero, publicacion, estado };
    }

    @Override
    public String toString() {
        return "Libro{" + "nombre=" + nombre + ", autor=" + autor + ", descripcion=" + descripcion + '}';
    }
    
    
}
