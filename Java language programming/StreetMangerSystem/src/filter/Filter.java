package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filter
 */
public class Filter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		req.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");

		String url = req.getRequestURI();
		
		if(url.equals("/StreetMangerSystem/Login") || url.equals("/StreetMangerSystem/login.jsp") || url.equals("/StreetMangerSystem/register.jsp") || url.equals("/StreetMangerSystem/Register")      ){
			chain.doFilter(request, response);
		}

		else{
		Object user = req.getSession().getAttribute("user");

		if(user != null){
			chain.doFilter(request, response);
		}
		
		else{
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("login.jsp");
		}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
