/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_fernando_lamas_ifts_diesciseis.Entidad;

import ifts16.pp.juego.entidades.PersonajeAbstracto;
import ifts16.pp.juego.sistemas.IOBase;
import juego_fernando_lamas_ifts_diesciseis.Componente.Personaje.Inventario;

/**
 *
 * @author Fernando Lamas
 */
public class EntidadHumana extends PersonajeAbstracto{
    
    public Inventario ConInventario;
    
    
    public EntidadHumana(){
        super();
        this.nombre = IOBase.ingresarTexto("Escribe el nombre de tu personaje ");
        this.id = this.getId();
        this.ConInventario = new Inventario();
        
    }
    
}
