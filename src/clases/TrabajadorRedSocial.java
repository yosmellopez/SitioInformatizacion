/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trabajador_red_social")
public class TrabajadorRedSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TrabajadorRedSocialPK trabajadorRedSocialPK;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_trabajador", referencedColumnName = "id_trabajador", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_trabajador_red_social"))
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "id_red_social", referencedColumnName = "id_red_social", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_red_social_trabajador"))
    private RedSocial redSocial;

    public TrabajadorRedSocialPK getTrabajadorRedSocialPK() {
        return trabajadorRedSocialPK;
    }

    public void setTrabajadorRedSocialPK(TrabajadorRedSocialPK trabajadorRedSocialPK) {
        this.trabajadorRedSocialPK = trabajadorRedSocialPK;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

}
