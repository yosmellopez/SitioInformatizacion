package clases;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resolucion")
public class Resolucion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion", nullable = false)
    private Integer idResolucion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "documento")
    private String documento;

    @Column(name = "clases")
    private String clases;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "publicada")
    private Boolean publicada;

    public Resolucion() {
    }

    public Resolucion(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }

    public Integer getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPublicada(Boolean publicada) {
        this.publicada = publicada;
    }

    public Boolean getPublicada() {
        return publicada;
    }

    public void setClases(String clases) {
        this.clases = clases;
    }

    public String getClases() {
        return clases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResolucion != null ? idResolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resolucion)) {
            return false;
        }
        Resolucion other = (Resolucion) object;
        return !((this.idResolucion == null && other.idResolucion != null) || (this.idResolucion != null && !this.idResolucion.equals(other.idResolucion)));
    }

    @Override
    public String toString() {
        return "clases.Resolucion[ id=" + idResolucion + " ]";
    }

}
