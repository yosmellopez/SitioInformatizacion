/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import clases.Trabajador;
import java.util.ArrayList;

public class BloqueTrabajador {

    private ArrayList<Trabajador> trabajadores = new ArrayList<>();

    public BloqueTrabajador() {
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public void addTrabajador(Trabajador trabajador) {
        trabajadores.add(trabajador);
    }

}
