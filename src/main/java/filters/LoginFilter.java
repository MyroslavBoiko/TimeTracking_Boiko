package filters;

import manager.PagesJsp;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(PagesJsp.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest  request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("type");

        String command = request.getParameter("command");
        String uri = request.getRequestURI();
        if(command == null){
            switch (uri){
                case "/adminpage":
                    if(type == null){
                        session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.LOGIN));
                    }else{
                        if(type.equals("Admin")){
                            session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.ADMIN));
                        }else {
                            session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.ERROR));
                        }
                    }
                    break;
                case "/clientpage":
                    if(type == null){
                        session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.LOGIN));
                    }else{
                        if(type.equals("Client")){
                            session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.CLIENT));
                        }else {
                            session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.ERROR));
                        }
                    }
                    break;
                    default:
                        session.setAttribute("redirect", PagesJsp.getInstance().getProperty(PagesJsp.INDEX));
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Init of LoginFilter.");
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroy of LoginFilter.");
    }
}
