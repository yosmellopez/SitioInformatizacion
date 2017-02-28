package clases;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vinculo")
public class Vinculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vinculo", nullable = false)
    private Integer idVinculo;

    @Column(name = "texto")
    private String texto;

    @Column(name = "url")
    private String url;

    @Column(name = "es_texto")
    private Boolean esTexto;

    @ManyToOne
    @JoinColumn(name = "id_encabezado", referencedColumnName = "id_encabezado", foreignKey = @ForeignKey(name = "fk_vinculo_seccion"))
    private Seccion seccion;

    public Vinculo() {
    }

    public Vinculo(Integer idVinculo) {
        this.idVinculo = idVinculo;
    }

    public Integer getIdVinculo() {
        return idVinculo;
    }

    public void setIdVinculo(Integer idVinculo) {
        this.idVinculo = idVinculo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Boolean getEsTexto() {
        return esTexto;
    }

    public void setEsTexto(Boolean esTexto) {
        this.esTexto = esTexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVinculo != null ? idVinculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vinculo)) {
            return false;
        }
        Vinculo other = (Vinculo) object;
        return !((this.idVinculo == null && other.idVinculo != null) || (this.idVinculo != null && !this.idVinculo.equals(other.idVinculo)));
    }

    @Override
    public String toString() {
        return "clases.Vinculos[ id=" + idVinculo + " ]";
    }

}
