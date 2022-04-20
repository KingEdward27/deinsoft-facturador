/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service.impl;

import com.deinsoft.efacturador3commons.model.SecUser;
import com.deinsoft.efacturador3commons.repository.SecUserRepository;
import com.deinsoft.efacturador3commons.service.SecUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EDWARD-PC
 */

@Service
public class SecUserServiceImpl implements SecUserService{

    private static final Logger log = LoggerFactory.getLogger(SecUserServiceImpl.class);
    
    @Autowired
    SecUserRepository secUserRepository;
    
    @Override
    public SecUser getSecUserById(long id) {
        return secUserRepository.getById(id);
    }

    @Override
    public List<SecUser> getSecUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SecUser save(SecUser secUser) {
        return secUserRepository.save(secUser);
    }
    @Override
    public SecUser getSecUserByName(String name) {
        return secUserRepository.findByName(name);
    }
}
