package poly.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyCustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication)
			throws IOException, ServletException {
		 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		 for(String i : roles) {
			 if (i.contains("ADMIN")) {
		            response.sendRedirect("admin.htm");
		        } else if (i.contains("USER")) {
		            response.sendRedirect("homepage.htm");
		        } else if(i.contains("SHIPPER")) {
		        	response.sendRedirect("shipper.htm");
		        }
		        else {
		        	response.sendRedirect("homepage.htm");
		        }
		 }
		
	}
	
}
