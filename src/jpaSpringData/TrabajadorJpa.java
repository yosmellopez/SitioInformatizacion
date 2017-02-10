/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaSpringData;

import clases.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorJpa extends JpaRepository<Trabajador, Integer> {

}
