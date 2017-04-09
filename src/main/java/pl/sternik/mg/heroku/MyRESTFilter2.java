package pl.sternik.mg.heroku;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="/MyRESTFilter2",
//servletNames={"HelloServlet"},
urlPatterns={"/hello"})
public class MyRESTFilter2 implements javax.servlet.Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-------- 22222222 ----------");
		chain.doFilter(req,  res);
		System.out.println("------ 77777777 -------");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
