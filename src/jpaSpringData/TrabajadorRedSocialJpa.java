package jpaSpringData;

import clases.TrabajadorRedSocial;
import clases.TrabajadorRedSocialPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRedSocialJpa extends JpaRepository<TrabajadorRedSocial, TrabajadorRedSocialPK> {

}
