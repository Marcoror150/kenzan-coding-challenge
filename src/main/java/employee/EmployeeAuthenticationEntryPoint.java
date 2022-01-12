package employee;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class generates the response page when a user tries to perform an operation they are not authorized for.
 */
public class EmployeeAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{
    /**
     * Called when a user tries to perform an action they are not authorized for.
     *
     * @param request  The request being made by the user.
     * @param response The response page that will be sent back to the user.
     * @param authEx   The authentication exception to be thrown when a user does not have the correct authentication
     *                 for an action.
     * @throws IOException If the response writer fails to be retrieved.
     */
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException
    {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    /**
     * Sets the realm name of the user, once authenticated, to admin, then calls the super class' method.
     */
    @Override
    public void afterPropertiesSet()
    {
        setRealmName("admin");
        super.afterPropertiesSet();
    }
}
