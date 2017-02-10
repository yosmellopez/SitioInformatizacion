/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "red_social")
public class RedSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_red_social", nullable = false)
    private Integer idRedSocial;

    @Column(name = "nombre_red")
    private String nombreRed;

    @Column(name = "direccion_red")
    private String direccionRed;

    @OneToMany(mappedBy = "redSocial")
    private List<TrabajadorRedSocial> trabajadoresRedesSociales;

    public Integer getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public String getDireccionRed() {
        return direccionRed;
    }

    public void setDireccionRed(String direccionRed) {
        this.direccionRed = direccionRed;
    }

    public List<TrabajadorRedSocial> getTrabajadoresRedesSociales() {
        return trabajadoresRedesSociales;
    }

    public void setTrabajadoresRedesSociales(List<TrabajadorRedSocial> trabajadoresRedesSociales) {
        this.trabajadoresRedesSociales = trabajadoresRedesSociales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRedSocial != null ? idRedSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedSocial)) {
            return false;
        }
        RedSocial other = (RedSocial) object;
        if ((this.idRedSocial == null && other.idRedSocial != null) || (this.idRedSocial != null && !this.idRedSocial.equals(other.idRedSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.RedSocial[ id=" + idRedSocial + " ]";
    }

}
