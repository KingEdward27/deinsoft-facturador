package com.deinsoft.efacturador3.security;

import com.deinsoft.efacturador3commons.model.Empresa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
/**
 * @author bangulo
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Empresa credenciales = new ObjectMapper().readValue(request.getInputStream(), Empresa.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getNumdoc(), credenciales.getUsuariosol() + credenciales.getClavesol(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
        LocalDateTime currentTime = LocalDateTime.now();
		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + SecurityConstants.TOKEN_EXPIRATION_TIME * 1000 * 60 * 1000/* *10000 maximo tiempo posible*/);
		LOGGER.info("expirationDate: " + expirationDate);
		//String username = ((UserDetails) auth.getPrincipal()).getUsername();
		//Usuario usuario =  usuarioRepository.findUsuarioByUsername(username);
		//String idUp = usuario.getUnidadPolicial().getIdUnidadPolicial();

		//Usuario usr = userService.getUserByUsername(usrname);
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(SecurityConstants.ISSUER_INFO)
				.setId("OCS-JWT")
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.claim("authorities", "OCS")
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(expirationDate)
				//.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(TOKEN_EXPIRATION_TIME).toInstant()))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SUPER_SECRET_KEY).compact();
		LOGGER.info( "********* TOKEN: {}" , SecurityConstants.TOKEN_BEARER_PREFIX + " " + token );
		response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX+" "+token);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		LOGGER.info("Authorities:{} ", auth.getAuthorities());
		LOGGER.info("UserDetails:{} ", userDetails.toString());
		LOGGER.info("********** User has authorities: {}" + userDetails.getAuthorities());
	}
}
