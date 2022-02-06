/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class PeliculaModel {
    
    private Connection _conexion = null;
    
    public PeliculaModel(){
        _conexion = Conexion.getConexion();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS peliculas"
                +"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +"nombre TEXT NOT NULL,"
                +"categoria TEXT NOT NULL,"
                +"calificacion NUMERIC NOT NULL,"
                +"duracion NUMERIC NOT NULL);";
        
        try{
            Statement comando = _conexion.createStatement();
            int resultado = comando.executeUpdate(sqlCreateTable);
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    public ArrayList<Pelicula> obtenerPeliculas(){
        try{
            ArrayList pelicula = new ArrayList<Pelicula>();
            Statement comandoSQL = _conexion.createStatement();
            ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * FROM peliculas");
            while (registroEnTabla.next()){
                Pelicula peliculaActual = new Pelicula();
                peliculaActual.setId(registroEnTabla.getInt("id"));
                peliculaActual.setNombrePelicula(registroEnTabla.getString("nombre"));
                peliculaActual.setCategoria(registroEnTabla.getString("categoria"));
                peliculaActual.setCalificacion(registroEnTabla.getInt("calificacion"));
                peliculaActual.setDuracionMinutos(registroEnTabla.getInt("duracion"));
                
                pelicula.add(peliculaActual);
            }
            return pelicula;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<Pelicula>();
        }
    }
    
    public Pelicula obtenerPeliculas(int id){
        try{
            PreparedStatement comandoSQL = _conexion.prepareStatement("SELECT * FROM peliculas where id= ?;");
            comandoSQL.setInt(1, id);
            ResultSet registroEnTabla = comandoSQL.executeQuery();
            Pelicula peliculaActual= new Pelicula();
            while (registroEnTabla.next()){
               peliculaActual.setId(registroEnTabla.getInt("id"));
               peliculaActual.setNombrePelicula(registroEnTabla.getString("nombre"));
               peliculaActual.setCategoria(registroEnTabla.getString("categoria"));
               peliculaActual.setCalificacion(registroEnTabla.getInt("calificacion"));
               peliculaActual.setDuracionMinutos(registroEnTabla.getInt("duracion")); 
               break;
            }
            return peliculaActual;
            
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public int agregarPelicula(Pelicula nuevaPelicula){
        try{
            String insertSql = "INSERT INTO peliculas (nombre, categoria, calificacion, duracion) values(?, ?, ?, ?);";
            PreparedStatement comandoSQL= _conexion.prepareStatement(insertSql);
            comandoSQL.setString(1, nuevaPelicula.getNombrePelicula());
            comandoSQL.setString(2, nuevaPelicula.getCategoria());
            comandoSQL.setInt(3, nuevaPelicula.getCalificacion());
            comandoSQL.setInt(4, nuevaPelicula.getDuracionMinutos());
            
            int RegistroAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int actualizarPelicula(Pelicula updatePelicula){
         try{
            String updateSql = "UPDATE peliculas SET nombre=?, categoria=?, calificacion=?, duracion=? where id=?;";
            PreparedStatement comandoSQL= _conexion.prepareStatement(updateSql);
            comandoSQL.setString(1, updatePelicula.getNombrePelicula());
            comandoSQL.setString(2, updatePelicula.getCategoria());
            comandoSQL.setInt(3, updatePelicula.getCalificacion());
            comandoSQL.setInt(4, updatePelicula.getDuracionMinutos());
            comandoSQL.setInt(5,updatePelicula.getId());
            
            int RegistroAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int deletePelicula(int id){
        try{
            String deleteSql= "DELETE FROM peliculas where id=?;";
            PreparedStatement comandoSQL= _conexion.prepareStatement(deleteSql);
            comandoSQL.setInt(1,id);
            
             int RegistroAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
            
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
