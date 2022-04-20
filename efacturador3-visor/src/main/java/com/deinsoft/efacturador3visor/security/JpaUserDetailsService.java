package com.deinsoft.efacturador3visor.security;

import com.deinsoft.efacturador3commons.model.SecRole;
import com.deinsoft.efacturador3commons.model.SecRoleUser;
import com.deinsoft.efacturador3commons.model.SecUser;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.deinsoft.efacturador3commons.service.SecUserService;
//import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
//import com.bolsadeideas.springboot.app.models.entity.Role;
//import com.bolsadeideas.springboot.app.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private SecUserService secUserService;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
            SecUser usuario = secUserService.getSecUserByName(username);
        
        if(usuario == null) {
        	logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(SecRoleUser roleUser: usuario.getListSecRoleUser()) {
        	logger.info("Role: ".concat(roleUser.getSecRole().getName()));
        	authorities.add(new SimpleGrantedAuthority(roleUser.getSecRole().getName()));
        }
        
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        
		return new User(usuario.getName(), usuario.getPassword(), true, true, true, true, authorities);
	}

}
