/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.controllers;

import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3.security.JwtUtil;
import com.deinsoft.efacturador3commons.service.EmpresaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 *
 * @author EDWARD-PC
 */
@Component
public class BaseController {

    @Autowired
    EmpresaService empresaService;

    protected Map<String, Object> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return errores;
    }

    protected Empresa getEmpresa(HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> map = JwtUtil.getJwtLoggedUserData(request);
        String numDoc = (String) map.get("numDoc");
        return empresaService.findByNumdoc(numDoc);
    }
}
