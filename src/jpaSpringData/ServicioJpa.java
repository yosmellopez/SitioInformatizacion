/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaSpringData;

import clases.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioJpa extends JpaRepository<Servicio, Integer> {

}
