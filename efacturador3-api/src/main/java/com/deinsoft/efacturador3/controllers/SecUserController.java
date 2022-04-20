package com.deinsoft.efacturador3.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import com.deinsoft.efacturador3commons.model.SecUser;
import com.deinsoft.efacturador3commons.service.SecUserService;

@RequestMapping("api/v1/users")
public class SecUserController {

    private static final Logger logger = LoggerFactory.getLogger(SecUserController.class);

    @Autowired
    SecUserService secUserService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping(value = "/get-all")
    public ResponseEntity<?> getAllSecUser(SecUser secUser, HttpServletRequest request) {
        logger.info("getAllSecUser received: " + secUser.toString());
        List<SecUser> secUserList = secUserService.getSecUsers();
        String nodeAction = "sec-user_view";
        return ResponseEntity.status(HttpStatus.OK).body(secUserList);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> getSecUser(@Param("id") Long id, HttpServletRequest request) {
        logger.info("getSecUser received: " + id);
        SecUser secUser = secUserService.getSecUserById(id);
        String nodeAction = "sec-user_view";
        return ResponseEntity.status(HttpStatus.OK).body(secUser);
    }
//	@GetMapping(value="/getByName")
//	public ResponseEntity<?> getSecUser(@Param("id") Long id, HttpServletRequest request) {
//		logger.info("getSecUser received: "+id);
//		SecUser secUser = secUserService.getSecUser(id);
//		String nodeAction = "sec-user_view";
//		return super.view(secUser, nodeAction, request);
//	}

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveSecUser(@Valid @RequestBody SecUser secUser, BindingResult result, HttpServletRequest request) {
        String password = bCryptPasswordEncoder.encode(secUser.getPassword());
	secUser.setPassword(password);
        SecUser secUserResult = secUserService.save(secUser);
        return ResponseEntity.status(HttpStatus.OK).body(secUserResult);
    }

}
