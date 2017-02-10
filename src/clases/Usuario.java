package clases;

import anotaciones.MiClonable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utiles.SerializadorFechaTraza;
import utiles.SerializadorPassword;

@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(name = "usuario_unico", columnNames = {"usuario"}),
    @UniqueConstraint(name = "nombre_apellidos_unico", columnNames = {"nombre", "apellidos"})})
public class Usuario implements Serializable, UserDetails, MiClonable<Usuario> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "usuario", length = 255)
    private String usuario;

    @Column(name = "contrasena", length = 512)
    @JsonSerialize(using = SerializadorPassword.class)
    private String contrasena;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "apellidos", length = 255)
    private String apellidos;

    @Column(name = "recoger_barra")
    @JsonIgnore
    private Boolean recogerBarra;

    @Column(name = "ultimo_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = SerializadorFechaTraza.class)
    private Date ultimoInicio;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", foreignKey = @ForeignKey(name = "fk_usuario_rol"))
    private Rol rol;

    @Transient
    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String usuario, String nombre, String apellidos) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(Integer idUsuario, String usuario, String nombre, String apellidos, Boolean recogerBarra, Date ultimoInicio) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.recogerBarra = recogerBarra;
        this.ultimoInicio = ultimoInicio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario.toLowerCase();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toLowerCase();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getRecogerBarra() {
        return recogerBarra;
    }

    public void setRecogerBarra(Boolean recogerBarra) {
        this.recogerBarra = recogerBarra;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getUltimoInicio() {
        return ultimoInicio;
    }

    public void setUltimoInicio(Date ultimoInicio) {
        this.ultimoInicio = ultimoInicio;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Rol> roles = new ArrayList<>();
        roles.add(rol);
        return roles;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return contrasena;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return usuario;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto = nombre + " " + apellidos;
    }

    public String getNombreCompleto() {
        nombreCompleto = nombre + " " + apellidos;
        return nombreCompleto;
    }

    @Override
    public void clonarDatos(Usuario t) {
        apellidos = t.apellidos;
        contrasena = t.contrasena.isEmpty() ? contrasena : t.contrasena;
        nombre = t.nombre;
        recogerBarra = t.recogerBarra == null ? recogerBarra : t.recogerBarra;
        rol = t.rol;
        ultimoInicio = t.ultimoInicio == null ? ultimoInicio : t.ultimoInicio;
        usuario = t.usuario;
    }

}
