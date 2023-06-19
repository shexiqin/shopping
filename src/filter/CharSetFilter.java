package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
编码过滤器
 */
public class CharSetFilter implements Filter {
    String encode;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config){
        encode = config.getInitParameter("encode");
    }

}
