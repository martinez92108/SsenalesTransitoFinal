package com.example.martinez.senalestransitofinal.ModelSenales;

/**
 * Created by martinez on 17/11/17.
 */

public class ComentariosModel {

    String titulo;
    String descripcion;

    public ComentariosModel(String descripcion) {

    }

    public ComentariosModel(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
