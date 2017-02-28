package clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "seccion")
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_encabezado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEncabezado;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "posicion")
    private Integer posicion;

    @OneToMany(mappedBy = "seccion", fetch = FetchType.EAGER)
    private List<Vinculo> vinculos;

    public Integer getIdEncabezado() {
        return idEncabezado;
    }

    public void setIdEncabezado(Integer idEncabezado) {
        this.idEncabezado = idEncabezado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncabezado != null ? idEncabezado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        return !((this.idEncabezado == null && other.idEncabezado != null) || (this.idEncabezado != null && !this.idEncabezado.equals(other.idEncabezado)));
    }

    @Override
    public String toString() {
        return "clases.Encabezado[ id=" + idEncabezado + " ]";
    }

}
