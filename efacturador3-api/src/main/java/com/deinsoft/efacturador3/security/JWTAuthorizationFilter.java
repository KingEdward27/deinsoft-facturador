package com.deinsoft.efacturador3.security;

import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String token = req.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		
		if (token != null) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}else{
			req.setAttribute("Access",0);
		}
		chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY).replace("\"","");
		//String tokken = token.replace(Constants.TOKEN_BEARER_PREFIX+" ", "");
		
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			try {
				String empresa = Jwts.parser()
						.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
						.parseClaimsJws(token.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""))
						.getBody()
						.getSubject();

				if (empresa != null) {
					return new UsernamePasswordAuthenticationToken(empresa, null, new ArrayList<>());
				}
				return null;

			}catch(Exception ex){
				LOGGER.error("Error: {}, {}", ex, ex.getMessage());
			}

		}
		return null;
	}
	
	
}
