package resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import utiles.MapeadorObjetos;
import java.util.ArrayList;
import javax.persistence.criteria.JoinType;

public class SpecificationArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_SPECIFICATION_PARAMETER = "parametros";

    private static final String DEFAULT_SPECIFICATION_JOIN = "joins";

    private static final String DEFAULT_DATE_PATTERN = "dateFormat";

    private HashMap<String, Object> hashMap = new HashMap<>();

    private HashMap<String, JoinType> hashMapJoins = new HashMap<>();

    private String parametrosParameterName = DEFAULT_SPECIFICATION_PARAMETER;

    private String joinsParameterName = DEFAULT_SPECIFICATION_JOIN;

    private String dateFormatParameterName = DEFAULT_DATE_PATTERN;

    private ObjectMapper objectMapper = new MapeadorObjetos();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Specification.class.equals(parameter.getParameterType());
    }

    @Override
    public Specification resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String parametro = webRequest.getParameter(parametrosParameterName);
        String dateFormat = webRequest.getParameter(dateFormatParameterName);
        String joins = webRequest.getParameter(joinsParameterName);
        boolean hasText = StringUtils.hasText(parametro);
        boolean hasJoinText = StringUtils.hasText(joins);
        if (hasJoinText) {
            String[] split = joins.split(",");
            for (String string : split) {
                hashMapJoins.put(string, JoinType.LEFT);
            }
            return null;
        }
        if (!hasText) {
            return null;
        }
        objectMapper.readValue(joins, ArrayList.class);
        hashMap = objectMapper.readValue(parametro, HashMap.class);
        if (hashMap == null) {
            return null;
        }
        if (dateFormat != null) {
            return null;
        }
        return null;
    }

    public String getParametrosParameterName() {
        return parametrosParameterName;
    }

    public void setParametrosParameterName(String parametrosParameterName) {
        this.parametrosParameterName = parametrosParameterName;
    }

    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
