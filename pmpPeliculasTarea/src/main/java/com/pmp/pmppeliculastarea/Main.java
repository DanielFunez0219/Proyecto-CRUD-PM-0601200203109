/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.pmppeliculastarea;

import java.util.Scanner;
import java.util.ArrayList;

import com.pmp.dao.Pelicula;
import com.pmp.dao.PeliculaModel;
/**
 *
 * @author Daniel
 */
public class Main {
    private static Scanner entradaTeclado = new Scanner (System.in);
    
    private static PeliculaModel model = new PeliculaModel();
    
    public static void main( String[] args){
        UIUtilidades.encabezado("Gestión de peliculas");
        String menuOption = "L";
        while (!menuOption.contentEquals("S")){
            switch (menuOption) {
                case "L":
                    mostrarListado();
                    break;
                case "I":
                    insertarUnapelicula();
                    break;
                case "A":                    
                    actualizarpelicula();
                    break;
                case "E":                   
                    elminarPelicula();
                    break;
                default:
                    UIUtilidades.print("Opción no encontrada!!!");
            }
            UIUtilidades.menu();
            menuOption = entradaTeclado.nextLine().toUpperCase();
        }
    }
    
    private static void mostrarListado(){
        UIUtilidades.printEncabezadoTabla();
        ArrayList<Pelicula> peliculas= model.obtenerPeliculas();
        for(int i=0; i < peliculas.size(); i++){
            UIUtilidades.print(peliculas.get(i).getRow());
            UIUtilidades.separador();
        }
    }
    
    private static void insertarUnapelicula(){
        Pelicula nuevaPelicula= new Pelicula();
        UIUtilidades.encabezado("Nueva Pelicula");
        nuevaPelicula.setNombrePelicula(UIUtilidades.capturarCampo(entradaTeclado,"Nombre","PeliculaXYZ"));
        nuevaPelicula.setCategoria(UIUtilidades.capturarCampo(entradaTeclado,"Categoria","Categoria"));
        nuevaPelicula.setCalificacion(Integer.parseInt(UIUtilidades.capturarCampo(entradaTeclado, "Calificacion en base 10","10")));
        nuevaPelicula.setDuracionMinutos(Integer.parseInt(UIUtilidades.capturarCampo(entradaTeclado,"Duracion En minutos", "120")));
        UIUtilidades.separador();
        int insertado = model.agregarPelicula(nuevaPelicula);
        if(insertado >0){
            UIUtilidades.print("Pelicula Agregada Con Exito!!!!!!");
            mostrarListado();
        }
        UIUtilidades.separador();
    }
    
    private static void actualizarpelicula(){
        Pelicula actualizarpelicula = new Pelicula();
        Scanner actualizar = new Scanner (System.in);
        System.out.println("Ingrese el id del producto a actualizar");
        int act=actualizar.nextInt();
        ArrayList<Pelicula> peliculas = model.obtenerPeliculas();
        actualizarpelicula.setId(act);
        actualizarpelicula.setNombrePelicula(UIUtilidades.capturarCampo(entradaTeclado, "Nombre", peliculas.get(act-1).getNombrePelicula()));
        actualizarpelicula.setCategoria(UIUtilidades.capturarCampo(entradaTeclado, "categoria", peliculas.get(act-1).getCategoria()));
        actualizarpelicula.setCalificacion(Integer.parseInt(UIUtilidades.capturarCampo(entradaTeclado, "Calificacion en base 10(Enteros)",Integer.toString(peliculas.get(act-1).getCalificacion()))));
        actualizarpelicula.setDuracionMinutos(Integer.parseInt(UIUtilidades.capturarCampo(entradaTeclado, "Duracion En minutos",Integer.toString(peliculas.get(act-1).getDuracionMinutos()))));
        UIUtilidades.separador();
        
        int actualiza =model.actualizarPelicula(actualizarpelicula);
        if (actualiza > 0) {
            UIUtilidades.print("Pelicula Actualizada Satisfactoriamente!!!");
            UIUtilidades.separador();
            mostrarListado();
        } else{
            System.out.println("Producto No Actualizado");
            mostrarListado();
        }
        UIUtilidades.separador();
    }
    
    private static void elminarPelicula(){
        Scanner eliminar= new Scanner(System.in);
        System.out.println("Ingrese el id de la pelicula a eliminar");
        int eli=eliminar.nextInt();
        
        Scanner confirmar=new Scanner(System.in);
        System.out.println("Esta Seguro de que quiere eliminarlo SI(1)/NO(2)");
        int confirma=confirmar.nextInt();
        
        if (confirma ==1) {
            int elimina =model.deletePelicula(eli);
            if (elimina >0) {
                UIUtilidades.print("Producto Eliminado Satisfactoriamente!!");
                UIUtilidades.separador();
                mostrarListado();
            }
            UIUtilidades.separador(); 
        }else{
            System.out.println("Producto No Eliminado");
            mostrarListado();
        }
    }
}

