/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaSpringData;

import clases.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoJpa extends JpaRepository<Archivo, Integer> {

}
