/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.controllers;

import com.deinsoft.efacturador3commons.bean.ComprobanteCab;
import com.deinsoft.efacturador3commons.config.AppConfig;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3.security.SecurityConstants;
import com.deinsoft.efacturador3commons.util.CertificadoFacturador;
import com.deinsoft.efacturador3commons.service.EmpresaService;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import com.deinsoft.efacturador3commons.util.FacturadorUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author EDWARD-PC
 */
@RestController
@RequestMapping("api/v1/empresa")
public class EmpresaController {

    private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    EmpresaService empresaService;

    @Autowired
    AppConfig appConfig;

    @PostMapping(value = "save")
    public ResponseEntity<?> save(@Valid @RequestBody Empresa empresa, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        HashMap<String, Object> resultado = null;
//        File directorio=new File(raiz + empresa.getNumdoc());
//        directorio.mkdir();
        String userDirectory = new File(".").getAbsolutePath();
        String userDirectory2 = new File(".").getPath();
        String userDirectory3 = new File(".").getCanonicalPath();
//        String[] wa = new File("/home/.").list();
//        for (String string : wa) {
//            log.info("string: " + string);
//        }
        String dir = System.getProperty("user.dir");
        log.info("userDirectory: " + userDirectory);
        log.info("userDirectory2: " + userDirectory2);
        log.info("userDirectory3: " + userDirectory3);
        log.info("dir: " + dir);
        Empresa empresaResult = null;
        if (empresa.getId() != null && empresa.getId() > 0) {
            empresaResult = empresaService.save(empresa);
//            resultado.put("message", "Empresa actualizada!");
            resultado.put("empresa", empresaResult);
        } else {
            try {
                Empresa foundEmpresa = empresaService.findByNumdoc(empresa.getNumdoc());
                if (foundEmpresa != null || (foundEmpresa != null && foundEmpresa.getNumdoc() != null)) {
                    return ResponseEntity.status(HttpStatus.FOUND).body("Ya se encuentra registrado el número de documento de la empresa");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error inesperador al buscar la empresa");
            }
            try {
                HashMap<String, Object> param = new HashMap<>();
                param.put("nombreCertificado", empresa.getCertName());
                param.put("passPrivateKey", empresa.getCertPass());
                param.put("numDoc", empresa.getNumdoc());
                param.put("rootPath", appConfig.getRootPath());
                resultado = (new CertificadoFacturador()).importarCertificado(param);
            } catch (Exception e) {
                resultado = new HashMap<>();
                resultado.put("validacion", e.getMessage());
                log.error(e.getMessage());
            }
            if (resultado != null) {
                String message = (resultado.get("validacion") != null) ? (String) resultado.get("validacion") : "";
                if (!message.equalsIgnoreCase("EXITO")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                }
            }
//            String tokenold = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY).replace("\"","");
//		Date expirationDate = Jwts.parser()
//				.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
//				.parseClaimsJws(tokenold.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""))
//				.getBody().getExpiration();
//				
//		Date issuedAt = Jwts.parser()
//				.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
//				.parseClaimsJws(tokenold.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""))
//				.getBody().getIssuedAt();

//		String id = request.getSession().getId();
//		List<String> authorities = util.getListAuthorities( updatedAuthorities );
//		Session service = util.setSession( id, authorities, secRoleUser, usr );
//		Session session = sessionService.save( service );
            String token = generateToken(empresa);

            log.info("********* TOKEN: {}", SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
//		LOGGER.info( "********* uuid mongodb: {}" , session.getId());
            response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
//		response.addHeader("sessionId", session.getId());
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

            empresa.setToken(token);
            empresa.setCertPass(FacturadorUtil.Encriptar(empresa.getCertPass()));
            empresaResult = empresaService.save(empresa);
            resultado.put("message", "Empresa creada!, se agregó correctamente la clave privada");
            resultado.put("empresa", empresaResult);
        }

//        // Falta validar si son todos los perfiles
////		boolean state;
//		
//		// Getting the authorities according to new selected user profile(s)
////		      List<String> permissionProfilelist;
////		if(StringUtils.equals(secRoleUser.getSecUser().getName(), Constant.ADMIN)) {
////			permissionProfilelist = permissionProfileService.getAuthoritiesByAdmin();
////		}
////		else {
////			permissionProfilelist = permissionProfileService.getAuthoritiesByRole(secRoleUser.getSecRole().getId());
////		}
////		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
////		
////		// Setting the new granted authorities  
////		for(int item = 0; item < permissionProfilelist.size(); item++) {
////			updatedAuthorities.add(new SimpleGrantedAuthority(permissionProfilelist.get(item)));
////		}
////		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
////		      SecurityContextHolder.getContext().setAuthentication(newAuth);
//		
////		String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
////		ocs.opensoft.model.SecUser usr = userService.findByName(loggedUsername).get(0);
////		
////                state = usr.getState() == Status.REGISTRADO.getValue();
//		
//		
//		// Updating the jason Web Token
//		String tokenold = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY).replace("\"","");
//		Date expirationDate = Jwts.parser()
//				.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
//				.parseClaimsJws(tokenold.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""))
//				.getBody().getExpiration();
//				
//		Date issuedAt = Jwts.parser()
//				.setSigningKey(SecurityConstants.SUPER_SECRET_KEY)
//				.parseClaimsJws(tokenold.replace(SecurityConstants.TOKEN_BEARER_PREFIX + " ", ""))
//				.getBody().getIssuedAt();
//		
////		String id = request.getSession().getId();
////		List<String> authorities = util.getListAuthorities( updatedAuthorities );
//		
//		
////		Session service = util.setSession( id, authorities, secRoleUser, usr );
////		Session session = sessionService.save( service );
//		
//		String token = Jwts.builder()
//                                .setIssuedAt(new Date()).setIssuer(SecurityConstants.ISSUER_INFO)
//				.setId("DEFACT-JWT")
//				.setSubject(empresa.getNumdoc()+ "/" + empresa.getRazonSocial())
//				//.claim("prf", secRoleUser.getSecRole())
//				//.claim("cg", secRoleUser.getCnfTenant())
//				//.claim("session", id)
//				//.claim("cg", secRoleUser.getTenants())
//				//.claim("cia", secRoleUser.getCompanies())
//				//.claim("brn", secRoleUser.getOrgs())
//				//.claim("usrEmail", usr.getEmail())
////				.claim("empresaId", empresa.getIdempresa())
//				.claim("authorities", empresa.toString()) //((User)auth.getPrincipal()).getAuthorities())
////				.claim("sessionId", session.getId()) //((User)auth.getPrincipal()).getAuthorities())
//				.setIssuedAt(issuedAt)
//				.setExpiration(expirationDate)
//				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SUPER_SECRET_KEY).compact();
//		
//		log.info( "********* TOKEN: {}" , SecurityConstants.TOKEN_BEARER_PREFIX + " " + token );
////		LOGGER.info( "********* uuid mongodb: {}" , session.getId());
//		response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX+" "+token);
////		response.addHeader("sessionId", session.getId());
//		response.addHeader("Access-Control-Expose-Headers", "Authorization");
//		response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
//		
////		return new User(usr.getName(), usr.getPassword(), state, true, true, true, updatedAuthorities);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PostMapping(value = "/update-token")
    public ResponseEntity<?> generateToken(@RequestParam(name = "id") String id,
            HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        HashMap<String, Object> resultado = new HashMap<>();
//        File directorio=new File(raiz + empresa.getNumdoc());
//        directorio.mkdir();
        Empresa empresa = empresaService.getEmpresaById(Integer.parseInt(id));
        String token = generateToken(empresa);

        log.info("********* TOKEN: {}", SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
//		LOGGER.info( "********* uuid mongodb: {}" , session.getId());
        response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
//		response.addHeader("sessionId", session.getId());
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        empresa.setToken(token);
//        empresa.setCertPass(FacturadorUtil.Encriptar(empresa.getCertPass()));
        Empresa empresaResult = empresaService.save(empresa);
        resultado.put("message", "Empresa actualizada!, se actualizó el token");
        resultado.put("empresa", empresaResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
    String generateToken(Empresa empresa){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer(SecurityConstants.ISSUER_INFO)
                .setId("DEFACT-JWT")
                .setSubject(empresa.getNumdoc() + "/" + empresa.getRazonSocial())
                //				.claim("empresaId", empresa.getIdempresa())
                //                                .claim("empresaId", empresa.getIdempresa()) 
                .claim("numDoc", empresa.getNumdoc()) //((User)auth.getPrincipal()).getAuthorities())
                //				.claim("authorities", empresa.getNumdoc())
                .claim("razonSocial", empresa.getRazonSocial()) //((User)auth.getPrincipal()).getAuthorities())
                .claim("usuarioSol", empresa.getUsuariosol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + SecurityConstants.TOKEN_EXPIRATION_TIME * 1000 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SUPER_SECRET_KEY).compact();
    }
}
