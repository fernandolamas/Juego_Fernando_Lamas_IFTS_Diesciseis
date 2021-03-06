/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_fernando_lamas_ifts_diesciseis.Sistemas;

import ifts16.pp.juego.entidades.Entidad;
import juego_fernando_lamas_ifts_diesciseis.Entidad.Lugar.LugarBase;
import ifts16.pp.juego.sistemas.IOBase;
import ifts16.pp.juego.sistemas.RepositorioPrincipal;
import ifts16.pp.juego.sistemas.Sistema;
import ifts16.pp.juego.utiles.Opcion;
import ifts16.pp.juego.utiles.Opciones;
import java.awt.Color;
import juego_fernando_lamas_ifts_diesciseis.Entidad.EntidadHumana;
import juego_fernando_lamas_ifts_diesciseis.Entidad.ListaDeItems;
import juego_fernando_lamas_ifts_diesciseis.Entidad.Viviente.Viviente;

/**
 *
 * @author Fernando Lamas
 */
public class Navegacion extends Sistema{
    
        public static ListaDeItems listaItems = new ListaDeItems();
        public static LugarBase ubicacionActual;
    
    public static void iniciar(LugarBase inicio) throws InterruptedException {
        ubicacionActual = inicio;
        while (ubicacionActual != null) {
            IOBase.borrar();
            IOBase.mostrarTexto(ubicacionActual.getNombre(), Color.BLUE, Color.WHITE);
            IOBase.mostrarTexto(ubicacionActual.getDescripcion());
            Opciones ops = ubicacionActual.opciones("Elija que hacer en este lugar");
            ops.agregar("ninguno", "Salir del juego");
            Opcion eleccion = IOBase.elegirOpcion(ops);
            IOBase.mostrarTexto("Eligio: " + eleccion.getTexto());
            if (eleccion.esComando() && eleccion.getComando().equalsIgnoreCase("ninguno")) {
                ubicacionActual = null;
            } else {
                realizarAccion(eleccion);
            }
        }
        IOBase.mostrarTexto("Fin del recorrido");
    }
    
    public static void realizarAccion(Opcion op) throws InterruptedException {
        switch (op.getComando()) {
            case "vecinos":
                irAVecino(ubicacionActual);
                break;
            case "luchadores":
                luchar(ubicacionActual);
                break;
            case "habladores":
                hablar(ubicacionActual);
                break;
            case "misiones":
                hacerMisiones(ubicacionActual);
                break;
            case "items":
                recolectarItems(listaItems);
                break;
            case "inventario":
                consultarInventario(ubicacionActual);
                break;
            default:
                irAVecino(ubicacionActual);
                break;
        }
    }
    
    public static void irAVecino(LugarBase ubicacion) {
        Opciones ops = ubicacion.getVecinos()
                .opcionesActivas("Elija el lugar limítrofe a ir.");
        Opcion eleccion = IOBase.elegirOpcion(ops);
        IOBase.mostrarTexto("Eligio ir a " + eleccion.getTexto());
        if (eleccion.esEntidad()) {
            ubicacionActual = (LugarBase) RepositorioPrincipal.traer(eleccion.getEntidadId());
        }
    }
    
    
    public static void hablar(LugarBase ubicacion) {
        Opciones ops = ubicacion.getHabladores()
                .opcionesActivas("Elija con quien hablar.");
        Opcion eleccion = IOBase.elegirOpcion(ops);
        IOBase.mostrarTexto("Eligio hablar con " + eleccion.getTexto());
        if(eleccion.esEntidad()){
            Entidad ent = RepositorioPrincipal.traer(eleccion.getEntidadId());
            IOBase.mostrarTexto("En construccion, chequear CreadorDeConversaciones y unir con este");
        }
    }
    
    public static void hacerMisiones(LugarBase ubicacion){
    }

    public static void recolectarItems(ListaDeItems lista){
        Opciones iOps = new Opciones();
        if(!(ubicacionActual == null)){
            iOps.agregar("bigote_falso","Un bigote falso");
            iOps.agregar("grog", "Una botella de grog");
        }else{
            IOBase.mostrarTexto("En el lugar donde te encuentras no hay objetos");
        }
        Opcion eleccion = IOBase.elegirOpcion(iOps);
        if (eleccion.esComando()) {
            IOBase.mostrarTexto("Intentaste agregar " + eleccion.getComando());
            switch(eleccion.getComando()){
                case "bigote_falso":
            lista.agregarItem(eleccion.getComando());
            IOBase.ingresarTexto("Has agregado " + eleccion.getTexto() + " a tu inventario");
                 break;
                case "grog":
            lista.agregarItem(eleccion.getComando());
            IOBase.ingresarTexto("Has agregado " + eleccion.getTexto() + "a tu inventario");
                break;
            }
            }
        }
   
   
    public static void luchar(LugarBase ubicacion) throws InterruptedException{
                Opciones ops = ubicacion.getLuchadores()
                .opcionesActivas("Elija los luchadores con quien pelear");
        Opcion eleccion = IOBase.elegirOpcion(ops);
        if (eleccion.esEntidad()) {
            Entidad ent = RepositorioPrincipal.traer(eleccion.getEntidadId());
            EntidadHumana p = new EntidadHumana();
            Viviente e = new Viviente();
            CreadorDeCombates.nuevaLucha(p,e);
        }
    }
    
        public static void consultarInventario(LugarBase ubicacion) throws InterruptedException{
        Opciones ops = listaItems.itemsListosParaInteractuar();
        ops.agregar("ninguno", "No tengo items");
        Opcion eleccion = IOBase.elegirOpcion(ops);
        if (eleccion.esComando() && eleccion.getComando().equalsIgnoreCase("ninguno")) {
            iniciar(ubicacionActual);
        } else {
            if (eleccion.esEntidad()) {
            Entidad ent = RepositorioPrincipal.traer(eleccion.getEntidadId());
            }
        }
    }
}
