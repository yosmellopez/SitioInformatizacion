/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrabajadorRedSocialPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_trabajador", nullable = false)
    private Integer idTrabajador;

    @Basic(optional = false)
    @Column(name = "id_red_social", nullable = false)
    private Integer idRedSocial;

    public TrabajadorRedSocialPK() {
    }

    public TrabajadorRedSocialPK(Integer idTrabajador, Integer idRedSocial) {
        this.idTrabajador = idTrabajador;
        this.idRedSocial = idRedSocial;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }
    
}
