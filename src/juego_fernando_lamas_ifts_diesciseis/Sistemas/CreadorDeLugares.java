/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_fernando_lamas_ifts_diesciseis.Sistemas;

import ifts16.pp.juego.entidades.LugarBase;
import ifts16.pp.juego.sistemas.FabricaPrincipal;
import ifts16.pp.juego.sistemas.RepositorioPrincipal;


public class CreadorDeLugares extends FabricaPrincipal {
    
    public static void crearLugares(){
        
        CreadorDePersonajes nuevoPersonaje = new CreadorDePersonajes();
        
        LugarBase recepcion = new LugarBase("Recepcion", "Ingreso del lugar");
        
        recepcion.activar();
        
        /*Importar al repositorio principal*/
        RepositorioPrincipal.agregar(recepcion, "Recepcion");
        CreadorDeLugares.agregarIdLugar(recepcion.getId());
        
        LugarBase habitacionPrincipal = new LugarBase("Habitacion Principal", "Una habitacion llena de sorpresas");
        
        //Como conecto mi mundo con otros mundos
        recepcion.agregarVecino(habitacionPrincipal.referencia("Habitacion principal"));
        recepcion.agregarHablador(nuevoPersonaje.referencia("Elsa Humerio una viajera desconocida"));
        
    }
    
    
    
    
    
}