/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaSpringData;

import clases.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoJpa extends JpaRepository<Video, Integer> {

}
