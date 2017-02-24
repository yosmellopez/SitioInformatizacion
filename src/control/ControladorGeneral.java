package control;

import clases.Trabajador;
import clases.Usuario;
import java.util.LinkedList;
import java.util.List;
import jpaSpringData.ServicioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import utiles.MapeadorObjetos;

import javax.servlet.http.HttpServletRequest;
import jpaSpringData.ResolucionJpa;
import jpaSpringData.TrabajadorJpa;

import jpaSpringData.UsuarioJpa;
import jpaSpringData.VideoJpa;
import utiles.BloqueTrabajador;

@Controller
public class ControladorGeneral {

    @Autowired
    UsuarioJpa usuarioJpa;

    @Autowired
    ServicioJpa servicioJpa;

    @Autowired
    VideoJpa videoJpa;

    @Autowired
    ResolucionJpa resolucionJpa;

    @Autowired
    TrabajadorJpa trabajadorJpa;

    @Autowired
    MapeadorObjetos mapeadorObjetos;

    @RequestMapping(value = "/{pagina}.html")
    public ModelAndView paginaRouter(@PathVariable String pagina, ModelMap map) {
        map.put("pagina", pagina);
        return new ModelAndView(pagina, map);
    }

    @RequestMapping(value = "/{id}/{pagina}.html")
    public ModelAndView paginaRouter(@PathVariable Integer id, @PathVariable String pagina, ModelMap map) {
        map.put("pagina", pagina);
        return new ModelAndView(pagina, map);
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index(ModelMap modelMap) {
        modelMap.put("serviciosGenerales", servicioJpa.findAll(new PageRequest(0, 4)));
        modelMap.put("trabajadores", crearBloque(trabajadorJpa.findAll()));
        modelMap.put("servicios", servicioJpa.findAll(new PageRequest(0, 5)));
        modelMap.put("servicios1", servicioJpa.findAll(new PageRequest(1, 5)));
        modelMap.put("servicios2", servicioJpa.findAll(new PageRequest(2, 5)));
        modelMap.put("resoluciones", resolucionJpa.findAll(new PageRequest(0, 4)));
        modelMap.put("videos", videoJpa.findAll());
        return new ModelAndView("index", modelMap);
    }

    @RequestMapping(value = "/denegado.html", method = RequestMethod.GET)
    public ModelAndView denegado(@AuthenticationPrincipal Usuario usuario) {
        ModelMap map = new ModelMap();
        llenarMap(map, usuario);
        return new ModelAndView("error/denegado", map);
    }

    @RequestMapping(value = "/error404.html")
    public ModelAndView error404Page(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Usuario) {
                Usuario usuario = (Usuario) principal;
                llenarMap(map, usuario);
            } else {
                llenarMap(map, null);
            }
        } else {
            llenarMap(map, null);
        }
        return new ModelAndView("error/error404", map);
    }

    @RequestMapping(value = "/errorPage.html")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView error500Page(ModelMap map, HttpServletRequest request) {
        if (contiene(request.getHeader("Referer"))) {
            Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
            map.put("success", false);
            map.put("msg", exception.getLocalizedMessage());
            return new ModelAndView(new MappingJackson2JsonView(mapeadorObjetos), map);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Usuario) {
                Usuario usuario = (Usuario) principal;
                llenarMap(map, usuario);
            } else {
                llenarMap(map, null);
            }
        } else {
            llenarMap(map, null);
        }
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.exception"));
        return new ModelAndView("error/error500", map);
    }

    private boolean contiene(String cadena) {
        return cadena.contains("ministerio") || cadena.contains("universidad") || cadena.contains("modelo") || cadena.contains("doctorado") || cadena.contains("otros") || cadena.contains("programas") || cadena.contains("slider") || cadena.contains("convocatorias");
    }

    private void llenarMap(ModelMap map, Usuario usuario) {
        map.put("usuarios", usuarioJpa.count());
    }

    private List<BloqueTrabajador> crearBloque(List<Trabajador> trabajadores) {
        List<BloqueTrabajador> bloques = new LinkedList<>();
        int cantidad = trabajadores.size();
        BloqueTrabajador bloqueTrabajador = new BloqueTrabajador();
        for (int i = 0; i < cantidad; i++) {
            if (i % 3 == 0) {
                bloqueTrabajador = new BloqueTrabajador();
                bloques.add(bloqueTrabajador);
            }
            bloqueTrabajador.addTrabajador(trabajadores.get(i));
        }
        return bloques;
    }
}
