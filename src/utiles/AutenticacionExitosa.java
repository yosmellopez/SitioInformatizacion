package utiles;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class AutenticacionExitosa extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie cookie = new Cookie("informatizacion", "" + new Date().getTime());
        response.addCookie(cookie);
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest != null) {
            String requestURL = savedRequest.getRequestURL();
            getRedirectStrategy().sendRedirect(request, response, requestURL + "#");
        } else {
            setDefaultTargetUrl("/admin/inicio.htm#");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
