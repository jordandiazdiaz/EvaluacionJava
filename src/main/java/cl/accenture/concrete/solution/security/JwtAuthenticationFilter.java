package cl.accenture.concrete.solution.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import cl.accenture.concrete.solution.constants.Constants;
import cl.accenture.concrete.solution.domain.JwtAuthenticationToken;
import cl.accenture.concrete.solution.entity.UsersDetail;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	 
	public JwtAuthenticationFilter() {
		super("/app/concrete/profile/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String header = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY);

		if (header == null) {
			throw new RuntimeException("Falta token");
		}
		JwtAuthenticationToken token = new JwtAuthenticationToken(header);
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		UsersDetail user = ((UsersDetail) auth.getPrincipal());
		String token = user.getUsers().getToken();
		response.addHeader(Constants.HEADER_AUTHORIZACION_KEY, token);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}

}
