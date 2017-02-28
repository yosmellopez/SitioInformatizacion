package jpaSpringData;

import clases.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionJpa extends JpaRepository<Seccion, Integer> {

}
