package control;

import clases.Usuario;
import java.util.List;
import javax.persistence.PersistenceException;
import jpaSpringData.UsuarioJpa;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import utiles.MapeadorObjetos;

@Controller
public class UsuarioControl {

    @Autowired
    UsuarioJpa usuarioJpa;

    @Autowired
    MapeadorObjetos mapeadorObjetos;

    @RequestMapping(value = "/usuario.json", method = RequestMethod.GET)
    public ModelAndView listarUsuarioes(Pageable p, Integer idUsuario, Specification s, ModelMap map) {
        if (s != null) {
            List findAll = usuarioJpa.findAll(s);
            map.put("lista", findAll);
            map.put("total", findAll.size());
        } else {
            Page<Usuario> usuarioes = usuarioJpa.findAll(p);
            map.put("lista", usuarioes.getContent());
            map.put("total", usuarioes.getTotalElements());
        }
        map.put("success", true);

        return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), map);
    }

    @RequestMapping(value = "/usuario.html", method = RequestMethod.GET)
    public ModelAndView listarUsuarios(ModelMap map) {
        List findAll = usuarioJpa.findAll();
        map.put("lista", findAll);
        map.put("total", findAll.size());
        map.put("success", true);
        return new ModelAndView("usuario", map);
    }

    @RequestMapping(value = "/usuario.json", method = RequestMethod.POST)
    public ModelAndView insertarUsuario(@RequestBody Usuario usuario) {
        usuarioJpa.saveAndFlush(usuario);
        ModelMap map = new ModelMap();
        map.put("success", true);
        map.put("lista", usuario);
        return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), map);
    }

    @RequestMapping(value = "/usuario.json/{idUsuario}", method = RequestMethod.PUT)
    public ModelAndView actualizarUsuario(@PathVariable("idUsuario") Usuario usuarioBd, @RequestBody Usuario usuario) {
        usuarioBd.clonarDatos(usuario);
        usuarioJpa.saveAndFlush(usuarioBd);
        ModelMap map = new ModelMap();
        map.put("success", true);
        map.put("lista", usuarioBd);
        map.put("total", usuarioJpa.count());
        return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), map);
    }

    @RequestMapping(value = "/usuario.json/{idUsuario}", method = RequestMethod.DELETE)
    public ModelAndView eliminarUsuario(@PathVariable int idUsuario) {
        usuarioJpa.delete(idUsuario);
        ModelMap map = new ModelMap();
        map.put("success", true);
        map.put("total", usuarioJpa.count());
        return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), map);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView tratarExcepcion(Exception e) {
        ModelMap modelMap = new ModelMap();
        if (e instanceof JpaSystemException) {
            JpaSystemException jse = (JpaSystemException) e;
            modelMap.put("msg", tratarMensaje(jse.getMostSpecificCause()));
        } else if (e instanceof PersistenceException) {
            JpaSystemException exception = new JpaSystemException((PersistenceException) e);
            modelMap.put("msg", tratarMensaje(exception.getMostSpecificCause()));
        } else if (e instanceof DataIntegrityViolationException) {
            DataIntegrityViolationException exception = (DataIntegrityViolationException) e;
            modelMap.put("msg", tratarMensaje(exception.getMostSpecificCause()));
        } else if (e instanceof SQLGrammarException) {
            SQLGrammarException exception = (SQLGrammarException) e;
            modelMap.put("msg", tratarMensaje(exception.getCause()));
        } else {
            modelMap.put("msg", e.getMessage());
        }
        modelMap.put("success", false);
        return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), modelMap);
    }

    private String tratarMensaje(Throwable e) {
        String message = e.getMessage();
        if (message.contains("usuario_ci_unico")) {
            return "Ya existe este número de identidad.";
        } else {
            return message;
        }
    }
}
