/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.dao;

/**
 *
 * @author Daniel
 */
public class Pelicula {
    
     public int getId(){
        return _id;
    }
    
    public void setId(int _id){
        this._id = _id;
    }
    //-------------------------------------------------------
    public String getNombrePelicula(){
        return _NombrePelicula;
    }
     
    public void setNombrePelicula(String _NombrePelicula){
        this._NombrePelicula = _NombrePelicula;
    }
    //-------------------------------------------------------
     public String getCategoria(){
        return _Categoria;
    }
     
    public void setCategoria(String _Categoria){
        this._Categoria = _Categoria;
    }
    //-------------------------------------------------------
    public int getCalificacion(){
        return _Calificacion;
    }
    
    public void setCalificacion(int _Calificacion){
        this._Calificacion = _Calificacion;
    }
    //-------------------------------------------------------
    public int getDuracionMinutos(){
        return _Duracionminutos;
    }
    
    public void setDuracionMinutos(int _Duracion_minutos){
        this._Duracionminutos = _Duracion_minutos;
    }
    
    public String getRow(){
        return String.format("%s\t%s\t%s\t\t%s\t\t%s", _id, _NombrePelicula, _Categoria, _Calificacion, _Duracionminutos);
    }
    
    
    private int _id;
    private String _NombrePelicula;
    private String _Categoria;
    private int _Calificacion;
    private int _Duracionminutos;
}
