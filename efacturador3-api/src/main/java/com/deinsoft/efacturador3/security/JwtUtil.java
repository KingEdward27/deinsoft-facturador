/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.security;

/**
 *
 * @author EDWARD-PC
 */
import com.deinsoft.efacturador3commons.model.Empresa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    
    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username) {
//        try {
//            
//        } catch (MalformedJwtException e) {
//            chain.doFailure(FAILURE_FACTORY.jwtMalformed(context, e));
//        }
        String token = Jwts.builder()
                .setSubject(username)
                // Vamos a asignar un tiempo de expiracion de 1 minuto
                // solo con fines demostrativos en el video que hay al final
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, "P@tit0")
                .compact();

        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer " + token);
    }

    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
                        .parseClaimsJws(token.replace("Bearer ", "")) //este metodo es el que valida
                        .getBody()
                        .getSubject();

                // Recordamos que para las demás peticiones que no sean /login
                // no requerimos una autenticacion por username/password
                // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
                return user != null
                        ? new UsernamePasswordAuthenticationToken(user, null, emptyList())
                        : null;
            } catch (Exception ex) {
                log.error("Error: {}, {}", ex, ex.getMessage());
            }
        }

        return null;
    }
    public static Map<String, Object> getJwtLoggedUserData(HttpServletRequest request) throws JsonProcessingException {
		
		String jwt = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY).replace("\"","");
		      Jws<Claims> claims = Jwts.parser()
				.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""));
		Map <String, Object> mp = new HashMap<>();
		
		//mp.put("idProfile",claims.getBody().get("prf").toString());
//                ObjectMapper mapper = new ObjectMapper();
//                Empresa jsonData = (Empresa)claims.getBody().get("authorities");
//                Empresa empresa = mapper.readValue(jsonData, Empresa.class);
//                Empresa jsonString = (Empresa)claims.getBody().get("authorities");
//                JSONObject json = new JSONObject(jsonString);
//                Empresa empresa = (Empresa)json.get("authorities");
//		mp.put("empresa",jsonString);
//		mp.put("empresaId", claims.getBody().get("empresaId"));
		mp.put("numDoc", claims.getBody().get("numDoc").toString());
		mp.put("razonSocial", claims.getBody().get("razonSocial").toString());
		mp.put("usuarioSol", claims.getBody().get("usuarioSol"));
		
		return mp;
	}
}
